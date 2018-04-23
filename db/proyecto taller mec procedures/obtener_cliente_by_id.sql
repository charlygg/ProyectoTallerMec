CREATE DEFINER=`root`@`localhost` PROCEDURE `obtener_cliente_by_id`(p_id	int)
BEGIN
-- Si es cliente "-2" traer todos los clientes
IF p_id = -2 then
SELECT * from tbl_clientes where id_cliente <> -1;
ELSE
SELECT * from tbl_clientes
where id_cliente = p_id;
END IF;
END