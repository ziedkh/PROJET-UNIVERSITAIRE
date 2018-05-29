import socket
socket.gethostbyname(socket.gethostname())
client=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect(('192.168.1.8',10000))
client.send("ADMIN")
i = 1
data =" "
while 1:
#___________________________________________Reception menu principal______________________________________________________
    data=client.recv(1024)
    print data
#_________________________________________________________________________________________________________________________

#___________________________________________Envoi du choix________________________________________________________________
    
    userInput = input("> ")
    client.send(str(userInput))
    #Cas choix 1 ou 2
    if(str(userInput) == "1" or str(userInput) == "2"):
    
        data=client.recv(1024)
        print data

        userInput = input("> ")
        client.send(str(userInput))

        data=client.recv(1024)
        print data
    #Cas choix 3
    elif(str(userInput) == "3"):
        #while 1:
        data=client.recv(1024)
            #if("\n*****\n" in data): break
        print data
    else:
        print"Choix invalide !\n"
        
    
