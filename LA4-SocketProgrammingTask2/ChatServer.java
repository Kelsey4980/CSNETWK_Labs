import java.net.*;
import java.io.*;

public class ChatServer {
	public static void main(String[] args) {
		int nPort = Integer.parseInt(args[0]);
		System.out.println("Server: Listening on port " + args[0] + "...\n");
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(nPort);

			Socket clientA = serverSocket.accept();
			System.out.println("Server: New client connected: " + clientA.getRemoteSocketAddress() + "\n");

			System.out.println("Server: Listening on port " + args[0] + "...\n");

			Socket clientB = serverSocket.accept();
			System.out.println("Server: New client connected: " + clientB.getRemoteSocketAddress() + "\n");

			/* Data Stream for Client A */
			DataInputStream disReaderA = new DataInputStream(clientA.getInputStream());
			DataOutputStream dosWriterA = new DataOutputStream(clientA.getOutputStream());

			/* Data Stream for Client B */
			DataInputStream disReaderB = new DataInputStream(clientB.getInputStream());
			DataOutputStream dosWriterB = new DataOutputStream(clientB.getOutputStream());

			/* Client Names */
			String nameClientA = disReaderA.readUTF();
			String nameClientB = disReaderB.readUTF();

			/* Client Messages */
			String messageClientA = disReaderA.readUTF();
			String messageClientB = disReaderB.readUTF();

			/* Message Sending to Recipient */
			String responseClientA = "Message from " + nameClientB + ": " + messageClientB + "\n";
			dosWriterA.writeUTF(responseClientA);

			String responseClientB = "Message from " + nameClientA + ": " + messageClientA + "\n";
			dosWriterB.writeUTF(responseClientB);

			clientA.close();
			clientB.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null && !serverSocket.isClosed()) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Server: Connection terminated.");
		}
	}
}