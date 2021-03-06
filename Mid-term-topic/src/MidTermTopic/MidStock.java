package MidTermTopic;

import java.io.FileWriter;
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
		stockDAO.createSQLserverConnection();					//呼叫資料庫連線
		ShowInfo showInfo = new ShowInfo();
		ArrayList<String> outputData =new ArrayList();	//要寫入的輸出至檔案的ArrayList
		FileWriter writer = new FileWriter("C:/Users/User/Desktop/mid/test.csv");
		Scanner scannerString = new Scanner(System.in);
		Scanner scannerInt = new Scanner(System.in);    //若在程式中央將Scanner關閉，會同時把system.in關閉 所以等無需使用scanner時再一次關閉
		Scanner scannerDouble = new Scanner(System.in);  
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
					//輸入公司名稱
					System.out.print(showInfo.getchinsesFeatureArray().get(0)+":");
					String  company = scannerString.nextLine().trim();
					newStock.setCompany(company);
					//輸入公司股票代號
					System.out.print(showInfo.getchinsesFeatureArray().get(1)+":");
					int  companyCode = scannerInt.nextInt();
					newStock.setCompanyCode(companyCode);
					//判斷此股票代碼是否已經存在
					Stock check = stockDAO.queryByCompanyCode(companyCode);
					if (check != null) {
						System.out.println("已有此股票代號!");
						continue;
					}
					outputData.add("項目,新增");
					//輸入公司營業收入					
					outputData.add(showInfo.getchinsesFeatureArray().get(0)+","+newStock.getCompany());
					outputData.add(showInfo.getchinsesFeatureArray().get(1)+","+newStock.getCompanyCode());
					System.out.print(showInfo.getchinsesFeatureArray().get(2)+":");
					Double  yearRevenue = scannerDouble.nextDouble();
					newStock.setYearRevenue(yearRevenue);
					outputData.add(showInfo.getchinsesFeatureArray().get(2)+","+ newStock.getYearRevenue());
					//輸入公司去年營業收入
					System.out.print(showInfo.getchinsesFeatureArray().get(3)+":");
					Double  lastYearRevenue = scannerDouble.nextDouble();
					newStock.setLastYearRevenue(lastYearRevenue);
					outputData.add(showInfo.getchinsesFeatureArray().get(3)+","+newStock.getLastYearRevenue());
					//輸入營業收入與去年同比增減
					System.out.print(showInfo.getchinsesFeatureArray().get(4)+":");
					Double  revenueIncreaseOrReduce = scannerDouble.nextDouble();
					newStock.setRevenueIncreaseOrReduce(revenueIncreaseOrReduce);
					outputData.add(showInfo.getchinsesFeatureArray().get(4)+","+newStock.getRevenueIncreaseOrReduce());
					//輸入公司稅後淨利
					System.out.print(showInfo.getchinsesFeatureArray().get(5)+":");
					Double profitLossAfterTax = scannerDouble.nextDouble();
					newStock.setProfitLossAfterTax(profitLossAfterTax);
					outputData.add(showInfo.getchinsesFeatureArray().get(5)+","+newStock.getProfitLossAfterTax());
					//輸入公司去年稅後淨利
					System.out.print(showInfo.getchinsesFeatureArray().get(6)+":");
					Double lastYearProfitLossAfterTax = scannerDouble.nextDouble();
					newStock.setLastYearProfitLossAfterTax(lastYearProfitLossAfterTax);
					outputData.add(showInfo.getchinsesFeatureArray().get(6)+","+newStock.getLastYearProfitLossAfterTax());
					//輸入公司稅後淨利與去年同比增減
					System.out.print(showInfo.getchinsesFeatureArray().get(7)+":");
					String profitLossAfterTaxIncreaseOrReduce = scannerString.nextLine().trim();
					newStock.setProfitLossAfterTaxIncreaseOrReduce(profitLossAfterTaxIncreaseOrReduce);
					outputData.add(showInfo.getchinsesFeatureArray().get(7)+","+newStock.getProfitLossAfterTaxIncreaseOrReduce());
					//輸入公司每股盈餘(EPS)
					System.out.print(showInfo.getchinsesFeatureArray().get(8)+":");
					Double EPS = scannerDouble.nextDouble();
					newStock.setEPS(EPS);
					outputData.add(showInfo.getchinsesFeatureArray().get(8)+","+newStock.getEPS());
					try {
						stockDAO.addDataToSQL(newStock);
						System.out.println("成功新增一筆資料");
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
						outputData.add("項目,查詢");
						System.out.println("請輸入你要查詢的是:\n1:ALL\n2:somedata");
						int selectForQuery = scannerInt.nextInt();
						if(selectForQuery == 1) {
							Map<String, String> chinese = showInfo.getChinese();
							System.out.printf("%s:%s%n%s:%d%n%s:%.1f%n%s:%.1f%n%s:%.1f%n%s:%.1f%n%s:%.1f%n%s:%s%n%s:%.1f%n",chinese.get("company"),result.getCompany(),chinese.get("company_code"),result.getCompanyCode(),chinese.get("year_Revenue"),result.getYearRevenue(),chinese.get("last_year_Revenue"),result.getLastYearRevenue(),chinese.get("Revenue_IncreaseOrReduce"),result.getRevenueIncreaseOrReduce(),chinese.get("profit_Loss_After_Tax"),result.getProfitLossAfterTax(),chinese.get("last_year_Profit_Loss_After_Tax"),result.getLastYearProfitLossAfterTax(),chinese.get("profit_Loss_After_Tax_IncreaseOrReduce"),result.getProfitLossAfterTaxIncreaseOrReduce(),chinese.get("EPS"),result.getEPS());						
							outputData.add(showInfo.getchinsesFeatureArray().get(0)+","+result.getCompany());
							outputData.add(showInfo.getchinsesFeatureArray().get(1)+","+result.getCompanyCode());
							outputData.add(showInfo.getchinsesFeatureArray().get(2)+","+result.getYearRevenue());
							outputData.add(showInfo.getchinsesFeatureArray().get(3)+","+result.getLastYearRevenue());
							outputData.add(showInfo.getchinsesFeatureArray().get(4)+","+result.getRevenueIncreaseOrReduce());
							outputData.add(showInfo.getchinsesFeatureArray().get(5)+","+result.getProfitLossAfterTax());
							outputData.add(showInfo.getchinsesFeatureArray().get(6)+","+result.getLastYearProfitLossAfterTax());
							outputData.add(showInfo.getchinsesFeatureArray().get(7)+","+result.getProfitLossAfterTaxIncreaseOrReduce());
							outputData.add(showInfo.getchinsesFeatureArray().get(8)+","+result.getEPS());							
						}else if(selectForQuery == 2){
							System.out.println("請輸入您要那些的項目:\n" + showInfo.chineseFeature_query());
							String itemOfdata = scannerString.nextLine().trim();
							ArrayList<String> featureNum = new ArrayList<String>(Arrays.asList(itemOfdata.split(",")));
							ArrayList<String> feature = showInfo.transferFeatureNumToFeature_query(featureNum);
							for(int i = 0; i < feature.size(); i++ ) {
								if (feature.get(i).equals("company")) {									
									System.out.printf("%s:%s%n",showInfo.getChinese().get(feature.get(i)),result.getCompany());
									outputData.add(showInfo.getchinsesFeatureArray().get(0)+","+result.getCompany());
								}else if(feature.get(i).equals("company_code")){
									System.out.printf("%s:%d%n",showInfo.getChinese().get(feature.get(i)),result.getCompanyCode());									
									outputData.add(showInfo.getchinsesFeatureArray().get(1)+","+result.getCompanyCode());
								}else if(feature.get(i).equals("year_Revenue")){
									System.out.printf("%s:%.1f%n",showInfo.getChinese().get(feature.get(i)),result.getYearRevenue());									
									outputData.add(showInfo.getchinsesFeatureArray().get(2)+","+result.getYearRevenue());
								}else if(feature.get(i).equals("last_year_Revenue")){
									System.out.printf("%s:%.1f%n",showInfo.getChinese().get(feature.get(i)),result.getLastYearRevenue());									
									outputData.add(showInfo.getchinsesFeatureArray().get(3)+","+result.getLastYearRevenue());
								}else if(feature.get(i).equals("Revenue_IncreaseOrReduce")){
									System.out.printf("%s:%.1f%n",showInfo.getChinese().get(feature.get(i)),result.getRevenueIncreaseOrReduce());									
									outputData.add(showInfo.getchinsesFeatureArray().get(4)+","+result.getRevenueIncreaseOrReduce());
								}else if(feature.get(i).equals("profit_Loss_After_Tax")){
									System.out.printf("%s:%.1f%n",showInfo.getChinese().get(feature.get(i)),result.getProfitLossAfterTax());									
									outputData.add(showInfo.getchinsesFeatureArray().get(5)+","+result.getProfitLossAfterTax());
								}else if(feature.get(i).equals("last_year_Profit_Loss_After_Tax")){
									System.out.printf("%s:%.1f%n",showInfo.getChinese().get(feature.get(i)),result.getLastYearProfitLossAfterTax());									
									outputData.add(showInfo.getchinsesFeatureArray().get(6)+","+result.getLastYearProfitLossAfterTax());
								}else if(feature.get(i).equals("profit_Loss_After_Tax_IncreaseOrReduce")){
									System.out.printf("%s:%s%n",showInfo.getChinese().get(feature.get(i)),result.getProfitLossAfterTaxIncreaseOrReduce());									
									outputData.add(showInfo.getchinsesFeatureArray().get(7)+","+result.getProfitLossAfterTaxIncreaseOrReduce());
								}else if(feature.get(i).equals("EPS")){
									System.out.printf("%s:%.1f%n",showInfo.getChinese().get(feature.get(i)),result.getEPS());									
									outputData.add(showInfo.getchinsesFeatureArray().get(8)+","+result.getEPS());
								}									
							}							
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
						outputData.add("項目,更新");
						System.out.println("您要更新的公司為:\r\n"+showInfo.getChinese().get("company")+":"+updateCompany.getCompany()+","+showInfo.getChinese().get("company_code")+":"+updateCompany.getCompanyCode());						
						System.out.println("請輸入您要修改的項目:\n" + showInfo.chineseFeature_update());
						String itemOfdata = scannerString.nextLine().trim();
						ArrayList<String> featureNum = new ArrayList<String>(Arrays.asList(itemOfdata.split(",")));
						
						ArrayList<String> feature = showInfo.transferFeatureNumToFeature(featureNum);  //回傳使用者輸入的項目
						Map<String, String> mapFeature = new HashMap<String, String>();
						
						for(int i = 0 ; i < feature.size();i++) {
							System.out.print(showInfo.getChinese().get(feature.get(i))+":");
							String userData  = scannerString.nextLine().trim();
							mapFeature.put(feature.get(i), userData);							
							outputData.add(showInfo.getChinese().get(feature.get(i))+","+ userData);   //輸出用
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
						System.out.println("您要刪除的公司為:\r\n"+showInfo.getChinese().get("company")+":"+deleteCompany.getCompany()+","+showInfo.getChinese().get("company_code")+":"+deleteCompany.getCompanyCode());
						System.out.println("請確認是否要刪除:Y / N");
						String checkDelete = scannerString.nextLine().toUpperCase().trim();  // 若使用者多打空白可以避免出錯 
						if(checkDelete .equals("Y")) {
							outputData.add("項目,刪除");
							
							outputData.add(showInfo.getchinsesFeatureArray().get(0)+","+deleteCompany.getCompany());
							outputData.add(showInfo.getchinsesFeatureArray().get(1)+","+deleteCompany.getCompanyCode());
							outputData.add(showInfo.getchinsesFeatureArray().get(2)+","+deleteCompany.getYearRevenue());
							outputData.add(showInfo.getchinsesFeatureArray().get(3)+","+deleteCompany.getLastYearRevenue());
							outputData.add(showInfo.getchinsesFeatureArray().get(4)+","+deleteCompany.getRevenueIncreaseOrReduce());
							outputData.add(showInfo.getchinsesFeatureArray().get(5)+","+deleteCompany.getProfitLossAfterTax());
							outputData.add(showInfo.getchinsesFeatureArray().get(6)+","+deleteCompany.getLastYearProfitLossAfterTax());
							outputData.add(showInfo.getchinsesFeatureArray().get(7)+","+deleteCompany.getProfitLossAfterTaxIncreaseOrReduce());
							outputData.add(showInfo.getchinsesFeatureArray().get(8)+","+deleteCompany.getEPS());							

							stockDAO.deleteDataByCompanyCode(deleteStockCode);
							System.out.println("確定刪除");
						}else {
							System.out.println("取消刪除");
							continue;
						}
					}
					break;
				default:
					System.out.println("請輸入正確指令");  //若使用者輸入C、R、U、D、EXIT以外的數值回傳
					continue;
			}		
		}
		System.out.println("請確認是否匯出檔案:Y / N");
		String check = scannerString.nextLine().toUpperCase().trim();
		if(check.equals("Y")) {
			System.out.println("輸出搂~~~~");
			for(int i = 0 ;i<outputData.size() ;i++) {	
				System.out.println(outputData.get(i));
				writer.write(outputData.get(i).toString());
				writer.write("\r\n");
			}			
		}else {
		}
		writer.close();
		scannerString.close();				//一次關閉scanner不然會將其中一個關閉的同時會把System.in關閉
		scannerInt.close();
		scannerDouble.close();
		stockDAO.closeSQLserverConnection();//關閉連線
	}
	

	

}
