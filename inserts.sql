/*insert into tbl_roles(id_rol, nombre_rol, activo) values ((select coalesce(max(id_rol),0) +1 from tbl_roles),'Desarrollador', 1);
insert into tbl_roles(id_rol, nombre_rol, activo) values ((select coalesce(max(id_rol),0) +1 from tbl_roles),'Lider', 1);
insert into tbl_roles(id_rol, nombre_rol, activo) values ((select coalesce(max(id_rol),0) +1 from tbl_roles),'Administrador', 1);

insert into tbl_estado(id_estado,nombre_estado) values ((select coalesce(max(id_estado),0) +1 from tbl_estado),'En Proceso');
insert into tbl_estado(id_estado,nombre_estado) values ((select coalesce(max(id_estado),0) +1 from tbl_estado),'Finalizado');
insert into tbl_estado(id_estado,nombre_estado) values ((select coalesce(max(id_estado),0) +1 from tbl_estado),'Pendiente');
insert into tbl_estado(id_estado,nombre_estado) values ((select coalesce(max(id_estado),0) +1 from tbl_estado),'Rechazado');*/

select id_tarea, a.nombre Tarea, a.descripcion, a.tiempo_estimado, a.tiempo_real, b.nombre, b.id_usuario,c.id_estado,c.nombre_estado from tbl_tarea a, tbl_usuario b, tbl_estado c where a.id_usuario = b.id_usuario and a.id_estado = c.id_estado