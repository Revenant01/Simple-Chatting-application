import java.io.*; 
import java.net.*; 

class TCPServer 
{ 

	public static void main(String argv[]) throws Exception 
	{ 
		ServerSocket welcomeSocket = new ServerSocket(8100);
		while (true)
		{
			String fromclientSentence; 
			String toClientSentence; 

			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream  outToClient =new DataOutputStream(connectionSocket.getOutputStream()); 
      		
			//The first part responsible for receiving messages from client to server and displaying it on the console
			fromclientSentence = inFromClient.readLine();
			System.out.println("CLIENT :" + fromclientSentence);
      	
			//The second part responsible for sending messages to client and displaying it on the console
			toClientSentence = inFromUser.readLine();
			if (toClientSentence.equals("stop"))// A condition to terminate the connection by the server
			{
				welcomeSocket.close();
				System.out.println("connection terminated by the server , you can't send anymore");
			}
			outToClient.writeBytes(toClientSentence+ '\n'); 
    }	
}
}