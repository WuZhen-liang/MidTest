package MidTermTopic;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

import javax.print.CancelablePrintJob;

import org.apache.logging.log4j.core.appender.rolling.action.IfAccumulatedFileCount;
import org.apache.logging.log4j.core.util.WatcherFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.formula.functions.Count;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSourceType;

public class ioDataInportData {
	 	
			
	public static void main(String[] args) throws Exception {
		Stock stock;
		StockDAO stockDAO = StockDAOFactory.createstockDAO();
		ioData ioData = new ioData();
		ArrayList<String[]> info = ioData.getInfo();
		stockDAO.createSQLserverConnection();                  //啟動資料庫連線
		for (int i = 0 ; i < info.size(); i++) {			   //取得總資料筆數
			stock = new Stock();
			//設定公司名稱
			stock.setCompany(info.get(i)[1]);					
			//設定股票代碼
			int companyCode = Integer.parseInt(info.get(i)[0]);
			stock.setCompanyCode(companyCode);
			//設定收入
			BigDecimal changeYearRevenue = new BigDecimal(info.get(i)[2]);  //解決excel數字過大而導致傳入資料不正確
			stock.setYearRevenue(changeYearRevenue.doubleValue());	
			//設定去年收入
			BigDecimal changeLastYearRevenue = new BigDecimal(info.get(i)[3]);  //解決excel數字過大而導致傳入資料不正確
			stock.setLastYearRevenue(changeLastYearRevenue.doubleValue());	
			//設定去年與今年的收入變化
			if (info.get(i)[4] != "") {
				double revenueIncreaseOrReduce = Double.parseDouble(info.get(i)[4]);
				stock.setRevenueIncreaseOrReduce(revenueIncreaseOrReduce);					
			}else {
				stock.setProfitLossAfterTaxIncreaseOrReduce("無提供資料");
			}
			//設定稅後總額
			BigDecimal changeProfitLossAfterTax = new BigDecimal(info.get(i)[9]);
			stock.setProfitLossAfterTax(changeProfitLossAfterTax.doubleValue());		
			//設定去年稅後總額
			BigDecimal changeLastYearProfitLossAfterTax = new BigDecimal(info.get(i)[10]);
			stock.setLastYearProfitLossAfterTax(changeLastYearProfitLossAfterTax.doubleValue());
			//設定去年與今年稅後總額的變化
			if (info.get(i)[11] != "") {
				stock.setProfitLossAfterTaxIncreaseOrReduce(info.get(i)[11]);
			}else {
				stock.setProfitLossAfterTaxIncreaseOrReduce("無提供資料");
			}
			//設定EPS
			double EPS = Double.parseDouble(info.get(i)[13]);
			stock.setEPS(EPS);
			//把資料匯入
			stockDAO.addDataToSQL(stock);
		}
		stockDAO.closeSQLserverConnection();		
	}
}
