package me.Athelor.perm;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.ConfigurationSection;

public class Perm
{
  public static ArrayList<String> getGroups()
  {
    ArrayList<String> groups = new ArrayList<String>();
    
    ConfigurationSection cs = Main.groupConfig.getConfigurationSection("");
    for (String group : cs.getKeys(false)) {
      if (!groups.contains(group)) {
        groups.add(group);
      }
    }
    return groups;
  }
  
  public static void addPermToGroup(String groupname, String permname)
  {
    List<String> perms = getPermsFromGroup(groupname);
    perms.add(permname);
    Main.groupConfig.set(groupname + ".permissions", perms);
    Main.plugin.groupConfigSave();
  }
  
  public static void removePermFromGroup(String groupname, String permname)
  {
    List<String> perms = getPermsFromGroup(groupname);
    perms.remove(permname);
    Main.groupConfig.set(groupname + ".permissions", perms);
    Main.plugin.groupConfigSave();
  }
  
  public static List<String> getPermsFromGroup(String groupname)
  {
    List<String> perms = Main.groupConfig.getStringList(groupname + ".permissions");
    return perms;
  }
  
  public static String getDefaultGroup()
  {
    ConfigurationSection cs = Main.groupConfig.getConfigurationSection("");
    for (String group : cs.getKeys(false)) {
      if (Main.groupConfig.getBoolean(group + ".default")) {
        return group;
      }
    }
    return null;
  }
}