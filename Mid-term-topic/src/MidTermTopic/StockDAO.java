package MidTermTopic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSourceType;


public class StockDAO {

	private Connection conn;
	
	
	public StockDAO() {
	}
	//連線至資料庫
	public Connection createSQLserverConnection() {
		String sqlStr = "jdbc:sqlserver://localhost\\SQLEXPRESS01:1433;"+
						"databaseName = MidStock;"+
						"user = sa;"+
						"password = qwe74159";
		try {
			conn = DriverManager.getConnection(sqlStr);
			System.out.println("connect Success!!!");
		} catch (SQLException e) {
			System.out.println("Connect Something Wrong");
			System.out.println(e.getMessage());
		}
		return conn;
	}
	//關閉資料庫連線
	public void closeSQLserverConnection(){
		
		if (conn != null ) {
			try {
				conn.close();
				System.out.println("close finish!!!");
			} catch (SQLException e) {
				System.out.println("disconnect Something Wrong");
				e.printStackTrace();
			}
		}		
	}
	//新增資料庫資料
	public void addDataToSQL(Stock s) throws SQLException {
		String sqlStr = "INSERT INTO stockImfromation(Company,Company_code,year_Revenue,Last_year_Revenue,Revenue_IncreaseOrReduce,Profit_Loss_After_Tax,Last_year_Profit_Loss_After_Tax,Profit_Loss_After_Tax_IncreaseOrReduce,EPS)"+
						"VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement prestate1 = conn.prepareStatement(sqlStr);
		prestate1.setString(1, s.getCompany());	
		prestate1.setInt(2, s.getCompanyCode());
		prestate1.setDouble(3, s.getYearRevenue());
		prestate1.setDouble(4, s.getLastYearRevenue());
		prestate1.setDouble(5, s.getRevenueIncreaseOrReduce());
		prestate1.setDouble(6, s.getProfitLossAfterTax());
		prestate1.setDouble(7, s.getLastYearProfitLossAfterTax());
		prestate1.setString(8, s.getProfitLossAfterTaxIncreaseOrReduce());
		prestate1.setDouble(9, s.getEPS());		
		prestate1.execute();
		prestate1.close();						
	}
	//修改公司名稱資料
	public void updateDataToSQL(Map<String, String> mapFeature,int updateComCode){
		Set<String> keySet = mapFeature.keySet();
		try {
			for (String key :keySet) {
//				System.out.println(mapFeature.get(key));
				if (key.equals("company")) {					
					String sqlStr = "UPDATE stockImfromation SET "+ key + "='" + mapFeature.get(key) + "' WHERE Company_Code = "+ updateComCode;
					Statement statement = conn.createStatement();
					statement.execute(sqlStr);
					statement.close();
				}else {
					double getvalue = Double.parseDouble(mapFeature.get(key));
					String sqlStr = "UPDATE stockImfromation SET "+ key + "=" + getvalue + " WHERE Company_Code = "+ updateComCode;					
					Statement statement = conn.createStatement();
					statement.execute(sqlStr);
					statement.close();				
				}
			}
		} catch (SQLException e) {
			System.out.println("修改失敗");
			e.printStackTrace();
		}
	}	

	//刪除資料方法 用company_code
	public void deleteDataByCompanyCode(int CompanyCode) {
		String sqlStr = "Delete FROM stockImfromation WHERE company_code = ?";
		try {
			PreparedStatement prestate2 = conn.prepareStatement(sqlStr);
			prestate2.setInt(1, CompanyCode);
			prestate2.execute();
			prestate2.close();			
		} catch (SQLException e) {
			System.out.println("刪除資料錯誤");
			System.out.println(e.getMessage());
		}		
	}
	//查詢 用company_code 全部資料
	public Stock queryByCompanyCode(int company_code) throws SQLException {
		String sqlStr = "SELECT * FROM stockImfromation WHERE company_code = ?";
		PreparedStatement prestate3 = conn.prepareStatement(sqlStr);
		prestate3.setInt(1, company_code);
		ResultSet rs = prestate3.executeQuery();
		
		Stock s = null;
		if (rs.next()) {
			s = new Stock();
			s.setCompany(rs.getString("Company"));
			s.setCompanyCode(rs.getInt("Company_code"));
			s.setYearRevenue(rs.getDouble("year_Revenue"));
			s.setLastYearRevenue(rs.getDouble("last_year_Revenue"));
			s.setRevenueIncreaseOrReduce(rs.getDouble("Revenue_IncreaseOrReduce"));
			s.setProfitLossAfterTax(rs.getDouble("profit_Loss_After_Tax"));
			s.setLastYearProfitLossAfterTax(rs.getDouble("last_year_Profit_Loss_After_Tax"));
			s.setProfitLossAfterTaxIncreaseOrReduce(rs.getString("profit_Loss_After_Tax_IncreaseOrReduce"));
			s.setEPS(rs.getDouble("EPS"));
		}
		rs.close();
		prestate3.close();
		return s;
	}
//	依股票代碼查詢並將想要查詢的項目顯示
//	public Stock querySelectItem() throws SQLException {
//		String sqlStr = "SELECT * FROM stockImfromation WHERE company_code = ? ";
//		PreparedStatement prestate4 = conn.prepareStatement(sqlStr);
//		ResultSet rs = prestate4.executeQuery();
//		Scanner selectItem = new Scanner(System.in);
//		int select = selectItem.nextInt();
//		System.out.println("請輸入您要的項目:\n1:company\n2:company_code\n3:year_Revenue\n4:last_year_Revenue\n5:Revenue_IncreaseOrReduce\n6:profit_Loss_After_Tax\n7:last_year_Profit_Loss_After_Tax\n8:profit_Loss_After_Tax_IncreaseOrReduce\n9:EPS");
//		Stock s =null;
//		while(rs.next()) {
//			
//			s = new Stock();
//			
//			
//			
//		}
				
	

}	
	
	
	
	
	

