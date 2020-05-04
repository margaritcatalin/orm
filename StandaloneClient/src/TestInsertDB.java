import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.example.myfirstejb.FirstStatelesEJBRemote;

public class TestInsertDB {

	public static void main(String[] args) {
		try {
			InitialContext context = new InitialContext();
			FirstStatelesEJBRemote firstEjb = (FirstStatelesEJBRemote) context.lookup("java:global/MyFirstEAR/MySecondEJB/FirstStatelesEJB!com.example.myfirstejb.FirstStatelesEJBRemote");
			firstEjb.insert("Test2 Test");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}
}
