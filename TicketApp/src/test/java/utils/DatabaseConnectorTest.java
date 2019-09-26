package utils;

import java.sql.Connection;
import org.junit.Test;
import junit.framework.Assert;
import junit.framework.TestCase;

public class DatabaseConnectorTest extends TestCase {
	
	@Test
	public void testIfConnectorEstablishesConnection() {
		DatabaseConnector connector = new DatabaseConnector();
		Connection connection = connector.sqlConnection();
		Assert.assertTrue(connection != null);
	}
}
