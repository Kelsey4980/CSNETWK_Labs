/**
 * GROUP/PAIR MEMBERS:
 * Chua, Hanielle Jermayn E. (12305693)
 * Kelsey, Gabrielle Madison F. (12307572)
 */

import java.net.*;
import java.io.*;

public class FileServer
{
	public static void main(String[] args)
	{
		int nPort = Integer.parseInt(args[0]);
		System.out.println("Server: Listening on port " + args[0] + "...");
		ServerSocket serverSocket;
		Socket serverEndpoint;

		try 
		{
			serverSocket = new ServerSocket(nPort);
			serverEndpoint = serverSocket.accept();
			
			System.out.println("Server: New client connected: " + serverEndpoint.getRemoteSocketAddress());
			
			DataInputStream disReader = new DataInputStream(serverEndpoint.getInputStream());
			System.out.println(disReader.readUTF());
			
			DataOutputStream dosWriter = new DataOutputStream(serverEndpoint.getOutputStream());
			dosWriter.writeUTF("Server: Hello World!");

			// Send file
			File file = new File("Download.txt");
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[4096];
			int bytesRead;

			dosWriter.writeLong(file.length());
			while ((bytesRead = fis.read(buffer)) != -1) {
				dosWriter.write(buffer, 0, bytesRead);
			}
			fis.close();

			System.out.println("Server: Sending file \"Download.txt\"");
			serverEndpoint.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Server: Connection is terminated.");
		}
	}
}