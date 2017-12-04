package easy.ai;

import java.util.ArrayList;

import easy.model.orm.entity.NeuronCellPo;

public class DataContainer {
	 private ArrayList<NeuronCellPo> listNoun;
	 private ArrayList<NeuronCellPo> listVerb;
	 private ArrayList<NeuronCellPo> listAdjective;
	 private ArrayList<NeuronCellPo> listAdverb;
	 private ArrayList<NeuronCellPo> listPronoun;
	 private ArrayList<NeuronCellPo> listPreposition;
	 private ArrayList<NeuronCellPo> listNumberic;
	 private ArrayList<NeuronCellPo> listAmount;
	 private ArrayList<NeuronCellPo> listReference;
	 private ArrayList<NeuronCellPo> listImpressive;
	 private ArrayList<NeuronCellPo> listEmotion;
	 private ArrayList<NeuronCellPo> listStatusEmotion;
	 private ArrayList<NeuronCellPo> listRelation;
	 
	 private DataContainer() {}	 
	 public static DataContainer instance = null;	 
	 public static DataContainer getInstance() {
		 if (instance == null) {
			 instance = new DataContainer();
		 }
		 return instance;
	 }
	 
	 public void loadData() {
		 
	 }

	public ArrayList<NeuronCellPo> getListNoun() {
		return listNoun;
	}

	public void setListNoun(ArrayList<NeuronCellPo> listNoun) {
		this.listNoun = listNoun;
	}

	public ArrayList<NeuronCellPo> getListVerb() {
		return listVerb;
	}

	public void setListVerb(ArrayList<NeuronCellPo> listVerb) {
		this.listVerb = listVerb;
	}

	public ArrayList<NeuronCellPo> getListAdjective() {
		return listAdjective;
	}

	public void setListAdjective(ArrayList<NeuronCellPo> listAdjective) {
		this.listAdjective = listAdjective;
	}

	public ArrayList<NeuronCellPo> getListAdverb() {
		return listAdverb;
	}

	public void setListAdverb(ArrayList<NeuronCellPo> listAdverb) {
		this.listAdverb = listAdverb;
	}

	public ArrayList<NeuronCellPo> getListPronoun() {
		return listPronoun;
	}

	public void setListPronoun(ArrayList<NeuronCellPo> listPronoun) {
		this.listPronoun = listPronoun;
	}

	public ArrayList<NeuronCellPo> getListPreposition() {
		return listPreposition;
	}

	public void setListPreposition(ArrayList<NeuronCellPo> listPreposition) {
		this.listPreposition = listPreposition;
	}

	public ArrayList<NeuronCellPo> getListNumberic() {
		return listNumberic;
	}

	public void setListNumberic(ArrayList<NeuronCellPo> listNumberic) {
		this.listNumberic = listNumberic;
	}

	public ArrayList<NeuronCellPo> getListAmount() {
		return listAmount;
	}

	public void setListAmount(ArrayList<NeuronCellPo> listAmount) {
		this.listAmount = listAmount;
	}

	public ArrayList<NeuronCellPo> getListReference() {
		return listReference;
	}

	public void setListReference(ArrayList<NeuronCellPo> listReference) {
		this.listReference = listReference;
	}

	public ArrayList<NeuronCellPo> getListImpressive() {
		return listImpressive;
	}

	public void setListImpressive(ArrayList<NeuronCellPo> listImpressive) {
		this.listImpressive = listImpressive;
	}

	public ArrayList<NeuronCellPo> getListEmotion() {
		return listEmotion;
	}

	public void setListEmotion(ArrayList<NeuronCellPo> listEmotion) {
		this.listEmotion = listEmotion;
	}

	public ArrayList<NeuronCellPo> getListStatusEmotion() {
		return listStatusEmotion;
	}

	public void setListStatusEmotion(ArrayList<NeuronCellPo> listStatusEmotion) {
		this.listStatusEmotion = listStatusEmotion;
	}

	public ArrayList<NeuronCellPo> getListRelation() {
		return listRelation;
	}

	public void setListRelation(ArrayList<NeuronCellPo> listRelation) {
		this.listRelation = listRelation;
	}

	public static void setInstance(DataContainer instance) {
		DataContainer.instance = instance;
	}
	 
}
