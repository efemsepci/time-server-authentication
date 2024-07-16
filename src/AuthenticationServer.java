import java.util.ArrayList;
import java.util.List;

public class AuthenticationServer {
	
	EncryptDecrypt cryptoOperations = new EncryptDecrypt();
	
	private List<User> users;
	private List<String> tickets;
	
	public AuthenticationServer() throws Exception{
		users = new ArrayList<>();
		tickets = new ArrayList<>();
		
		users.add(new User("admin","test123"));
		users.add(new User("ahmet","test123"));
		users.add(new User("hasan","test123"));
		users.add(new User("fatma","test123"));
		users.add(new User("ayþe","test123"));
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<String> getTickets() {
		return tickets;
	}

	public void setTickets(List<String> tickets) {
		this.tickets = tickets;
	}

	public boolean authenticateUser(String username) {
		for(int i = 0; i<users.size(); i++) {
			if(users.get(i).getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkTicket(String ticket) throws Exception {
		for(int i = 0; i<tickets.size(); i++) {
			if(tickets.get(i).equals(ticket)) {
				String tmpPassword = cryptoOperations.stringDecryption(ticket);
				for(int j = 0; j<users.size(); j++) {
					if(users.get(i).getPassword().equals(tmpPassword)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public String createTicket(String username) throws Exception{
		String ticket = generateTicket(username);
		tickets.add(ticket);
		return ticket;
	}
	
	private String generateTicket(String username) throws Exception {
		String password = "";
		for(int i = 0; i<users.size(); i++) {
			if(users.get(i).getUsername().equals(username)) {
				password = users.get(i).getPassword();
			}
		}
		return cryptoOperations.stringEncryption(password);
	}
	
}
