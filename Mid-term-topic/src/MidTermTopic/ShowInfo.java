package MidTermTopic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hpsf.CodePageString;

public class ShowInfo {
	
	public String getTotalFeature() {
		String res = "";
		ArrayList<String> selectItem = getFeatureArray();
		for (int i = 0; i < selectItem.size(); i++) {
			res += ((i+1) + ":"+ selectItem.get(i) + "\n");
		}
		return res;
	}
	public ArrayList<String> transferFeatureNumToFeature(ArrayList<String> numArray){
		ArrayList<String> selectItem = getFeatureArray();
		ArrayList<String> featureArray = new ArrayList<String>();
		for(String feature : numArray) {
			int featureNum = Integer.parseInt(feature)-1;
			String item = selectItem.get(featureNum);
			featureArray.add(item);
		}		
		return featureArray ;
	}
	public ArrayList<String> getFeatureArray(){
		ArrayList<String> selectItem = new ArrayList<String>();
		selectItem.add("company");			
		selectItem.add("year_Revenue");		
		selectItem.add("last_year_Revenue");		
		selectItem.add("Revenue_IncreaseOrReduce");		
		selectItem.add("profit_Loss_After_Tax");		
		selectItem.add("last_year_Profit_Loss_After_Tax");		
		selectItem.add("profit_Loss_After_Tax_IncreaseOrReduce");
		selectItem.add("EPS");
		return selectItem;
	}
	public Map<String, String> getChinese(){
		Map<String, String> mapName = new HashMap<String, String>();
		mapName.put("company", "公司名稱");
		mapName.put("company_code", "股票代號");
		mapName.put("year_Revenue", "營業收入");
		mapName.put("last_year_Revenue", "去年營業收入");
		mapName.put("Revenue_IncreaseOrReduce", "營業收入成長");
		mapName.put("profit_Loss_After_Tax", "稅後淨利");
		mapName.put("last_year_Profit_Loss_After_Tax", "去年稅後淨利");
		mapName.put("profit_Loss_After_Tax_IncreaseOrReduce", "稅後淨利成長");		
		mapName.put("EPS", "每股盈餘");
		return mapName;
	}
	public ArrayList<String> getInsertFeatureArray(){
		ArrayList<String> selectItem = new ArrayList<String>();
		selectItem.add("company");		
		selectItem.add("company_code");		
		selectItem.add("year_Revenue");		
		selectItem.add("last_year_Revenue");		
		selectItem.add("Revenue_IncreaseOrReduce");		
		selectItem.add("profit_Loss_After_Tax");		
		selectItem.add("last_year_Profit_Loss_After_Tax");		
		selectItem.add("profit_Loss_After_Tax_IncreaseOrReduce");
		selectItem.add("EPS");
		return selectItem;
	}	
	public String getTotalFeature_query() {
		String res = "";
		ArrayList<String> selectItem = getInsertFeatureArray();
		for (int i = 0; i < selectItem.size(); i++) {
			res += ((i+1) + ":"+ selectItem.get(i) + "\n");
		}
		return res;
	}
	public ArrayList<String> transferFeatureNumToFeature_query(ArrayList<String> numArray){
		ArrayList<String> selectItem = getInsertFeatureArray();
		ArrayList<String> featureArray = new ArrayList<String>();
		for(String feature : numArray) {
			int featureNum = Integer.parseInt(feature)-1;
			String item = selectItem.get(featureNum);
			featureArray.add(item);
		}		
		return featureArray ;
	}
	public String chineseFeature_query() {
		String res = "";
		ArrayList<String> selectItem = getchinsesFeatureArray();
		for (int i = 0; i < selectItem.size(); i++) {
			res += ((i+1) + ":"+ selectItem.get(i) + "\n");
		}
		return res;
	}
	public ArrayList<String> getchinsesFeatureArray(){
		ArrayList<String> selectItem = new ArrayList<String>();
		selectItem.add("公司名稱");		
		selectItem.add("股票代號");		
		selectItem.add("營業收入");		
		selectItem.add("去年營業收入");		
		selectItem.add("營業收入成長");		
		selectItem.add("稅後淨利");		
		selectItem.add("去年稅後淨利");		
		selectItem.add("稅後淨利成長");
		selectItem.add("EPS");
		return selectItem;
	}
	public String chineseFeature_update() {
		String res = "";
		ArrayList<String> selectItem = getchinsesFeatureArray_update();
		for (int i = 0; i < selectItem.size(); i++) {
			res += ((i+1) + ":"+ selectItem.get(i) + "\n");
		}
		return res;
	}
	public ArrayList<String> transferFeatureNumToFeature_update(ArrayList<String> numArray){
		ArrayList<String> selectItem = getchinsesFeatureArray_update();
		ArrayList<String> featureArray = new ArrayList<String>();
		for(String feature : numArray) {
			int featureNum = Integer.parseInt(feature)-1;
			String item = selectItem.get(featureNum);
			featureArray.add(item);
		}		
		return featureArray ;
	}
	
	public ArrayList<String> getchinsesFeatureArray_update(){
		ArrayList<String> selectItem = new ArrayList<String>();
		selectItem.add("公司名稱");				
		selectItem.add("營業收入");		
		selectItem.add("去年營業收入");		
		selectItem.add("營業收入成長");		
		selectItem.add("稅後淨利");		
		selectItem.add("去年稅後淨利");		
		selectItem.add("稅後淨利成長");
		selectItem.add("EPS");
		return selectItem;
	}
	
}
