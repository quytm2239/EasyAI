package easy.model.orm.manager.impl;

import java.util.List;

import easy.model.orm.entity.DataUnitPo;
import easy.model.orm.manager.DataUnitManager;

public class DataUnitManagerImpl implements DataUnitManager{

	@Override
	public void insert(DataUnitPo dataUnitPo) {
		// TODO: 11/20/2017  
	}

	@Override
	public DataUnitPo get(long id) {
		return new DataUnitPo();
	}

	@Override
	public DataUnitPo get(String name) {
		return null;
	}

	@Override
	public List<DataUnitPo> getAll() {
		return null;
	}

	@Override
	public void update(DataUnitPo dataUnitPo) {

	}

	@Override
	public void delete(long id) {

	}

	@Override
	public void delete(String name) {

	}
}
