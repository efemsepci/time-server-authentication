import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeServer {
	
	public static int clientCount = 0;

    public static void main(String[] args) {
    	int portNum = 3333;
    	
    	try{
    		ServerSocket serverSocket = new ServerSocket(portNum);
    		System.out.println("Waiting for client on port " + portNum);
    		while(true) {
    			Socket clientSocket = serverSocket.accept();
    			if(clientSocket.isConnected()) {
    				clientCount++;
    			}
    			new Thread(() -> clientHandle(clientSocket)).start();
    			
    		}
    		
    	}catch(Exception e) {
    		System.err.println("Error!");
    	}
    	
    }

    private static void clientHandle(Socket clientSocket) {
        try{
        	PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
        	BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        	String clientUsername = input.readLine();
        	if(authenticateClient(clientUsername)) {
        		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            	String time = myFormat.format(new Date());
            	System.out.println("Hello Client " + clientCount);
            	output.println(time);
        	}
        	else {
				output.println("Invalid username!!!");
				clientSocket.close();
        	}
        	
        } catch(Exception e){
        	System.err.println("Error!");
        }
        }
    
    private static boolean authenticateClient(String username) throws Exception{
    	AuthenticationServer authenticationService = new AuthenticationServer();
    	if(authenticationService.authenticateUser(username)) {
    		String ticket = authenticationService.createTicket(username);
    		if(authenticationService.checkTicket(ticket)) {
    			return true;
    		}
    		else {
    			return false;
    		}
    	}
    	return false;
    }
    
}




