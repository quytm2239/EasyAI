package easy.model.orm.manager;

import java.util.List;

import easy.model.orm.entity.DataUnitPo;

public interface DataUnitManager {
	void insert(DataUnitPo dataUnitPo);
	DataUnitPo get(long id);
	DataUnitPo get(String name);
	List<DataUnitPo> getAll();
	void update(DataUnitPo dataUnitPo);
	void delete(long id);
	void delete(String name);
}
