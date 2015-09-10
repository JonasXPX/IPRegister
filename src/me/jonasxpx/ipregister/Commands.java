package me.jonasxpx.ipregister;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] args) {
		if(!(sender.isOp() || sender.hasPermission("ipregister.allow"))){
			sender.sendMessage("§cYou don't have permissions for this!");
			return true;
		}
		if(args.length == 3){
			if(args[0].equalsIgnoreCase("register")){
				if(args[1] != null || args[2] != null){
					if(ManagerIP.registerPlayerIP(args[1], ManagerIP.formatIP(args[2])))
						sender.sendMessage("§aJogador cadastrado!");
					else
						sender.sendMessage("§cFalha!");
					return true;
				}
				sender.sendMessage("/ipregister register <Player Name> <IP>");
				return true;
			}
		}
		if(args.length == 2){
			if(args[0].equalsIgnoreCase("delete")){
				try {
					if(!ManagerPlayer.unregister(args[1]))
						sender.sendMessage("§cFalha!");
					else
						sender.sendMessage("§aRegistro deletado!");
				} catch (IOException e) {
					e.printStackTrace();
				}
				return true;
			}
			if(args[0].equalsIgnoreCase("getip")){
				if(Bukkit.getPlayer(args[1]).isOnline())
					sender.sendMessage("§aIP: §f" + ManagerIP.getPlayerIp(Bukkit.getPlayer(args[1])));
				else{
					sender.sendMessage("§cJogador offline!");
				}
				return true;
			}
		}
		sender.sendMessage("----- IP REGISTER - JonasXPX ------");
		sender.sendMessage("Permite login em apenas um IP específico");
		sender.sendMessage("/ipregister register <Nick> <IP>");
		sender.sendMessage("/ipregister delete <Nick>");
		sender.sendMessage("/ipregister getip <Nick>");
		return false;
	}

	
	
	
}
