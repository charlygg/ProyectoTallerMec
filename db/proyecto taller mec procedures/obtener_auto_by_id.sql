CREATE DEFINER=`root`@`localhost` PROCEDURE `obtener_auto_by_id`(p_id	int)
BEGIN

SELECT * from tbl_auto
where id_auto = p_id;


END