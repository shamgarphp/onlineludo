package com.smart.ludoclassic;

public class TakenBox {
    Integer markerNumber;
    Integer playerId;

    public TakenBox(Integer playerId, Integer markerNumber) {
        this.playerId = playerId;
        this.markerNumber = markerNumber;
    }

    public Integer getPlayerId() {
        return this.playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getMarkerNumber() {
        return this.markerNumber;
    }

    public void setMarkerNumber(Integer markerNumber) {
        this.markerNumber = markerNumber;
    }
}
