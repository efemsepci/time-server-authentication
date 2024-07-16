import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class TimeClient {

    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int serverPort = 3333;

        try {
        	Socket socket = new Socket(serverAddress, serverPort);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter username: ");
            String username = sc.nextLine();
            output.println(username);
        	String serverResponse = input.readLine();
            System.out.println("Time is: " + serverResponse);
            if(!(serverResponse.equals("Invalid username!!!"))) {
            	clientOperations(username);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void clientOperations(String username) throws Exception {
    	AuthenticationServer authenticationService = new AuthenticationServer();
    	Scanner sc = new Scanner(System.in);
    	List<User> users = authenticationService.getUsers();
    	if(username.equals("admin")) {
    		while(true) {
    			System.out.println("0: Exit");
            	System.out.println("1: Change password");
            	System.out.println("2: Show users");
            	System.out.println("3: Edit selected user");
            	System.out.println("4: Add user");
            	String option = sc.nextLine();
            	if(option.equals("0")) {
            		System.out.println("Goodbye!");
            		break;
            	}
            	else if(option.equals("1")) {
            		System.out.println("Type new password:");
            		String newPassword = sc.nextLine();
            		for(int i = 0; i<users.size(); i++) {
            			if(users.get(i).getUsername().equals("admin")) {
            				users.get(i).setPassword(newPassword);
            			}
            		}
            	}
            	else if(option.equals("2")) {
            		System.out.println("Users:");
            		for(int i = 0; i<users.size(); i++) {
            			System.out.println(users.get(i));
            		}
            	}
            	else if(option.equals("3")) {
            		System.out.println("Type name: ");
            		String selectedUser = sc.nextLine();
            		for(int i = 0; i<users.size(); i++) {
            			if(users.get(i).getUsername().equals(selectedUser)) {
            				System.out.println("Type new username");
            				String newUsername = sc.nextLine();
            				System.out.println("Type new password");
            				String newPassword = sc.nextLine();
            				users.get(i).setUsername(newUsername);
            				users.get(i).setPassword(newPassword);
            			}
            		}
            	}
            	else if(option.equals("4")){
            		System.out.println("Username:");
            		String usernameN = sc.nextLine();
            		System.out.println("Password:");
            		String passwordN = sc.nextLine();
            		User newUser = new User(usernameN, passwordN);
            		users.add(newUser);
            	}
            	else {
            		System.out.println("Invalid option!");
            	}
    		}
    	}
    	else {
    		while(true) {
    			System.out.println("0: Exit");
            	System.out.println("1: Change password");
            	String option = sc.nextLine();
            	if(option.equals("0")) {
            		System.out.println("Goodbye!");
            		break;
            	}
            	else if(option.equals("1")) {
            		System.out.println("Type new password:");
            		String newPassword = sc.nextLine();
            		for(int i = 0; i<users.size(); i++) {
            			if(users.get(i).getUsername().equals(username)) {
            				users.get(i).setPassword(newPassword);
            			}
            		}
            	}
    		}
    	}
    }
}
