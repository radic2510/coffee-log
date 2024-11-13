CREATE TABLE test
(
    id               uuid         NOT NULL,
    value            varchar(255) NULL,
    created_at       timestamp    NULL,
    updated_at       timestamp    NULL,
    deleted_at       timestamp    NULL,
    CONSTRAINT material_contract_pkey PRIMARY KEY (id)
);