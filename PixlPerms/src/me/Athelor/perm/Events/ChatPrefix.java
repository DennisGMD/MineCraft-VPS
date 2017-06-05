package me.Athelor.perm.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.Athelor.perm.Main;
import me.Athelor.perm.PlayerManager;

public class ChatPrefix
  implements Listener
{
  @EventHandler
  public void onChat(AsyncPlayerChatEvent e)
  {
    Player p = e.getPlayer();
    String group = PlayerManager.getGroup(p).toString();
    if ((Main.groupConfig.getString(group + ".chatprefix") != null) && (!e.getMessage().startsWith("/")))
    {
      String format = Main.groupConfig.getString(group + ".chatprefix");
      e.setCancelled(true);
      Bukkit.broadcastMessage(format.replace("&", "§").replace("%player%", p.getName()).replace("%message%", e.getMessage()));
    }
    else if ((Main.groupConfig.getString(group + ".chatprefix") == null) || (!e.getMessage().startsWith("/")))
    {
      e.setCancelled(true);
      Bukkit.broadcastMessage("§7" + p.getDisplayName() + "§7: §7" + e.getMessage());
    }
    else
    {
      e.setCancelled(false);
    }
  }
}
