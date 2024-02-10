
-- Desarrollado por Julian Enrique Rodriguez Saavedra
-- Debido a que son dos microservicios, el estándar de desarrollo de dicha arquitectura indica que cada microservicio se conecta a su propia base de datos
-- en la medida de lo posible por lo que opte por crear dos bases de datos en PostgreSQL.

-- clientesDevsuDB es donde se crear y almacena la tabla de clientes y su información.
-- cuentasDevsuDB es donde se crea y almaceta las tablas de cuentas y movimientos junto a su información.


-- BASE DE DATOS "clientesDevsuDB"

alter table if exists public.cliente drop constraint if exists cliente_telefono_uk;
alter table if exists public.cliente drop constraint if exists cliente_identificacion_uk;
alter table if exists public.cliente drop constraint if exists cliente_pk;

drop table if exists public.cliente;

create table if not exists public.cliente (
	cliente_id Integer generated always as identity not null, 
	nombre varchar(50) not null,
	genero varchar(10),
	edad integer,
	identificacion varchar(20) not null,
	direccion varchar(100) not null,
	telefono bigint not null,
	contrasena varchar(100) not null,
	estado boolean not null
);
alter table if exists public.cliente add constraint cliente_pk primary key (cliente_id);
alter table if exists public.cliente add constraint cliente_identificacion_uk unique (identificacion);
alter table if exists public.cliente add constraint cliente_telefono_uk unique (telefono);


-- BASE DE DATOS "cuentasDevsuDB"

alter table if exists public.movimiento drop constraint if exists movimiento_cuenta_id_fk;
alter table if exists public.movimiento drop constraint if exists movimiento_pk;
alter table if exists public.cuenta drop constraint if exists cuenta_numero_cuenta_uk;
alter table if exists public.cuenta drop constraint if exists cuenta_pk;

drop table if exists public.movimiento;
drop table if exists public.cuenta;

create table if not exists public.cuenta (
	cuenta_id integer generated always as identity not null,
	cliente_id integer not null,
	numero_cuenta integer not null,
	tipo_cuenta varchar(10) not null,
	saldo_inicial bigint not null,
	estado boolean not null
);
alter table if exists public.cuenta add constraint cuenta_pk primary key (cuenta_id);
alter table if exists public.cuenta add constraint cuenta_numero_cuenta_uk unique (numero_cuenta);

create table if not exists public.movimiento (
	movimiento_id integer generated always as identity not null,
	cuenta_id integer not null,
	fecha date not null,
	tipo_movimiento varchar(10) not null,
	valor bigint not null,
	saldo bigint not null,
	estado boolean not null
);
alter table if exists public.movimiento add constraint movimiento_pk primary key (movimiento_id);
alter table if exists public.movimiento add constraint movimiento_cuenta_id_fk foreign key (cuenta_id) references public.cuenta(cuenta_id);
