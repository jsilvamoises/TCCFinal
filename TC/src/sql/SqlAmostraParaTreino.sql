--DELETE FROM tbl_equipamento
INSERT INTO tbl_equipamento(`idEquipamento`,`valorSensorReferencia`, `statusEquipamento`) 
VALUES
--ILUMINACAO
(0.100,32.0,1.0),
(0.100,33.0,1.0),
(0.100,35.0,1.0),
(0.100,37.0,1.0),
(0.100,38.0,1.0),

(0.100,50.0,0.0),
(0.100,53.0,0.0),
(0.100,54.0,0.0),
(0.100,58.0,0.0),
(0.100,61.0,0.0),
-- ARCONDICIONADO
(0.200,49.0,1.0),
(0.200,43.0,1.0),
(0.200,39.0,1.0),
(0.200,35.0,1.0),
(0.200,27.0,1.0),

(0.200,16.0,0.0),
(0.200,18.0,0.0),
(0.200,15.0,0.0),
(0.200,20.0,0.0),
(0.200,23.0,0.0),
-- AQUECEDOR
(0.300,13.0,1.0),
(0.300,10.0,1.0),
(0.300,15.0,1.0),
(0.300,12.0,1.0),
(0.300,18.0,1.0),

(0.300,35.0,0.0),
(0.300,32.0,0.0),
(0.300,29.0,0.0),
(0.300,29.0,0.0),
(0.300,26.0,0.0),
--UMIDIFICADOR
(0.400,32.0,1.0),
(0.400,25.0,1.0),
(0.400,30.0,1.0),
(0.400,49.0,1.0),
(0.400,52.0,1.0),

(0.400,59.0,0.0),
(0.400,65.0,0.0),
(0.400,60.0,0.0),
(0.400,63.0,0.0),
(0.400,61.0,0.0);
select * FROM tbl_equipamento