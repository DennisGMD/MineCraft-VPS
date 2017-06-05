package me.Athelor.perm.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PermCMD
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
      sendHelp(p);
    }
  }
  else
  {
	String pf = "§c§lPixl§f§lZone §f» §7 "; 
    sender.sendMessage(pf + "Je moet een speler zijn!");
  }
  return false;
}

public void sendHelp(Player p)
{
  String pf = "§c§lPixl§f§lZone §f» §7 "; 
  
  p.sendMessage(pf + "PixlPerms help menu");
  p.sendMessage("§c/user set <Player> <Group> §8- §7Zet een speler in een group!");
  p.sendMessage("§c/user remove <Player> <Group> §8- §7Haal een speler uit een group!");
  p.sendMessage("§c/user info <Player> §8- §7Laat de speler info zien!");
  p.sendMessage(" ");
  p.sendMessage("§c/group add <Group> <Permission> §8- §7Geef een group permissie's");
  p.sendMessage("§c/group remove <Group> <Permission> §8- §7Verwijder een permissie bij een group");
  p.sendMessage("§c/group create <Name> §8- §7Maak een group aan");
  p.sendMessage("§c/group delete <Group> §8- §7Verwijder een group");
  p.sendMessage("§c/group list §8- §7Laat alle groups zien");
  p.sendMessage(pf + "PixlPerms help menu");
	}
}