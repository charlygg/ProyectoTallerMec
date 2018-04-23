CREATE DEFINER=`root`@`localhost` PROCEDURE `agregar_modificar_auto`(INOUT p_id_auto	int,
										                       IN p_modelo		varchar(45),
                                           IN p_marca	varchar(255),
                                           IN p_linea  varchar(45),
                                           IN p_anio   int,
                                           IN p_serie	varchar(255),
                                           IN p_color	varchar(255),
                                           IN p_placas varchar(13),
                                           IN p_kilometraje  INT,
                                           IN p_id_cliente  INT
                                           )
BEGIN

DECLARE p_max_id	int;
DECLARE p_existe  int;

IF p_id_auto = 0 THEN
	SELECT COALESCE(MAX(id_auto),1) into p_max_id from tbl_auto;
    SET p_max_id = p_max_id + 1;    
    
    INSERT INTO tbl_auto
    (
      id_auto,
      modelo,
      marca,
      linea,
      anio,
      color,
      placas,
      kilometraje,
      serie,
      id_cliente
    )
    values
    (
      p_max_id,
      p_modelo,
      p_marca,
      p_linea,
      p_anio,
      p_color,
      p_placas,
      p_kilometraje ,
      p_serie,
       COALESCE(IF(p_id_cliente=0,-1,p_id_cliente),-1) -- Si se asigna cliente por defecto si no se elige un cliente existente
    );
    
    SET p_id_auto = p_max_id;
    
    Select 'Registro guardado exitosamente' as mensaje, 0 as error, p_id_auto as p_id_auto from dual;

ELSEIF p_id_auto > 0 THEN

    SELECT COUNT(*) INTO p_existe
    FROM tbl_auto WHERE id_auto = p_id_auto;

    IF p_existe > 0 THEN
    
      UPDATE tbl_auto set modelo	=	p_modelo
          ,marca	=	p_marca
          ,linea	=	p_linea  
          ,anio	=	p_anio   
          ,color	=	p_color
          ,placas	=	p_placas 
          ,kilometraje	=	p_kilometraje 
          ,serie	=	p_serie
          ,id_cliente = COALESCE(IF(p_id_cliente=0,-1,p_id_cliente),-1) -- Si se asigna cliente por defecto si no se elige un cliente existente
      WHERE id_auto = p_id_auto;      
      
      Select 'Registro actualizado exitosamente' as mensaje, 0 as error, 1 as actualizado  from dual;
    ELSEIF p_existe = 0 THEN
      Select 'El auto no existe para actualizar, verificar que el id exista en la tabla de autos' as mensaje, 1 as error, p_max_id as p_id_cliente  from dual;
    END IF;
    
END IF;


END