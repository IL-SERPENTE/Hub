package net.samagames.hub.common.hydroangeas.packets.hubinfos;

import net.samagames.hub.common.hydroangeas.connection.Packet;

import java.util.UUID;

/**
 * ╱╲＿＿＿＿＿＿╱╲
 * ▏╭━━╮╭━━╮▕
 * ▏┃＿＿┃┃＿＿┃▕
 * ▏┃＿▉┃┃▉＿┃▕
 * ▏╰━━╯╰━━╯▕
 * ╲╰╰╯╲╱╰╯╯╱  Created by Silvanosky on 27/10/2016
 * ╱╰╯╰╯╰╯╰╯╲
 * ▏▕╰╯╰╯╰╯▏▕
 * ▏▕╯╰╯╰╯╰▏▕
 * ╲╱╲╯╰╯╰╱╲╱
 * ＿＿╱▕▔▔▏╲＿＿
 * ＿＿▔▔＿＿▔▔＿＿
 */
public class HostGameInfoToHubPacket  extends Packet
{
    private UUID event;
    private UUID creator;

    private int state;// 0 = create / 1 = update / 2 = delete

    private int playerMaxForMap;
    private int playerWaitFor;
    private int totalPlayerOnServers;

    public int getPlayerMaxForMap() {
        return playerMaxForMap;
    }

    public void setPlayerMaxForMap(int playerMaxForMap) {
        this.playerMaxForMap = playerMaxForMap;
    }

    public int getPlayerWaitFor() {
        return playerWaitFor;
    }

    public void setPlayerWaitFor(int playerWaitFor) {
        this.playerWaitFor = playerWaitFor;
    }

    public int getTotalPlayerOnServers() {
        return totalPlayerOnServers;
    }

    public void setTotalPlayerOnServers(int totalPlayerOnServers) {
        this.totalPlayerOnServers = totalPlayerOnServers;
    }

    public UUID getCreator() {
        return creator;
    }

    public void setCreator(UUID creator) {
        this.creator = creator;
    }

    public UUID getEvent() {
        return event;
    }

    public void setEvent(UUID event) {
        this.event = event;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}