-- liquibase formatted sql
-- changeset andrey-rock:1

CREATE TABLE wallets
(
    wallet_id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    balance    NUMERIC          DEFAULT 0
);