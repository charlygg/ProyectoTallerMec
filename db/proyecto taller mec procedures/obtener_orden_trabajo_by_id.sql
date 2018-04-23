CREATE DEFINER=`root`@`localhost` PROCEDURE `obtener_orden_trabajo_by_id`(IN  p_id_orden_trabajo  INT)
BEGIN

IF p_id_orden_trabajo = -2 THEN
  select * from tbl_orden_trabajo;
ELSE
  select * from tbl_orden_trabajo
  where id_orden_trabajo = p_id_orden_trabajo;
END IF;
END