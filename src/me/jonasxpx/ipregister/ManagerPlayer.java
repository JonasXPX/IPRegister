package me.jonasxpx.ipregister;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ManagerPlayer {

	public static boolean isRegistred(Player player){
		if(IPRegister.useDatabase){
			return IPRegister.db.getIP(player.getName()) == null ? false : true;
		}else{
			FileConfiguration conf = YamlConfiguration.loadConfiguration(new File(IPRegister.dir));
			if(conf.contains("Registred."+player.getName().toLowerCase()))
				return true;
			else
				return false;
		}
	}
	
	public static boolean isRegistred(String player){
		if(IPRegister.useDatabase){
			return IPRegister.db.getIP(player) == null ? false : true;
		}else{
			FileConfiguration conf = YamlConfiguration.loadConfiguration(new File(IPRegister.dir));
			if(conf.contains("Registred."+player.toLowerCase()))
				return true;
			else
				return false;
		}
	}
	
	public static boolean isEqualIP(Player player, String IP){
		if(IPRegister.useDatabase){
			String ip = IPRegister.db.getIP(player.getName());
			if(ip.contains(","))
				return Arrays.asList(ip.split(",")).contains(IP);
			else
				return ip.equalsIgnoreCase(IP);
		}else{
			FileConfiguration conf = YamlConfiguration.loadConfiguration(new File(IPRegister.dir));
			if(conf.isList("Registred."+player.getName().toLowerCase())){
				List<String> ip = conf.getStringList("Registred."+player.getName().toLowerCase());
				if(ip.contains(ManagerIP.formatIP(IP)))
					return true;
				else
					return false;
			}else{
				String ip = conf.getString("Registred."+player.getName().toLowerCase());
				if(ip.equalsIgnoreCase(ManagerIP.formatIP(IP)))
					return true;
				else
					return false;
			}
		}
	}
	public static boolean isEqualIP(String player, String IP){
		if(IPRegister.useDatabase){
			String ip = IPRegister.db.getIP(player);
			if(ip.contains(","))
				return Arrays.asList(ip.split(",")).contains(IP);
			else
				return ip.equalsIgnoreCase(IP);
		}else{
			FileConfiguration conf = YamlConfiguration.loadConfiguration(new File(IPRegister.dir));
			if(conf.isList("Registred."+player.toLowerCase())){
				List<String> ip = conf.getStringList("Registred."+player.toLowerCase());
				if(ip.contains(ManagerIP.formatIP(IP)))
					return true;
				else
					return false;
			}else{
				String ip = conf.getString("Registred."+player.toLowerCase());
				if(ip.equalsIgnoreCase(ManagerIP.formatIP(IP)))
					return true;
				else
					return false;
			}
		}
	}
	
	
	
	public static boolean unregister(String player) throws IOException{
		if(IPRegister.useDatabase){
			IPRegister.db.toggleIP(player, null);
			return true;
		}else
		if(isRegistred(player)){
			FileConfiguration conf = YamlConfiguration.loadConfiguration(new File(IPRegister.dir));
			conf.set("Registred."+player.toLowerCase(), null);
			conf.save(new File(IPRegister.dir));
			return true;
		}
		return false;
	}

	
}
