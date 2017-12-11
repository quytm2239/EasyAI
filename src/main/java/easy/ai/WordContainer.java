package easy.ai;

import java.util.ArrayList;

import easy.model.orm.entity.DataUnitPo;

public class WordContainer {
	 private ArrayList<DataUnitPo> listNoun;
	 private ArrayList<DataUnitPo> listVerb;
	 private ArrayList<DataUnitPo> listAdjective;
	 private ArrayList<DataUnitPo> listAdverb;
	 private ArrayList<DataUnitPo> listPronoun;
	 private ArrayList<DataUnitPo> listPreposition;
	 private ArrayList<DataUnitPo> listNumberic;
	 private ArrayList<DataUnitPo> listAmount;
	 private ArrayList<DataUnitPo> listReference;
	 private ArrayList<DataUnitPo> listImpressive;
	 private ArrayList<DataUnitPo> listEmotion;
	 private ArrayList<DataUnitPo> listStatusEmotion;
	 private ArrayList<DataUnitPo> listRelation;
	 
	 private WordContainer() {}	 
	 private static WordContainer instance = null;	 
	 public static WordContainer getInstance() {
		 if (instance == null) {
			 instance = new WordContainer();
		 }
		 return instance;
	 }
	 
	 public void loadData() {
		System.out.println("Start load all words!");
		
	 }

	public ArrayList<DataUnitPo> getListNoun() {
		return listNoun;
	}

	public void setListNoun(ArrayList<DataUnitPo> listNoun) {
		this.listNoun = listNoun;
	}

	public ArrayList<DataUnitPo> getListVerb() {
		return listVerb;
	}

	public void setListVerb(ArrayList<DataUnitPo> listVerb) {
		this.listVerb = listVerb;
	}

	public ArrayList<DataUnitPo> getListAdjective() {
		return listAdjective;
	}

	public void setListAdjective(ArrayList<DataUnitPo> listAdjective) {
		this.listAdjective = listAdjective;
	}

	public ArrayList<DataUnitPo> getListAdverb() {
		return listAdverb;
	}

	public void setListAdverb(ArrayList<DataUnitPo> listAdverb) {
		this.listAdverb = listAdverb;
	}

	public ArrayList<DataUnitPo> getListPronoun() {
		return listPronoun;
	}

	public void setListPronoun(ArrayList<DataUnitPo> listPronoun) {
		this.listPronoun = listPronoun;
	}

	public ArrayList<DataUnitPo> getListPreposition() {
		return listPreposition;
	}

	public void setListPreposition(ArrayList<DataUnitPo> listPreposition) {
		this.listPreposition = listPreposition;
	}

	public ArrayList<DataUnitPo> getListNumberic() {
		return listNumberic;
	}

	public void setListNumberic(ArrayList<DataUnitPo> listNumberic) {
		this.listNumberic = listNumberic;
	}

	public ArrayList<DataUnitPo> getListAmount() {
		return listAmount;
	}

	public void setListAmount(ArrayList<DataUnitPo> listAmount) {
		this.listAmount = listAmount;
	}

	public ArrayList<DataUnitPo> getListReference() {
		return listReference;
	}

	public void setListReference(ArrayList<DataUnitPo> listReference) {
		this.listReference = listReference;
	}

	public ArrayList<DataUnitPo> getListImpressive() {
		return listImpressive;
	}

	public void setListImpressive(ArrayList<DataUnitPo> listImpressive) {
		this.listImpressive = listImpressive;
	}

	public ArrayList<DataUnitPo> getListEmotion() {
		return listEmotion;
	}

	public void setListEmotion(ArrayList<DataUnitPo> listEmotion) {
		this.listEmotion = listEmotion;
	}

	public ArrayList<DataUnitPo> getListStatusEmotion() {
		return listStatusEmotion;
	}

	public void setListStatusEmotion(ArrayList<DataUnitPo> listStatusEmotion) {
		this.listStatusEmotion = listStatusEmotion;
	}

	public ArrayList<DataUnitPo> getListRelation() {
		return listRelation;
	}

	public void setListRelation(ArrayList<DataUnitPo> listRelation) {
		this.listRelation = listRelation;
	}
	 
}
