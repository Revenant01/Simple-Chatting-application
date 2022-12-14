package Milestone2;
import java.io.*; 
import java.net.*; 
import java.util.*;
class TCPClient 
{ 
	public static void main(String argv[]) throws Exception 
	{ 
		while (true) // to handle the condition of starting the connection only when typing the word "CONNECT"
		{
			Scanner inFromUser1 = new Scanner(System.in);
			String firstCheck = inFromUser1.nextLine();
			if (firstCheck.equals("CONNECT"))
			{
				System.out.println("succesfully connected" + '\n') ;
				break;
			}
			else 
				System.out.println("couldn't connect , please try again" + '\n');
		}
		
		while (true)// The real application chat code
		{
			//The first part when the client sends a message to the server
			String toServerSentence; 
			String fromServerSentence; 
			
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); 
			Socket clientSocket = new Socket("192.168.1.138", 8100); 
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); 	
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
			//The first part responsible for sending messages from client to server only and displaying it on the console
			toServerSentence = inFromUser.readLine(); 
			//System.out.println("CLIENT: " + toServerSentence);
			if (toServerSentence.equals("stop")) //A condition to terminate the connection by the client
			{
				clientSocket.close(); 
				System.out.println("connection terminated, you can't send anymore");
				break;
			}
			outToServer.writeBytes(toServerSentence + '\n'); 
			
			//The second part responsible for receiving messages from sever to client only and displaying it on the console 
			fromServerSentence = inFromServer.readLine(); 
			System.out.println("SERVER: " + fromServerSentence); 
			if ((fromServerSentence==null)||fromServerSentence.equals("stop"))
			{
				clientSocket.close(); 
				System.out.println("connection terminated, you can't send anymore");
				break;
			}
			
    
		}
}
}