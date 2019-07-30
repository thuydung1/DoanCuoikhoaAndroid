select Tenthucan
from ThucAn,LoaiThucAn,JOIN2BANG
where ThucAn.id=JOIN2BANG.intthucan and JOIN2BANG.idloai=LoaiThucAn.id and LoaiThucAn.tenloai='abc'