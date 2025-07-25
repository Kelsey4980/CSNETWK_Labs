# GROUP/PAIR MEMBERS:
# Chua, Hanielle Jermayn E. (12305693)
# Kelsey, Gabrielle Madison F. (12307572)

# import socket module
from socket import *
import sys # In order to terminate the program

serverSocket = socket(AF_INET, SOCK_STREAM)
# Prepare a sever socket
serverSocket.bind(('', 6969))  # Fill
serverSocket.listen(1)          # Fill


while True:
    #Establish the connection
    print('CSNETWK Web Server is ready to serve...')
    connectionSocket, addr = serverSocket.accept()      # Fill
    try:
        message = connectionSocket.recv(1024).decode()  # Fill
        filename = message.split()[1]
        f = open(filename[1:])
        outputdata = f.read()   # Fill
        
        #Send one HTTP header line into socket
        connectionSocket.send("HTTP/1.1 200 OK\r\n\r\n".encode())   # Fill
        
        #Send the content of the requested file to the client
        for i in range(0, len(outputdata)):
            connectionSocket.send(outputdata[i].encode())
       
        connectionSocket.send("\r\n".encode())
        connectionSocket.close()
    
    except IOError:
        #Send response message for file not found
        connectionSocket.send("HTTP/1.1 404 Not Found\r\n\r\n".encode())    # Fill
        connectionSocket.send("<html><body><h1>404 Not Found</h1></body></html>".encode()) # Fill
       
        #Close client socket
        connectionSocket.close() # Fill

serverSocket.close()
sys.exit()  # Terminate the program after sending the corresponding data