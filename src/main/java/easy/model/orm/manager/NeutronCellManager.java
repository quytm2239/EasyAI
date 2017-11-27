package easy.model.orm.manager;

import java.util.List;

import easy.model.orm.entity.NeuronCellPo;

public interface NeutronCellManager {
	void insert(NeuronCellPo neuronCellPo);
	NeuronCellPo get(long id);
	NeuronCellPo get(String name);
	List<NeuronCellPo> getAll();
	void update(NeuronCellPo neuronCellPo);
	void delete(long id);
	void delete(String name);
}
