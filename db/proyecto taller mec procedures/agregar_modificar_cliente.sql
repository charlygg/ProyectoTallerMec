CREATE DEFINER=`root`@`localhost` PROCEDURE `agregar_modificar_cliente`(INOUT p_id_cliente int, 
											  p_nombre	varchar(255), 
                                              p_ap_pat	varchar(255), 
                                              p_ap_mat	varchar(255), 
                                              p_edad	int, 
                                              p_email	varchar(255), 
                                              p_activo	varchar(1), 
                                              p_calle	varchar(255), 
                                              p_no_ext	varchar(255), 
                                              p_no_int	varchar(255), 
                                              p_cod_postal int, 
                                              p_estado	varchar(255), 
                                              p_telefono varchar(255))
BEGIN

DECLARE p_max_id	int;

IF p_id_cliente = 0 THEN
	SELECT COALESCE(MAX(id_cliente),1) into p_max_id from tbl_clientes;
    SET p_max_id = p_max_id + 1;  
    
    INSERT INTO tbl_clientes
    (
		id_cliente, 
		nombre, 
		ap_pat, 
		ap_mat, 
		edad, 
		email, 
		activo, 
		calle, 
		no_ext, 
		no_int, 
		cod_postal, 
		estado, 
		telefono
    )
    values
    (
		p_max_id,
		p_nombre, 
		p_ap_pat, 
		p_ap_mat, 
		p_edad, 
		p_email, 
		p_activo, 
		p_calle, 
		p_no_ext, 
		p_no_int, 
		p_cod_postal, 
		p_estado, 
		p_telefono 
    );
    
    SET p_id_cliente = p_max_id;
    
    Select 'Registro guardado exitosamente' as mensaje, 0 as error, p_max_id as p_id_cliente  from dual;
    
END IF;    

END