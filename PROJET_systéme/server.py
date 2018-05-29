from socket import *
import socket
import thread
import threading
import copy
import struct
import sys, traceback


def send_one_message(clientsocket, msg):
         clientsocket.send(msg)
	
       
#__________________________________________  Appler au niv client_achat _______________________________________________________

         
def Achat(demande,clientsocket):   
    requete = copy.copy(demande.split(','))
    idclt = requete[0]
    idprod = requete[1]
    qt = requete[2]
    with open('stock.txt', 'r') as f:
        produit = list()
        exist = False
        for ligne in f:
            produit = copy.copy(ligne.split(','))
            if(idprod == produit[0]):
                if(int(float(qt)) <= int(float(produit[2]))):
                    newqt=((int(float(produit[2])))-(int(float(qt))))
                    Newqt = str(newqt)
                    m="Quantite Totale : %s\n" % (produit[2])
                    m+= "Quantite demandée %s	\n" % (qt) 
                    modifstock(idprod,Newqt)
                    ajouthisto(idclt,idprod, qt, "Succes") 
                    x = MAJfacture(idclt, float(qt)*float(produit[1]))
                    result = m+"Client : %s : Achat de %s x produit %s avec succes  \n\t Total à payer : %s\n " % (idclt, qt, idprod, x)
                    result+="Appuyez sur 1 pour contu, Q pour Quitter : "
                elif(int(float(qt)) > int(float(produit[2]))):
                    send_one_message ( clientsocket,"qt tot : %s" % (produit[2]))
                    send_one_message ( clientsocket , "qt demandée : %s" % (qt))
                    ajouthisto(idclt,idprod, qt, "Echec")
                    result = "Echec ! stock insuffisant\n"
                    result+="Appuyez sur 1 pour contu, Q pour Quitter : "
                exist = True
            else: continue
        if(exist == False):
            result = "Produit inexistant !"
            result += "Appuyez sur 1 pour contu, Q pour Quitter : "
        send_one_message(clientsocket, result)

       
        
        



#_______________________________________  Appler au niv client_admin ==> consulter _facture    ____________________________________________
def readfacture(ind,clientsocket):
 lock = threading.Lock()
 lock.acquire()
 try :
    with open('facture.txt', 'r') as f:
        facture = list()
        existe=False 
        for ligne in f:
            facture = copy.copy(ligne.split(','))
            if (ind == facture[0]):
                msg = " ID Client : %s \n Somme à payer : %s \n" % (facture[0], facture[1])
                existe = True 
                break
        if(existe!=True): msg ="Aucune Facture !"
    send_one_message(clientsocket, msg)      
 finally:
        lock.release()



 #_______________________________________      Appler au niv achat    ___________________________________________________

        
def MAJfacture(idclt, somme):
  lock = threading.Lock()
  lock.acquire()
  try :
     listefacture = list() 
     with open('facture.txt', 'r') as f:
        factur = list() 
        for ligne in f:
            factur = copy.copy(ligne.split(','))
            if (idclt == factur[0]):
                x = float(factur[1])+ float(somme)
                factur[1]=str(x)
            listefacture.append(factur)
        #print(listeproduit)
     with open('facture.txt', 'w') as f:
        for elem in listefacture:
            for elm in elem:
                f.write(elm.rstrip())
                if(elm != elem[1]):
                   f.write(',')
                else: f.write('\n')
        #f.write('\n')
    
  finally:
     lock.release()
     return x


     
#________________________________________     Appler au niv  client_admin ==> consulter_prod   _______________________________________
def readstock(ind ,clientsocket):
  lock = threading.Lock()
  lock.acquire()
  try :    
    with open('stock.txt', 'r') as f:
        produit = list()
        existe=False 
        for ligne in f:
            produit = copy.copy(ligne.split(','))
            if (ind == produit[0]):
                msg = " ref : %s \n prix unitaire : %s \n stock : %s \n\n" % (produit[0], produit[1], produit[2])
                existe = True 
                break
        if(existe!=True): msg = "Produit inexistant !"
        send_one_message(clientsocket, msg)    
  finally:
        lock.release()
  






#_______________________________________     Appler au niv client_Achat   ______________________________________________________
def read_fich_stock(clientsocket):
  lock = threading.Lock()
  lock.acquire()
  try :    
    with open('stock.txt', 'r') as f:
        produit = list()
        for ligne in f:
                produit = copy.copy(ligne.split(','))
                msg = (" ref : %s \t prix unitaire : %s \t stock : %s \t\n" % (produit[0], produit[1], produit[2]))
                send_one_message(clientsocket, msg)            
  finally:
        lock.release()




