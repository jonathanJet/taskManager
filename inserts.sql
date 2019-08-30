/*insert into tbl_roles(id_rol, nombre_rol, activo) values ((select coalesce(max(id_rol),0) +1 from tbl_roles),'Desarrollador', 1);
insert into tbl_roles(id_rol, nombre_rol, activo) values ((select coalesce(max(id_rol),0) +1 from tbl_roles),'Lider', 1);
insert into tbl_roles(id_rol, nombre_rol, activo) values ((select coalesce(max(id_rol),0) +1 from tbl_roles),'Administrador', 1);

insert into tbl_estado(id_estado,nombre_estado) values ((select coalesce(max(id_estado),0) +1 from tbl_estado),'En Proceso');
insert into tbl_estado(id_estado,nombre_estado) values ((select coalesce(max(id_estado),0) +1 from tbl_estado),'Finalizado');
insert into tbl_estado(id_estado,nombre_estado) values ((select coalesce(max(id_estado),0) +1 from tbl_estado),'Pendiente');
insert into tbl_estado(id_estado,nombre_estado) values ((select coalesce(max(id_estado),0) +1 from tbl_estado),'Rechazado');*/

select a.id_usuario, a.nombre from tbl_usuario a, tbl_usuario_rol b where a.id_usuario = b.id_usuario and b.id_rol = 1