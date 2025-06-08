/**
 * GROUP/PAIR MEMBERS:
 * Chua, Hanielle Jermayn E. (12305693)
 * Kelsey, Gabrielle Madison F. (12307572)
 */

import java.net.*;
import java.io.*;

public class FileClient
{
	public static void main(String[] args)
	{
		String sServerAddress = args[0];
		int nPort = Integer.parseInt(args[1]);
		
		try
		{
			Socket clientEndpoint = new Socket(sServerAddress, nPort);
			
			System.out.println("Client: Connected to server at" + clientEndpoint.getRemoteSocketAddress());
			
			DataOutputStream dosWriter = new DataOutputStream(clientEndpoint.getOutputStream());
			dosWriter.writeUTF("Client: Hello from client" + clientEndpoint.getLocalSocketAddress());
			
			DataInputStream disReader = new DataInputStream(clientEndpoint.getInputStream());
			System.out.println(disReader.readUTF());

			// Receive file
			long fileSize = disReader.readLong();
			FileOutputStream fos = new FileOutputStream("Received.txt");

			byte[] buffer = new byte[4096];
			int bytesRead;
			long totalRead = 0;

			while (totalRead < fileSize && (bytesRead = disReader.read(buffer)) != -1) {
				fos.write(buffer, 0, bytesRead);
				totalRead += bytesRead;
			}
			fos.close();
			
			System.out.println("Client: Downloaded file \"Received.txt\"");
			clientEndpoint.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Client: Connection is terminated.");
		}
	}
}