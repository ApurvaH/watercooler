package com.watercooler.database;

import com.watercooler.watercoolergroup.WaterCoolerChatGroupCredentials;

import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

/**
 * Class to manage all groups of WaterCooler.
 */
public class ChatGroupRepository {
    // Hashtable to store all groupUUID(key) and their corresponding credentials(value)
    Hashtable<UUID, WaterCoolerChatGroupCredentials> chatGroupRepository = new Hashtable<>();
    // Hashtable to store groupUUID(key) and corresponding list of member UUIDs(value)
    Hashtable<UUID, List<UUID>> groupMembersMap = new Hashtable<>();

    /**
     * Utility to add new group to repository.
     *
     * @param newWaterCoolerChatGroup: WaterCoolerChatGroupCredentials object containing all essential group information
     *                                 to be saved in ActiveUserDirectory.
     * @param memberUUIDs:             List of member uuids (must contain atleast 1 uuid(i.e. group creator))
     * @return true if group creation successful
     */
    public boolean addNewGroupToRepository(WaterCoolerChatGroupCredentials newWaterCoolerChatGroup,
                                           List<UUID> memberUUIDs) {
        //TODO: Generate new UUID for group
        UUID groupUUID = null;
        chatGroupRepository.put(groupUUID, newWaterCoolerChatGroup);
        groupMembersMap.put(groupUUID, memberUUIDs);
        return true;
    }

    /**
     * Utility to delete group from repository based on groupUUID
     *
     * @param groupUUID: GroupUUID to be deleted
     * @return List of member UUIDs of deleted group in order to inform that group does not exist
     * @throws Exception
     */
    public List<UUID> removeGroupFromRepository(UUID groupUUID) throws Exception {
        if (checkIfGroupExists(groupUUID)) {
            chatGroupRepository.remove(groupUUID);
            return groupMembersMap.remove(groupUUID);
        } else
            throw new Exception("No such group exists");
    }


    /**
     * Utility to verify if group with specified UUID exists
     * [TODO Unnecessary here, but might be relevant if we use a in-mem store]
     *
     * @param groupUUID : GroupUUID to validate
     * @return true if found; false otherwise
     */
    public boolean checkIfGroupExists(UUID groupUUID) {
        if (chatGroupRepository.containsKey(groupUUID))
            return true;
        return false;
    }

    /**
     * Utility to retrieve all members of a chat group
     * [TODO Unnecessary here, but might be relevant if we use a in-mem store]
     *
     * @param groupUUID : GroupUUID whose member list has to be retrieved
     * @return List of all member UUIDs
     * @throws : Throws exception if an attempt is made to retrieve members of a non-existing group
     */
    public List<UUID> getGroupMembers(UUID groupUUID) throws Exception {
        if (checkIfGroupExists(groupUUID))
            return groupMembersMap.get(groupUUID);
        else
            throw new Exception("No such group exists");
    }
}
