CREATE TABLE shipment_history
(
    id          UUID                        NOT NULL,
    shipment_id UUID                        NOT NULL,
    status      VARCHAR(255)                NOT NULL,
    location    VARCHAR(100),
    description VARCHAR(255),
    event_time  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_shipment_history PRIMARY KEY (id)
);