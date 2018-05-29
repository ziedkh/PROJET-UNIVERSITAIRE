import socket
socket.gethostbyname(socket.gethostname())
client=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect(('192.168.1.8',10000))
client.send("ACHAT")
print "     |________Magazin Manar______|\n"
data =" "
while 1:
#________________________________________________Lire le menu principal : liste stock____________________________________________________________________________
    while("[*] Identificateur Client:" not in data):
        data=client.recv(1024)
        print data

#_______________________________________________________Saisie id client:________________________________________________________________________________________
    userInput = input("> ")
    client.send(str(userInput))

#_____________________________________________________Saisie id produit:_________________________________________________________________________________________
    data=client.recv(1024)
    print data

    userInput = input("> ")
    client.send(str(userInput))

#_________________________________________________________Saisie qt:_____________________________________________________________________________________________
    data=client.recv(1024)
    print data

    userInput = input("> ")
    client.send(str(userInput))

    
#_________________________________________message validation & choix de reessayer:________________________________________________________________________________
    data=client.recv(1024)
    print data



        
    userInput = input("> ")
    client.send(str(userInput))
    

    data=client.recv(1024)
    print data

    print"\n-----------------------------------------\n"
    print"-----------------------------------------\n"

#ET ON REBOUCLE     
