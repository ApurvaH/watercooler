package com.watercooler.chatmessage;

import java.time.OffsetDateTime;
import java.util.UUID;

public class WaterCoolerMessage {
    public String messageContents;
    public OffsetDateTime timestamp; //Epoch Time
    public UUID senderId;
    public UUID groupUUID;

    public WaterCoolerMessage(String messageContents, UUID senderId, UUID groupUUID) {
        this.messageContents = messageContents;
        this.senderId = senderId;
        this.groupUUID = groupUUID;
        this.timestamp = OffsetDateTime.now();
    }
}
