package me.Athelor.perm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.Athelor.perm.Commands.GroupCMD;
import me.Athelor.perm.Commands.PermCMD;
import me.Athelor.perm.Commands.UserCMD;
import me.Athelor.perm.Events.ChatPrefix;
import me.Athelor.perm.Events.JoinEvent;
import me.Athelor.perm.Events.TabPrefix;

public class Main
  extends JavaPlugin
{
  public static File userFile = new File("plugins/PixlPerms/users.yml");
  public static File groupFile = new File("plugins/PixlPerms/groups.yml");
  public static File playerFile = new File("plugins/PixlPerms/players.yml");
  public static FileConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);
  public static FileConfiguration groupConfig = YamlConfiguration.loadConfiguration(groupFile);
  public static FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
  public static Main plugin;
  
  public void onEnable()
  {
    plugin = this;
    
    getCommand("group").setExecutor(new GroupCMD());
    getCommand("user").setExecutor(new UserCMD());
    getCommand("pixlperm").setExecutor(new PermCMD());
    
    Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
    Bukkit.getPluginManager().registerEvents(new ChatPrefix(), this);
    Bukkit.getPluginManager().registerEvents(new TabPrefix(), this);
    
  }
  
  public void onDisable() {}
  {
    if (!userFile.exists()) {
      userConfigSave();
    }
    if (!groupFile.exists())
    {
      ArrayList<String> perm = new ArrayList<String>();
      perm.add("PERMISSION");
      
      groupConfig.set("default.default", Boolean.valueOf(true));
      groupConfig.set("default.chatprefix", "&7Speler %player%: %message%");
      groupConfig.set("default.tabprefix", "&7Speler %player%");
      groupConfig.set("default.permissions", perm);
      groupConfigSave();
    }
    if (!playerFile.exists())
    {
      List<String> players = new ArrayList<String>();
      playerConfig.set("Players", players);
      try
      {
        playerConfig.save(playerFile);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public void userConfigSave()
  {
    try
    {
      userConfig.save(userFile);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public void groupConfigSave()
  {
    try
    {
      groupConfig.save(groupFile);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public void userConfigLoad()
  {
    try
    {
      userConfig.load(userFile);
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (InvalidConfigurationException e)
    {
      e.printStackTrace();
    }
  }
  
  public void groupConfigLoad()
  {
    try
    {
      groupConfig.load(groupFile);
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (InvalidConfigurationException e)
    {
      e.printStackTrace();
    }
  }
}