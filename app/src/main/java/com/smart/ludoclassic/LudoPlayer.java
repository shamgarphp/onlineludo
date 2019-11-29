package com.smart.ludoclassic;

public class LudoPlayer {
    boolean isComputer;
    boolean playerActive = false;
    String playerColor;
    int playerId;
    String playerName;
    int playerPlace = -1;
    int[] playerRockAry = new int[]{-1, -1, -1, -1};
    int[] playerRockAryCount = new int[4];

    public LudoPlayer(int playerId, String playerName, boolean isComputer, String playerColor) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.isComputer = isComputer;
        this.playerColor = playerColor;
    }

    public int getPlayerPlace() {
        return this.playerPlace;
    }

    public void setPlayerPlace(int playerPlace) {
        this.playerPlace = playerPlace;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public boolean isComputer() {
        return this.isComputer;
    }

    public void setComputer(boolean isComputer) {
        this.isComputer = isComputer;
    }

    public boolean isPlayerActive() {
        return this.playerActive;
    }

    public void setPlayerActive(boolean playerActive) {
        this.playerActive = playerActive;
    }

    public int[] getPlayerRockAry() {
        return this.playerRockAry;
    }

    public void setPlayerRockAry(int[] playerRockAry) {
        this.playerRockAry = playerRockAry;
    }

    public int[] getPlayerRockAryCount() {
        return this.playerRockAryCount;
    }

    public void setPlayerRockAryCount(int[] playerRockAryCount) {
        this.playerRockAryCount = playerRockAryCount;
    }
}
