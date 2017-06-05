package me.Athelor.perm.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Athelor.perm.Main;
import me.Athelor.perm.Perm;
import me.Athelor.perm.PlayerManager;

public class UserCMD
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args)
  {
    if ((sender instanceof Player))
    {
      String pf = "§c§lPixl§f§lZone §f» §7 "; 
      Player p = (Player)sender;
      if (!p.hasPermission("permissions.*")) {
    	  p.sendMessage(pf + "Je hebt hier geen permissie voor!");
      } else if (p.hasPermission("permissions.*")) {
        if (args.length == 0) {
          sendUserHelp(p);
        } else if (args.length == 2)
        {
          if (args[0].equalsIgnoreCase("info"))
          {
            Player target = Bukkit.getPlayer(args[1]);
            p.sendMessage("§c" + target.getName() + " §6ist in der Gruppe §c" + PlayerManager.getGroup(target));
          }
          else if (args[0].equalsIgnoreCase("setdefault"))
          {
            Player target = Bukkit.getPlayer(args[1]);
            PlayerManager.setGroup(Perm.getDefaultGroup(), target);
            target.kickPlayer("§cPixlPerms\nPermission Veranderd!");
            p.sendMessage("§6Der Spieler §c" + target.getName() + " §6ist nun in der §cdefault §6Gruppe");
          }
          else
          {
            sendUserHelp(p);
          }
        }
        else if (args.length == 3)
        {
          if (args[0].equalsIgnoreCase("set"))
          {
            Player target = Bukkit.getPlayer(args[1]);
            if (target != null)
            {
              if (Main.groupConfig.getString(args[2]) != null)
              {
                PlayerManager.setGroup(args[2], target);
                target.kickPlayer("§cPixlPerms\n§4Permission Veranderd!");
                target.kickPlayer("§cPixlPerms\n§4Permission Veranderd!");
              }
              else
              {
            	  p.sendMessage(pf + "Die group bestaat niet!");
              }
            }
            else
            {
            	p.sendMessage(pf + "De speler §c" + args[1] + " §7Is niet online!");
              return false;
            }
          }
          else
          {
            sendUserHelp(p);
          }
        }
        else {
          sendUserHelp(p);
        }
      }
    }
    else
    {
    	String pf = "§c§lPixl§f§lZone §f» §7 ";  
        sender.sendMessage(pf + "Je moet een speler zijn!");
    }
    return false;
  }
  
  public void sendUserHelp(Player p)
  {
	  p.sendMessage("§c/user set <Player> <Group> §8- §7Zet een speler in een group!");
	  p.sendMessage("§c/user remove <Player> <Group> §8- §7Haal een speler uit een group!");
	  p.sendMessage("§c/user info <Player> §8- §7Laat de speler info zien!");
  }
}