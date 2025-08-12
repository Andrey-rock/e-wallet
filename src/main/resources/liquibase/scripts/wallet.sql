-- liquibase formatted sql
-- changeset andrey-rock:1

CREATE TABLE wallets
(
    wallet_id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    balance    NUMERIC          DEFAULT 0
);

-- changeset andrey-rock:2

insert into wallets(wallet_id, balance)
VALUES ('cda242bc-8b4e-4d3f-ba09-974fb6cce200', 1000),
       ('cda242bc-8b4e-4d3f-ba09-974fb6cce210', 1000),
       ('cda242bc-8b4e-4d3f-ba09-974fb6cce220', 1000),
       ('cda242bc-8b4e-4d3f-ba09-974fb6cce230', 1000),
       ('cda242bc-8b4e-4d3f-ba09-974fb6cce240', 1000);