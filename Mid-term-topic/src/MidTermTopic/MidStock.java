package MidTermTopic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.print.attribute.IntegerSyntax;

import org.apache.poi.hpsf.CodePageString;

public class MidStock {
	
	public static void main(String[] args) throws Exception {
		StockDAO stockDAO = StockDAOFactory.createstockDAO();
		stockDAO.createSQLserverConnection();
		ShowInfo showInfo = new ShowInfo();
		
		Scanner scannerString = new Scanner(System.in);
		Scanner scannerInt = new Scanner(System.in);    //若在程式中央將Scanner關閉，會同時把system.in關閉 所以等無需使用scanner時再一次關閉

		while(true) {
			System.out.printf("請輸入:%nC:新增%nR:查詢%nU:更新%nD:刪除%nEXIT:離開應用程式%n");
			String select = scannerString.nextLine().toUpperCase().trim();
			if(select.equals("EXIT") ) {
				break;
			}
			switch(select) {
				case "C":
					System.out.println("開始新增資料");
					Stock newStock = new Stock(); 
					newStock.setCompany("我家");
					newStock.setCompanyCode(12301);
					newStock.setYearRevenue(999999999);
					newStock.setLastYearRevenue(1234657);
					newStock.setRevenueIncreaseOrReduce(20);
					newStock.setProfitLossAfterTax(456);
					newStock.setLastYearProfitLossAfterTax(798);
					newStock.setProfitLossAfterTaxIncreaseOrReduce("654");
					newStock.setEPS(50.45);
					System.out.println("成功新增一筆資料");
					try {
						stockDAO.addDataToSQL(newStock);
					}catch (Exception e) {
						System.out.println("新增資料異常-->股票代號已存在");
					}
					break;
				case "R":
					System.out.println("開始查詢資料");
					System.out.println("請輸入你要查詢的股票代號:");
					int stockCode = scannerInt.nextInt();
					try {					
						Stock result = stockDAO.queryByCompanyCode(stockCode);
						if (result == null) {
							System.out.println("此股票代號不存在!");
							continue;
						}
						
						System.out.println("請輸入你要查詢的是:\n1:ALL\n2:somedata");
						int selectForQuery = scannerInt.nextInt();
						if(selectForQuery == 1) {
							Map<String, String> chinese = showInfo.getChinese();
							System.out.printf("%s:%s%n%s:%d%n%s:%.1f%n%s:%.1f%n%s:%.1f%n%s:%.1f%n%s:%.1f%n%s:%s%n%s:%.1f%n",chinese.get("company"),result.getCompany(),chinese.get("company_code"),result.getCompanyCode(),chinese.get("year_Revenue"),result.getYearRevenue(),chinese.get("last_year_Revenue"),result.getLastYearRevenue(),chinese.get("Revenue_IncreaseOrReduce"),result.getRevenueIncreaseOrReduce(),chinese.get("profit_Loss_After_Tax"),result.getProfitLossAfterTax(),chinese.get("last_year_Profit_Loss_After_Tax"),result.getLastYearProfitLossAfterTax(),chinese.get("profit_Loss_After_Tax_IncreaseOrReduce"),result.getProfitLossAfterTaxIncreaseOrReduce(),chinese.get("EPS"),result.getEPS());
						}else if(selectForQuery == 2){
							System.out.println("123");
						}
					}catch (Exception e) {
						e.printStackTrace();
					}					
					break;
				case "U":				
					System.out.println("開始更新資料");
					System.out.println("請輸入要更改的公司的股票代碼:");
					int updateComCode = scannerInt.nextInt();
					Stock updateCompany = stockDAO.queryByCompanyCode(updateComCode);
					if(updateCompany == null) {
						System.out.println("此股票代號不存在!");
					}else {
						
						System.out.println("請輸入您要修改的項目:\n" + showInfo.getTotalFeature());
						String itemOfdata = scannerString.nextLine();
						ArrayList<String> featureNum = new ArrayList<String>(Arrays.asList(itemOfdata.split(",")));
						
						ArrayList<String> feature = showInfo.transferFeatureNumToFeature(featureNum);
						Map<String, String> mapFeature = new HashMap<String, String>();
						
						for(int i = 0 ; i < feature.size();i++) {
							System.out.print(feature.get(i)+":");
							String userData  = scannerString.nextLine();
//							System.out.println();
							mapFeature.put(feature.get(i), userData);							
						}
						System.out.println("請確認是否要更改此項目:Y / N");
						String commitOrRollBack = scannerString.nextLine().toUpperCase().trim(); 
						if(commitOrRollBack .equals("Y")) {
							stockDAO.updateDataToSQL(mapFeature,updateComCode);
							System.out.println("更改完成!!!!");
						}else {
							System.out.println("更新取消");
							continue;
						}
						
					}
					break;
				case "D":
					System.out.println("開始刪除資料");
					System.out.println("請輸入要刪除的股票代號:");
					int deleteStockCode = scannerInt.nextInt();
					Stock deleteCompany = stockDAO.queryByCompanyCode(deleteStockCode);
					if (deleteCompany == null) {
						System.out.println("此股票代號不存在!");
						continue;
					}else {
						System.out.println("請確認是否要刪除:Y / N");
						String checkDelete = scannerString.nextLine().toUpperCase().trim(); 
						if(checkDelete .equals("Y")) {
							stockDAO.deleteDataByCompanyCode(deleteStockCode);
							System.out.println("確定刪除");
						}else {
							System.out.println("取消刪除");
							continue;
						}
					}
					break;
			}		
		}
		scannerString.close();
		scannerInt.close();

		stockDAO.closeSQLserverConnection();	//關閉連線
	}
	

	

}
