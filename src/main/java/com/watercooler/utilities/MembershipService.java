package com.watercooler.utilities;

import com.watercooler.client.WaterCoolerClientCredentials;
import com.watercooler.watercoolergroup.WaterCoolerChatGroupCredentials;

import java.util.UUID;

public class MembershipService {
    public UUID registerNewUser() {
        WaterCoolerClientCredentials newWaterCoolerClient = new WaterCoolerClientCredentials();
        return newWaterCoolerClient.clientUUID;
    }

    public boolean deregisterUser() {
        return true;
    }

    public UUID createNewGroup() {
        WaterCoolerChatGroupCredentials newWaterCoolerChatGroup = new WaterCoolerChatGroupCredentials();
        return newWaterCoolerChatGroup.groupId;
    }

    public boolean deleteGroup() {
        return true;
    }

    public boolean addNewUserToGroup() {
        return true;
    }

    public boolean removeUserFromGroup() {
        return true;
    }

}
