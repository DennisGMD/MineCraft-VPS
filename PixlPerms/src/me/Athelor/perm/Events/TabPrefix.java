package me.Athelor.perm.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Athelor.perm.Main;
import me.Athelor.perm.PlayerManager;

public class TabPrefix
  implements Listener
{
  @EventHandler
  public void onJoin(PlayerJoinEvent e)
  {
    Player p = e.getPlayer();
    setPrefix(PlayerManager.getGroup(p).toString(), p);
  }
  
  public void setPrefix(String groupname, Player p)
  {
    if (Main.groupConfig.getString(groupname + ".tabprefix") != null)
    {
      String prefix = Main.groupConfig.getString(groupname + ".tabprefix").replace("&", "§").replace("%player%", p.getName());
      p.setPlayerListName(prefix);
    }
  }
}
