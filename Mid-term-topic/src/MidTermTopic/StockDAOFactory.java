package MidTermTopic;

public class StockDAOFactory {

	
	public static StockDAO createstockDAO() {
		StockDAO stockDAO = new StockDAO();
		return stockDAO;
	}
	
}
