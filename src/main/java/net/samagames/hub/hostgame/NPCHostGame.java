package net.samagames.hub.hostgame;

import net.samagames.api.SamaGamesAPI;
import net.samagames.hub.common.hydroangeas.packets.hubinfos.HostGameInfoToHubPacket;
import net.samagames.tools.holograms.Hologram;
import net.samagames.tools.npc.nms.CustomNPC;
import org.bukkit.ChatColor;
import org.bukkit.Location;

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
public class NPCHostGame
{
    private final UUID event;
    private final UUID creator;
    private final String name;
    private final CustomNPC npc;

    public NPCHostGame(Location location, HostGameInfoToHubPacket packet)
    {
        this.event = packet.getEvent();
        this.creator = packet.getCreator();
        this.name = SamaGamesAPI.get().getUUIDTranslator().getName(packet.getCreator());

        this.npc = SamaGamesAPI.get().getNPCManager().createNPC(location, packet.getCreator(), new String[] {
                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Nouvel Evenement !"
        });

        this.update(packet);
    }

    public void update(HostGameInfoToHubPacket packet)
    {
        if(!packet.getEvent().equals(this.event))
            return;

        Hologram hologram = this.npc.getHologram();
        hologram.change(new String[] {
                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Evènement " + this.name,
                ChatColor.GOLD + "" + packet.getTotalPlayerOnServers() + ChatColor.AQUA +  "/" + ChatColor.RED + packet.getPlayerMaxForMap()
        });
    }

    public void remove()
    {
        SamaGamesAPI.get().getNPCManager().removeNPC(this.npc);
    }
}