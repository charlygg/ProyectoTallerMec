CREATE DEFINER=`root`@`localhost` PROCEDURE `obtener_autos`(in p_id_cliente INT)
BEGIN

SELECT cli.id_cliente, cli.nombre, a.id_auto, a.modelo, a.marca, a.placas, a.color
FROM tbl_clientes cli
JOIN tbl_auto_cliente tac
on cli.id_cliente = tac.id_cliente
JOIN tbl_auto a
on a.id_auto = tac.id_auto
WHERE cli.id_cliente = p_id_cliente;

END