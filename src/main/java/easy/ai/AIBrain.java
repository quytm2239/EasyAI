package easy.ai;

import java.util.ArrayList;

import easy.model.orm.entity.DataUnitPo;

public class AIBrain {
	private ArrayList<BasicSentence> temporarySentences = null;  
	
	public void awake() {
		GrammarContainer.getInstance().loadData();
		WordContainer.getInstance().loadData();
		temporarySentences = new ArrayList<BasicSentence>();
	}
	
	public void scan(String inputSentence) {
		
	}
	
	public void scanToBasicSentences(String inputSentence) {

	}
	
	private ArrayList<DataUnitPo> scanToGetDataUnit(ArrayList<DataUnitPo> listWord) {
		ArrayList<DataUnitPo> result = new ArrayList<DataUnitPo>();
		
		
		return result;
	}
}
