CREATE DEFINER=`root`@`localhost` PROCEDURE `agregar_modificar_cliente`(INOUT p_id_cliente int, 
											                        IN p_nombre_comp	varchar(255), 
                                              IN p_compania	varchar(255), 
                                              IN p_rfc	varchar(255), 
                                              IN p_calle varchar(150),
                                              IN p_no_ext	varchar(255), 
                                              IN p_no_int	varchar(255), 
                                              IN p_cod_postal int, 
                                              IN p_estado	varchar(255), 
                                              IN p_telefono varchar(255),
                                              IN p_email	varchar(255), 
                                              IN p_activo	varchar(1), 
                                              IN p_ciudad  varchar(45),
                                              IN p_fax varchar(45)
                                              )
BEGIN

DECLARE p_max_id	int;
DECLARE p_existe  int;

IF p_id_cliente = 0 THEN
	SELECT COALESCE(MAX(id_cliente),1) into p_max_id from tbl_clientes;
    SET p_max_id = p_max_id + 1;  
    
    INSERT INTO tbl_clientes
    (
    	id_cliente,
    	nombre_completo,
    	compania,
    	rfc,
    	calle,
    	no_ext,
    	no_int,
    	codigo_postal	,
    	estado,
    	telefono,
    	correo,
    	activo,
    	ciudad,
    	fax
    )
    values
    (
  		p_max_id,
      p_nombre_comp	, 
      p_compania, 
      p_rfc	, 
      p_calle,
      p_no_ext,
      p_no_int,
      p_cod_postal,
      p_estado,
      p_telefono,
      p_email,
      p_activo,
      p_ciudad,
      p_fax
    );
    
    SET p_id_cliente = p_max_id;
    
    Select 'Registro guardado exitosamente' as mensaje, 0 as error, p_max_id as p_id_cliente  from dual;

ELSEIF p_id_cliente > 0 THEN   

    SELECT COUNT(*) INTO p_existe
    FROM tbl_clientes WHERE id_cliente = p_id_cliente;
    
    IF p_existe > 0 THEN
    
      UPDATE tbl_clientes SET nombre_completo	=	p_nombre_comp,
            compania	=	p_compania,
            rfc	=	p_rfc,
            calle	=	p_calle,
            no_ext	=	p_no_ext,
            no_int	=	p_no_int,
            codigo_postal	=	p_cod_postal,
            estado	=	p_estado,
            telefono	=	p_telefono,
            correo	=	p_email,
            activo	=	p_activo,
            ciudad	=	p_ciudad,
            fax	=	p_fax
      WHERE id_cliente = p_id_cliente;
    
      Select 'Registro actualizado exitosamente' as mensaje, 0 as error, 1 as actualizado  from dual;
    ELSEIF p_existe = 0 THEN
      Select 'El registro no existe para actualizar, verificar que el id exista en la tabla' as mensaje, 1 as error, p_max_id as p_id_cliente  from dual;
    END IF;

    
END IF;    

END