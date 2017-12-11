package easy.ai;

import java.util.ArrayList;

import easy.model.orm.entity.DataUnitPo;

public class GrammarContainer {
	 private ArrayList<DataUnitPo> listWordType;
	 private ArrayList<DataUnitPo> listWordCategory;
	 
	 private GrammarContainer() {}	 
	 private static GrammarContainer instance = null;	 
	 public static GrammarContainer getInstance() {
		 if (instance == null) {
			 instance = new GrammarContainer();
		 }
		 return instance;
	 }
	 
	 public void loadData() {
		System.out.println("Start load all grammar!");
		
	 }

	public ArrayList<DataUnitPo> getListWordType() {
		return listWordType;
	}

	public void setListWordType(ArrayList<DataUnitPo> listWordType) {
		this.listWordType = listWordType;
	}

	public ArrayList<DataUnitPo> getListWordCategory() {
		return listWordCategory;
	}

	public void setListWordCategory(ArrayList<DataUnitPo> listWordCategory) {
		this.listWordCategory = listWordCategory;
	}
	 
	 
}
