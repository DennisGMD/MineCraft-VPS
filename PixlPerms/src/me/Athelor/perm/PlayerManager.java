package me.Athelor.perm;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerManager
{
  public static void initPerm(Player p, String groupname)
  {
    if (Main.userConfig.getString(p.getUniqueId().toString() + ".group") != null)
    {
      List<String> perms = Perm.getPermsFromGroup(groupname);
      for (String permname : perms) {
        p.addAttachment(Main.plugin, permname, false);
      }
    }
    Main.userConfig.set(p.getUniqueId().toString() + ".group", groupname);
    Main.plugin.userConfigSave();
    Main.plugin.userConfigLoad();
    
    List<String> perms = Perm.getPermsFromGroup(groupname);
    for (String permname : perms) {
      p.addAttachment(Main.plugin, permname, true);
    }
  }
  
  public static String getGroup(Player p)
  {
    return Main.userConfig.getString(p.getUniqueId().toString() + ".group");
  }
  
  public static void setGroup(String groupname, Player p)
  {
    initPerm(p, groupname);
  }
  
  public static Player nameToPlayer(String name)
  {
    Player p = Bukkit.getPlayer(name);
    return p;
  }
  
  public static String playerToName(Player p)
  {
    String name = p.getName();
    return name;
  }
}