package MidTermTopic;

import java.io.Serializable;

public class Stock implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	private String company;                 				//公司名稱
	private int company_code;								//股票代碼
	private double year_Revenue;								//今年營收
	private double last_year_Revenue;							//去年營收
	private double Revenue_IncreaseOrReduce;				//營收相對去年增長率
	private double profit_Loss_After_Tax;						//今年稅後淨利
	private double last_year_Profit_Loss_After_Tax; 			//去年稅後淨利
	private String profit_Loss_After_Tax_IncreaseOrReduce;	//稅後淨利相對去年增長率					
	private double EPS;										//每股盈餘
//	private double PE_Ratio;								//本益比
//	private double Dividend_yield;							//殖利率
	
	public Stock() {
	}
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getCompanyCode() {
		return company_code;
	}
	public void setCompanyCode(int company_code) {
	 	this.company_code = company_code;
	}
	public double getYearRevenue() {
		return year_Revenue;
	}
	public void setYearRevenue(double year_Revenue) {
		this.year_Revenue = year_Revenue;
	}
	public double getLastYearRevenue() {
		return last_year_Revenue;
	}
	public void setLastYearRevenue(double last_year_Revenue) {
		this.last_year_Revenue = last_year_Revenue;
	}
	public double getRevenueIncreaseOrReduce() {
		return Revenue_IncreaseOrReduce;
	}
	public void setRevenueIncreaseOrReduce(double Revenue_IncreaseOrReduce) {
		this.Revenue_IncreaseOrReduce = Revenue_IncreaseOrReduce;
	}
	public double getProfitLossAfterTax() {
		return profit_Loss_After_Tax;
	}
	public void setProfitLossAfterTax(double profit_Loss_After_Tax) {
		this.profit_Loss_After_Tax = profit_Loss_After_Tax;
	}
	public double getLastYearProfitLossAfterTax() {
		return last_year_Profit_Loss_After_Tax;
	}
	public void setLastYearProfitLossAfterTax(double last_year_Profit_Loss_After_Tax) {
		this.last_year_Profit_Loss_After_Tax = last_year_Profit_Loss_After_Tax;
	}
	public String getProfitLossAfterTaxIncreaseOrReduce() {
		return profit_Loss_After_Tax_IncreaseOrReduce;
	}
	public void setProfitLossAfterTaxIncreaseOrReduce(String profit_Loss_After_Tax_IncreaseOrReduce) {
		this.profit_Loss_After_Tax_IncreaseOrReduce = profit_Loss_After_Tax_IncreaseOrReduce;
	}

	public double getEPS() {
		return EPS;
	}
	public void setEPS(double ePS) {
		this.EPS = ePS;
	}
	
}
