package MidTermTopic;

public class StockDAOAction {

	public static void main(String[] args) {
		
		StockDAO stockDAO = StockDAOFactory.createstockDAO();
		
		stockDAO.createSQLserverConnection();
		
		
		
		
		
		stockDAO.closeSQLserverConnection();
	}

}
