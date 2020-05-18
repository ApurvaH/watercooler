/*
TODO: Use Redis or ElastiCache for this.
*/
package com.watercooler.database;

import com.watercooler.chatmessage.WaterCoolerMessage;
import com.watercooler.client.WaterCoolerClientCredentials;
import com.watercooler.messagebroker.MessageBroker;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Class to manage list of all active users of WaterCooler.
 */
public class ActiveUserDirectory {

    // Hashtable to store clientUUID(key) and their corresponding credentials(value)
    Hashtable<UUID, WaterCoolerClientCredentials> clientDirectory = new Hashtable<>();
    // Hashtable to store clientUUID(key) and reference to their message queue(value)
    Hashtable<UUID, ArrayBlockingQueue<WaterCoolerMessage>> clientMessageQueue = new Hashtable<>();

    /**
     * Utility to add new users to WaterCoolerDirectory
     *
     * @param newWaterCoolerClient: WaterCoolerClientCredentials object containing all essential client information
     *                              to be saved in ActiveUserDirectory.
     * @return UUID: returns unique clientUUID for if new user registration succeeds
     * @throws : Throws exception if an attempt is made to register an already existing client
     */
    public UUID addUserToDirectory(WaterCoolerClientCredentials newWaterCoolerClient) throws Exception {
        //TODO: Generate new UUID in java
        UUID uniqueNewClientUUID = null;
        if (clientDirectory.containsKey(uniqueNewClientUUID))
            throw new Exception("Client is already a member of WaterCooler!");
        else {
            clientDirectory.put(uniqueNewClientUUID, newWaterCoolerClient);
            clientMessageQueue.put(uniqueNewClientUUID, new ArrayBlockingQueue(1024));
        }
        return uniqueNewClientUUID;
    }

    /**
     * Utility to remove existing user from WaterCoolerDirectory
     *
     * @param waterCoolerClientUUID: UUID of client whose account has to be deactivated
     *                               to be saved in ActiveUserDirectory.
     * @return status: returns true if deletion succeeds, false otherwise
     * @throws : Throws exception if an attempt is made to delete a client that does not exist
     */
    public boolean deleteUserFromDirectory(UUID waterCoolerClientUUID) throws Exception {
        if (clientDirectory.containsKey(waterCoolerClientUUID)) {
            clientDirectory.remove(waterCoolerClientUUID);
            clientMessageQueue.remove(waterCoolerClientUUID);
        } else
            throw new Exception("No such user exists!");
        return true;
    }

    /**
     * Utility to get list of all active users from the directory
     *
     * @return : list of UUIDs of all the active clients
     */
    public List<UUID> getActiveUsers() {
        return new ArrayList<>(clientDirectory.keySet());
    }

    public ArrayBlockingQueue<WaterCoolerMessage> getMessageQueueInstance(UUID clientUUID) throws Exception {
        if (clientMessageQueue.containsKey(clientUUID))
            return clientMessageQueue.get(clientUUID);
        else
            throw new Exception("Invalid clientUUID! No such client exists");
    }

}
