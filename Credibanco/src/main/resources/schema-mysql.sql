drop database if exists credibanco;

create database credibanco;

use credibanco;

create table tarjeta (
PAN varchar (20) not NULL ,
titular varchar (99) not null,
cedula varchar (16) not null,
tipo varchar (10) not null,
telefono varchar (10) not null,
validation_num int not null,
estado varchar(8),
primary key (PAN)
);

create table transacciones (
reference_number varchar(6) not null,
pan varchar (20) not NULL ,
total_compra double not null,
direccion_compra varchar(100),
estado varchar(10) not null,
fecha datetime not null,
primary key (reference_number),
foreign key (pan) references tarjeta (PAN));


-- información inicial de prueba

insert into tarjeta (PAN, titular, cedula, tipo, telefono, validation_num, estado) values (
'1231231231234546',
'Luis Lara',
'1018511129',
'débito',
'3219251701',
CEIL( RAND() * 100),
'Creada');

insert into tarjeta (PAN, titular, cedula, tipo, telefono, validation_num, estado) values (
'12312312332152595',
'Luis Lara 2',
'1018511129',
'débito',
'3219251701',
CEIL( RAND() * 100),
'Enrolada');

insert into transacciones (reference_number,pan,total_compra,direccion_compra,estado,fecha) values (
'1',
'1231231231234546',
'10000',
'Calle 2',
'Aprobada',
(CONVERT_TZ(NOW(), '+00:00', '+05:00')));
