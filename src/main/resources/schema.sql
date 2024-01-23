SET FOREIGN_KEY_CHECKS = 0;

– Drop all tables
DROP TABLE IF EXISTS RECIBO_DETALLES;
DROP TABLE IF EXISTS RECIBO;
DROP TABLE IF EXISTS PRODUCTO;
DROP TABLE IF EXISTS CLIENTE;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE CLIENTE (
id INT PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(75) NOT NULL,
apellido VARCHAR(75) NOT NULL,
documento VARCHAR(11) NOT NULL
);



CREATE TABLE PRODUCTO (
id INT PRIMARY KEY AUTO_INCREMENT,
descripcion VARCHAR(150) NOT NULL,
codigo VARCHAR(50) NOT NULL,
stock int NOT NULL,
precio double NOT NULL
);



CREATE TABLE RECIBO (
id INT PRIMARY KEY AUTO_INCREMENT,
cliente_id INT,
creacion DATETIME,
recibo_total DOUBLE,
FOREIGN KEY (cliente_id) REFERENCES CLIENTE(id)
);


CREATE TABLE RECIBO_DETALLES (
id INT PRIMARY KEY AUTO_INCREMENT,
recibo_id INT,
monto INT,
producto_id INT,
precio DOUBLE,
FOREIGN KEY (recibo_id) REFERENCES recibo(id),
FOREIGN KEY (producto_id) REFERENCES producto(id)
);