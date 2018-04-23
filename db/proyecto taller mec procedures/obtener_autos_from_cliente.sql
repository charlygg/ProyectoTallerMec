CREATE DEFINER=`root`@`localhost` PROCEDURE `obtener_autos_from_cliente`(in p_id_cliente INT)
BEGIN
-- Si es cliente "-2" traer todos los automoviles
IF p_id_cliente = -2 then

SELECT * 
from tbl_auto;

ELSE

SELECT * 
from tbl_auto where id_cliente = p_id_cliente;


END IF;

END