#_________________________________________     Appler au niv Achat ______________________________________________________________

        
def modifstock(ind, stk):
  lock = threading.Lock()
  lock.acquire()
  try :
     listeproduit = list() 
     with open('stock.txt', 'r') as f:
        produit = list() 
        for ligne in f:
            produit = copy.copy(ligne.split(','))
            if (ind == produit[0]):
                produit[2]=stk   
            listeproduit.append(produit)
        #print(listeproduit)
     with open('stock.txt', 'w') as f:
        for elem in listeproduit:
            for elm in elem:
                f.write(elm.rstrip())
                if(elm != elem[2]):
                   f.write(',')
            #f.write ("%s" % elem)
            f.write('\n')
  finally:
        lock.release()






#_____________________________________     Appler au niv client_Admin    ____________________________________________________________


def readhisto(clientsocket):
    lock = threading.Lock()
    with open('histo.txt', 'r') as f:
         histo = list()
         lock.acquire()
         msg =""
         for ligne in f:
            histo = copy.copy(ligne.split(','))
            msg+= " ID Client : %s \n Réfrance Produit : %s \n Valeur Commande : %s \n Résultat : %s \n\n\n" % (histo[0], histo[1], histo[2], histo[3])
         send_one_message(clientsocket, msg)
         lock.release()
 
        




#_____________________________________         Appler au niv de l'achat       _______________________________________________


   
def ajouthisto(idclt, idprod, qt, resul):
    lock = threading.Lock()
    lock.acquire()
    try:
       with open('histo.txt', 'a') as f:
           f.write("\n%s,%s,%s,%s".rstrip() % (idclt, idprod, qt, resul))
       return
    finally:
        lock.release()

#______________________________________________________________________________________________________________________________
        
def client_Achat(clientsocket):
  while 1:
        send_one_message(clientsocket, "\n  [*] Nos produits\n")
        send_one_message(clientsocket ," ID_PRODUIT \t PRIX_UNIT  \t\t  QUANTITE \n")
        read_fich_stock(clientsocket)
        #introduire les donneés 
        send_one_message(clientsocket, " [*] Identificateur Client:\n")
        ch =clientsocket.recv(1024)
        send_one_message(clientsocket, " [*] Produit à commander(REFERANCE) \n")
        ch += ","+ clientsocket.recv(1024)
        send_one_message(clientsocket, " [*] QUANTITE : \n")
        ch+= "," + clientsocket.recv(1024)
        #appler la fonction achat
        Achat(ch,clientsocket)

        rep = clientsocket.recv(1024)
        if(rep == '0'):
                send_one_message(clientsocket, "Au revoir !!!!!!")
                break 
        else : send_one_message(clientsocket,"\n")


#___________________________________________________________________________________________________________________________________
        
def client_Admin(clientsocket):
    while 1:
        
        send_one_message(clientsocket, "\n\n [1] Consulter Produit   \n [2] Consulter Facture   \n [3] Historique \n [4] EXIT ")
        choix =clientsocket.recv(1024)
        
        if (choix == "1" ):
             send_one_message(clientsocket, "\n\n  [*] Referénce Produit:\n ")
             ref =clientsocket.recv(1024)
             readstock(ref,clientsocket)
        elif(choix == "2"):
             send_one_message(clientsocket, "\n\n  [*] Identificateur Client :\n ")
             idclt =clientsocket.recv(1024)
             readfacture(idclt,clientsocket)
        elif(choix =="3"):
             readhisto(clientsocket)
             send_one_message(clientsocket, " \n*****\n")
        elif(choix =="4"):
            clientsocket.close()
            traceback.print_exc(file=sys.stdout)
            sys.exit(0)   
          
        

#_________________________________________________________________________________________________________________________________________      

def handler(clientsocket, clientaddr):
    print ("Accepted connection from: ", clientaddr)

    while 1:
        data = clientsocket.recv(1024)
        if( data == "ACHAT" ):
               client_Achat(clientsocket)

        elif (data == "ADMIN" ):
           client_Admin(clientsocket)
    clientsocket.close(clientsocket)
#____________________________________________________________________________________________________________________________________________
def Main():

 server= socket.socket(socket.AF_INET,socket.SOCK_STREAM)
 server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
 
 server.bind(('192.168.1.8',10000))	
	
 server.listen(3)
 print "Listening on port", server.getsockname()[1]	
 while 1:
        print( "Server is listening for connections\n")

        clientsocket, clientaddr = server.accept()
        thread.start_new_thread(handler, (clientsocket, clientaddr))
             
        
 server.close()

 print ("Main complete")

if __name__ == '__main__':
    Main()
    
