CREATE TABLE customers (
                           idCustomer INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
                           name VARCHAR (45),
                           password VARCHAR(45),
                           solde DOUBLE
);

CREATE TABLE operation (
                           idOperation INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
                           operation VARCHAR (45),
                           idCustomer INTEGER,
                           FOREIGN KEY (idCustomer) REFERENCES customers(idCustomer)
);

INSERT INTO customer (name, password, solde) VALUES ('Simon','1234',1000000);
INSERT INTO customer (name, password, solde) VALUES ('Richard','1234',1000000);


