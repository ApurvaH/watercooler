package com.watercooler.messagebroker;


import com.watercooler.chatmessage.WaterCoolerMessage;
import com.watercooler.client.Client;
import com.watercooler.database.ActiveUserDirectory;
import com.watercooler.database.ChatGroupRepository;

import java.util.List;
import java.util.UUID;

public class MessageBroker {
    ChatGroupRepository groupRepo = new ChatGroupRepository();
    ActiveUserDirectory userDirectory = new ActiveUserDirectory();
    Client client = new Client();

    // Create a continuous pipeline for distributing messages
    public void distributeMessageToClients(WaterCoolerMessage newMessage) throws Exception {
        List<UUID> allMemberUUIDS = groupRepo.getGroupMembers(newMessage.groupUUID);
        // Send message to all members of the group except the sender
        for(UUID memberUUID: allMemberUUIDS) {
            if(memberUUID!=newMessage.senderId) {
                userDirectory.getMessageQueueInstance(memberUUID).add(newMessage);
            }
        }
    }
}
