package easy.ai.orm.manager;

import easy.ai.orm.entity.NeuronCellPo;

import java.util.List;

public interface NeutronCellManager {
	void insert(NeuronCellPo neuronCellPo);
	NeuronCellPo get(long id);
	NeuronCellPo get(String name);
	List<NeuronCellPo> getAll();
	void update(NeuronCellPo neuronCellPo);
	void delete(long id);
	void delete(String name);
}
