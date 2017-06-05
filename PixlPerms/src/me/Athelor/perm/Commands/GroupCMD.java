package me.Athelor.perm.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Athelor.perm.Main;
import me.Athelor.perm.Perm;

public class GroupCMD
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args)
  {
	String pf = "§c§lPixl§f§lZone §f» §7 ";  
    List<String> players = new ArrayList<String>();
    if ((sender instanceof Player))
    {
      Player p = (Player)sender;
      if (!p.hasPermission("permissions.*")) {
    	  p.sendMessage(pf + "Je hebt geen permissie");
      } else if (p.hasPermission("permissions.*")) {
        if (args.length == 0) {
          sendGroupHelp(p);
        } else if (args.length == 1)
        {
          if (args[0].equalsIgnoreCase("list")) {
            for (String all : Perm.getGroups()) {
              p.sendMessage("§7" + all);
            }
          } else {
            sendGroupHelp(p);
          }
        }
        else if (args.length == 2)
        {
          if (args[0].equalsIgnoreCase("delete"))
          {
            if (Main.groupConfig.get(args[1]) != null)
            {
              if (Main.groupConfig.getBoolean(args[1] + ".default"))
              {
            	  p.sendMessage(pf + "Je mag niet de default group verwijderen!");
              }
              else
              {
                Main.groupConfig.set(args[1], null);
                Main.plugin.groupConfigSave();
                p.sendMessage(pf + "Je hebt de groep" + args[1] + " succesvol verwijderd!");
              }
            }
            else {
            	p.sendMessage(pf + "Deze group bestaat niet!");
            }
          }
          else if (args[0].equalsIgnoreCase("create"))
          {
            if (Main.groupConfig.get(args[1]) == null)
            {
              Main.groupConfig.set(args[1] + ".default", Boolean.valueOf(false));
              Main.groupConfig.set(args[1] + ".permissions", players);
              Main.plugin.groupConfigSave();
              p.sendMessage(pf + "De group" + args[1] + " word ingesteld!");
            }
            else
            {
            	p.sendMessage(pf + "Deze group bestaat al!");
            }
          }
          else {
            sendGroupHelp(p);
          }
        }
        else if (args.length == 3) {
          if (args[0].equalsIgnoreCase("remove"))
          {
            List<String> perms = Main.groupConfig.getStringList(args[1] + ".permissions");
            if (Main.groupConfig.get(args[1]) != null)
            {
              if (perms.contains(args[2]))
              {
                perms.remove(args[2]);
                Main.groupConfig.set(args[1] + ".permissions", perms);
                Main.plugin.groupConfigSave();
                p.sendMessage(pf + "Je hebt de permissie succesvol verwijderd");
              }
              else
              {
            	  p.sendMessage(pf + "Deze group heeft de toesteming niet!");
              }
            }
            else {
            	p.sendMessage(pf + "Deze groep bestaat niet!");
            }
          }
          else if (args[0].equalsIgnoreCase("add"))
          {
            List<String> perms = Main.groupConfig.getStringList(args[1] + ".permissions");
            if (Main.groupConfig.get(args[1]) != null)
            {
              if (!perms.contains(args[2]))
              {
                perms.add(args[2]);
                Main.groupConfig.set(args[1] + ".permissions", perms);
                Main.plugin.groupConfigSave();
                p.sendMessage(pf + "De permissie werd succesvol toegevoegd!");
              }
              else
              {
            	  p.sendMessage(pf + "Deze group heeft de toestemming al!");
              }
            }
            else {
            	 p.sendMessage(pf + "Deze group bestaan niet!");
            }
          }
          else
          {
            sendGroupHelp(p);
          }
        }
      }
    }
    else
    {
    	sender.sendMessage(pf + "Je moet een speler zijn!");
    }
    return true;
  }
  
  public void sendGroupHelp(Player p)
  {
	  p.sendMessage("§c/group add <Group> <Permission> §8- §7Geef een group permissie's");
	  p.sendMessage("§c/group remove <Group> <Permission> §8- §7Verwijder een permissie bij een group");
	  p.sendMessage("§c/group create <Name> §8- §7Maak een group aan");
	  p.sendMessage("§c/group delete <Group> §8- §7Verwijder een group");
	  p.sendMessage("§c/group list §8- §7Laat alle groups zien");
  }
}
