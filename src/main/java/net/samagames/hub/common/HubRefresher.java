package net.samagames.hub.common;

import net.samagames.api.SamaGamesAPI;
import net.samagames.hub.Hub;
import net.samagames.hub.gui.main.GuiSwitchHub;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.com.google.gson.Gson;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HubRefresher implements Runnable
{
    private final Hub hub;
    private final ArrayList<JsonHub> hubs;

    public HubRefresher(Hub hub)
    {
        this.hub = hub;
        this.hubs = new ArrayList<>();
    }

    @Override
    public void run()
    {
        Jedis jedis = SamaGamesAPI.get().getResource();

        JsonHub thisHub = new JsonHub();
        thisHub.setHubNumber(Integer.parseInt(SamaGamesAPI.get().getServerName().split("_")[1]));
        thisHub.setConnectedPlayers(Bukkit.getOnlinePlayers().size());
        Bukkit.getOnlinePlayers().forEach(thisHub::addConnectedPlayer);

        String thisHubJson = new Gson().toJson(thisHub);
        jedis.hset("hubs_connected", SamaGamesAPI.get().getServerName(), thisHubJson);

        Map<String, String> redisHubs = jedis.hgetAll("hubs_connected");
        HashMap<Integer, String> hubsList = new HashMap<>();

        for (String hubServerName : redisHubs.keySet())
            hubsList.put(Integer.parseInt(hubServerName.split("_")[1]), redisHubs.get(hubServerName));

        this.hubs.clear();

        for (int hubNumber : hubsList.keySet())
        {
            String jsonHubString = hubsList.get(hubNumber);
            JsonHub jsonHub = new Gson().fromJson(jsonHubString, JsonHub.class);

            this.hubs.add(jsonHub);
        }

        jedis.close();

        this.hub.getGuiManager().getPlayersGui().keySet().stream().filter(uuid -> this.hub.getGuiManager().getPlayersGui().get(uuid) instanceof GuiSwitchHub).forEach(uuid -> this.hub.getGuiManager().getPlayersGui().get(uuid).update(Bukkit.getPlayer(uuid)));
    }

    public ArrayList<JsonHub> getHubs()
    {
        return this.hubs;
    }
}
