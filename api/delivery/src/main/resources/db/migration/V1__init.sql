CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       email VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       phone_number VARCHAR(9) NOT NULL,
                       country VARCHAR(56) NOT NULL,
                       role VARCHAR(50) NOT NULL,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE couriers (
                          id UUID PRIMARY KEY,
                          first_name VARCHAR(30) NOT NULL,
                          last_name VARCHAR(30) NOT NULL,
                          phone_number VARCHAR(9) NOT NULL,
                          country VARCHAR(56) NOT NULL
);

CREATE TABLE shipments (
                           id UUID PRIMARY KEY,
                           tracking_code VARCHAR(100) NOT NULL UNIQUE,
                           courier_id UUID,
                           client_id UUID NOT NULL,
                           created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           status VARCHAR(50) NOT NULL,

                           CONSTRAINT fk_shipment_courier
                               FOREIGN KEY (courier_id)
                                   REFERENCES couriers(id),

                           CONSTRAINT fk_shipment_client
                               FOREIGN KEY (client_id)
                                   REFERENCES users(id)
);

CREATE TABLE shipment_history (
                                  id UUID PRIMARY KEY,
                                  shipment_id UUID NOT NULL,
                                  status VARCHAR(15) NOT NULL,
                                  location VARCHAR(100) NOT NULL,
                                  description VARCHAR(256) NOT NULL,
                                  event_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                  CONSTRAINT fk_status_shipment
                                      FOREIGN KEY (shipment_id)
                                          REFERENCES shipments(id)
);