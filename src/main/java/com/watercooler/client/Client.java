package com.watercooler.client;

import com.watercooler.chatmessage.WaterCoolerMessage;
import com.watercooler.database.ActiveUserDirectory;
import com.watercooler.messagebroker.MessageBroker;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;


public class Client {
    WaterCoolerClientCredentials clientInfo;
    ArrayBlockingQueue<WaterCoolerMessage> clientMessageQueue;
    MessageBroker broker = new MessageBroker();
    ActiveUserDirectory userDirectory = new ActiveUserDirectory();

    public void setClientCredentials(String actualName, String userName, String password,
                                     String alias, String emailAddress, String contactNumber) {
        clientInfo.actualName = actualName;
        clientInfo.userName = userName;
        clientInfo.password = password;
        clientInfo.alias = alias;
        clientInfo.emailAddress = emailAddress;
        clientInfo.contactNumber = contactNumber;
        clientInfo.clientUUID = null; // TODO Generate new UUID
    }

    public void sendMessage(String messageContents, UUID groupUUID) throws Exception {
        WaterCoolerMessage newMessage = new WaterCoolerMessage(messageContents, clientInfo.clientUUID, groupUUID);
        broker.distributeMessageToClients(newMessage);
    }

    public void receiveMessage() {
        // TODO receive message from broker and display it in appropriate chat group window. How????
        for(WaterCoolerMessage newMessage: clientMessageQueue) {
            System.out.println(newMessage.timestamp+": "+newMessage.senderId +" -> "+ newMessage.messageContents);
        }
    }
}
