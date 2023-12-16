use jonathan041096_mymenu;
CREATE TABLE usuarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(250) NOT NULL
);

CREATE TABLE menu (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255),
);


CREATE TABLE usuarios_menu_relacion (
    id INT PRIMARY KEY AUTO_INCREMENT,
    usuario_id INT,
    menu_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (menu_id) REFERENCES menu(id),
    UNIQUE KEY unique_usuarios_menu (usuario_id, menu_id)
);

CREATE TABLE producto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255),
    precio DECIMAL(15,2),
    menu_id INT,
    FOREIGN KEY (menu_id) REFERENCES menu(id)
);