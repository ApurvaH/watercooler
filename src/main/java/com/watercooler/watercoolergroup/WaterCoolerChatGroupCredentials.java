package com.watercooler.watercoolergroup;

import java.time.OffsetDateTime;
import java.util.UUID;
import java.net.URL;

public class WaterCoolerChatGroupCredentials {
    String groupName;
    public UUID groupId;
    OffsetDateTime activeSince;
    boolean isArchived;
    public URL waterCoolerLink;
}
