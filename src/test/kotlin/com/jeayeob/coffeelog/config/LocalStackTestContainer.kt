package com.jeayeob.coffeelog.config

import org.springframework.stereotype.Component
import org.springframework.test.context.DynamicPropertyRegistry
import org.testcontainers.containers.localstack.LocalStackContainer
import org.testcontainers.containers.localstack.LocalStackContainer.Service.SQS
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.utility.DockerImageName


@Component
class LocalStackTestContainer {

    companion object {
        private const val DOCKER_TAG = "localstack/localstack:3.0.2"
        private const val INPUT_QUEUE_NAME = "commands.fifo"
        private const val OUTPUT_QUEUE_NAME = "output.fifo"

        @JvmStatic
        @Container
        val localstackContainer = LocalStackContainer(DockerImageName.parse(DOCKER_TAG))
            .apply { withEnv("queue_name", OUTPUT_QUEUE_NAME) }
            .apply { withServices(SQS) }
            .apply { start() }
            .apply { execInContainer("awslocal", "sqs", "create-queue", "--queue-name", OUTPUT_QUEUE_NAME, "--attributes", "FifoQueue=true, ContentBasedDeduplication=true")
                .apply {
                    println("stdout: $stdout")
                    println("stderr: $stderr")
                }
            }
            .apply { execInContainer("awslocal", "sqs", "create-queue", "--queue-name", INPUT_QUEUE_NAME, "--attributes", "FifoQueue=true, ContentBasedDeduplication=true")
                .apply {
                    println("stdout: $stdout")
                    println("stderr: $stderr")
                }
            }


        fun setProperties(registry: DynamicPropertyRegistry, container: LocalStackContainer) {
            registry.add("spring.cloud.aws.credentials.access-key") { container.accessKey }
            registry.add("spring.cloud.aws.credentials.secret-key") { container.secretKey }
            registry.add("spring.cloud.aws.sqs.endpoint") {
                container.getEndpointOverride(SQS).toString()
            }
            registry.add("spring.cloud.aws.sqs.commands") { INPUT_QUEUE_NAME }
            registry.add("spring.cloud.aws.sqs.output") { OUTPUT_QUEUE_NAME }
            registry.add("spring.cloud.aws.region.static") { container.region }
        }
    }
}