CREATE DEFINER=`root`@`localhost` PROCEDURE `agregar_modificar_orden_trabajo`(INOUT p_id_orden_trabajo	int,
                                                   IN p_folio varchar(45),
                                                   IN p_id_cliente  INT,
                                                   IN p_id_auto INT,
                                                   IN p_fecha_registro  DATETIME,
                                                   IN p_estado  VARCHAR(1),
                                                   IN p_fecha_completado  DATETIME,
                                                   IN p_id_usuario  INT,
                                                   IN p_id_empleado INT
                                                   )
BEGIN
	
DECLARE p_max_id	int;
DECLARE p_existe  int;  

IF p_id_orden_trabajo = 0 THEN
    SELECT COALESCE(MAX(id_orden_trabajo),1) into p_max_id from tbl_orden_trabajo;
    SET p_max_id = p_max_id + 1;  
    
    INSERT INTO tbl_orden_trabajo 
    (
      id_orden_trabajo,
      folio,
      id_cliente,
      id_auto,
      fecha_registro,
      estado,
      fecha_completado,
      tbl_usuarios_id_usuarios,
      id_empleado_asignado
    ) VALUES
    (
      p_max_id,
      p_folio,
      p_id_cliente,
      p_id_auto,
      p_fecha_registro,
      p_estado,
      p_fecha_completado,
      p_id_usuario,
      p_id_empleado
    );
    
    SET p_id_orden_trabajo = p_max_id;
    
    Select 'Registro guardado exitosamente' as mensaje, 0 as error, p_max_id as p_id_orden_trabajo  from dual;

ELSEIF p_id_orden_trabajo > 0 THEN
  
    SELECT COUNT(*) INTO p_existe
    FROM tbl_orden_trabajo WHERE id_orden_trabajo = p_id_orden_trabajo;
    
    IF p_existe > 0 THEN
    
      UPDATE tbl_orden_trabajo SET folio	=	p_folio
        ,id_cliente	=	p_id_cliente
        ,id_auto	=	p_id_auto 
        ,fecha_registro	=	p_fecha_registro  
        ,estado	=	p_estado  
        ,fecha_completado	=	p_fecha_completado
        ,tbl_usuarios_id_usuarios	=	p_id_usuario
        ,id_empleado_asignado	=	p_id_empleado      
      WHERE id_orden_trabajo = p_id_orden_trabajo;
    
      Select 'Registro actualizado exitosamente' as mensaje, 0 as error, 1 as actualizado  from dual;
    ELSEIF p_existe = 0 THEN
      Select 'El registro no existe para actualizar, verificar que el id exista en la tabla' as mensaje, 1 as error, p_max_id as p_id_cliente  from dual;
    END IF;
  
END IF;
  
END