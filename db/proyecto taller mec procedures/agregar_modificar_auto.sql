CREATE DEFINER=`root`@`localhost` PROCEDURE `agregar_modificar_auto`(INOUT p_id_auto	int,
										   p_modelo		int,
                                           p_marca	varchar(255),
                                           p_placas	varchar(255),
                                           p_color	varchar(255)
                                           )
BEGIN

DECLARE p_max_id	int;

IF p_id_auto = 0 THEN
	SELECT COALESCE(MAX(id_auto),1) into p_max_id from tbl_auto;
    SET p_max_id = p_max_id + 1;    
    
    INSERT INTO tbl_auto
    (
		id_auto,
        modelo,
        marca,
        placas,
        color
    )
    values
    (
		p_max_id,
        p_modelo,
        p_marca,
        p_placas,
        p_color
    );
    
    SET p_id_auto = p_max_id;
    
    Select 'Registro guardado exitosamente' as mensaje, 0 as error from dual;

ELSEIF p_id_auto > 0 THEN

	 UPDATE tbl_auto set modelo = p_modelo, marca = p_marca, placas = p_placas, color = p_color
     WHERE id_auto = p_id_auto;

	 Select 'Registro actualizado exitosamente' as mensaje, 0 as error from dual;
    
END IF;


END