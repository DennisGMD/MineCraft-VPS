package me.Athelor.perm.Events;

import java.io.IOException;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Athelor.perm.Main;
import me.Athelor.perm.Perm;
import me.Athelor.perm.PlayerManager;

public class JoinEvent
  implements Listener
{
  List<String> players = Main.playerConfig.getStringList("Players");
  
  @EventHandler
  public void onJoin(PlayerJoinEvent e)
  {
    Player p = e.getPlayer();
    if (!Main.playerConfig.getStringList("Players").contains(p.getUniqueId().toString()))
    {
      if (Perm.getDefaultGroup() != null)
      {
        PlayerManager.setGroup(Perm.getDefaultGroup(), p);
        this.players.add(p.getUniqueId().toString());
        Main.playerConfig.set("Players", this.players);
        try
        {
          Main.playerConfig.save(Main.playerFile);
        }
        catch (IOException e1)
        {
          e1.printStackTrace();
        }
      }
    }
    else {
      PlayerManager.initPerm(p, PlayerManager.getGroup(p));
    }
  }
}
