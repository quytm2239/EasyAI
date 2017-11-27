package easy.model.orm.manager.impl;

import java.util.List;

import easy.model.orm.entity.NeuronCellPo;
import easy.model.orm.manager.NeutronCellManager;

public class NeutronCellManagerImpl implements NeutronCellManager{

	@Override
	public void insert(NeuronCellPo neuronCellPo) {
		// TODO: 11/20/2017  
	}

	@Override
	public NeuronCellPo get(long id) {
		return new NeuronCellPo();
	}

	@Override
	public NeuronCellPo get(String name) {
		return null;
	}

	@Override
	public List<NeuronCellPo> getAll() {
		return null;
	}

	@Override
	public void update(NeuronCellPo neuronCellPo) {

	}

	@Override
	public void delete(long id) {

	}

	@Override
	public void delete(String name) {

	}
}
