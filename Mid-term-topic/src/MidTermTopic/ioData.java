package MidTermTopic;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ioData {

	public static ArrayList<String[]> getInfo() throws Exception {
		
		File file = new File("C:/Users/User/Desktop/mid/2020Q4.xls");
		ArrayList<String[]> stockInfo = new ArrayList<String[]>();
		//工作表
		Workbook workbook = WorkbookFactory.create(file);
		
		//查資料
					
		Sheet sheet =  workbook.getSheetAt(0);			//使用第0個sheet
		
		//行數
		int rowNumbers = sheet.getLastRowNum() + 1;		//取得列的資料
		
		//excel第一行
		Row temp = sheet.getRow(0);									
				
		int cells = temp.getPhysicalNumberOfCells();	//取得有值的行
		String [] data = new String [22];				//因資料欄位為22個
		//讀資料
		for (int row = 0; row < rowNumbers; row++) { 	//讀取row 總數(橫列)
			data = new String [22];						//每次進來都須新增一個新的記憶體空間不然永遠都只串改第一筆的數值
			Row r = sheet.getRow(row);							
			if (r == null) {							//若Row r為空值表示無資料直接跳過
				continue;
			}
			int i = 0;							
			for (int col = 0; col < cells; col++) {		//讀取有效欄位(直行)
				if (r.getCell(col) == null ||			//判斷當(直行)讀取到的值若為null或"" 塞入一個""避免資料跑版
					r.getCell(col).equals("")) {		//塞入""後往回做判斷
					data[i] = "";
					i++;
					continue;
				}else {
					data[i] = r.getCell(col).toString();//若資料非null或""就把資料直接丟入
					i++;
				}
			}
			if(data[0].length() > 3) {					//過濾不相關資料 判斷資料長度不足3的資料不列入新增
				stockInfo.add(data);
			}else {
				continue;
			}
		}
		return stockInfo;
			
	}	

}
