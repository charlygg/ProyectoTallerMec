CREATE DEFINER=`root`@`localhost` PROCEDURE `obtener_cliente_by_id`(p_id	int)
BEGIN

SELECT * from tbl_clientes
where id_cliente = p_id;


END