package me.jonasxpx.ipregister;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ManagerPlayer {

	public static boolean isRegistred(Player player){
		FileConfiguration conf = YamlConfiguration.loadConfiguration(new File(IPRegister.dir));
		if(conf.contains("Registred."+player.getName().toLowerCase()))
			return true;
		else
			return false;
	}
	
	public static boolean isRegistred(String player){
		FileConfiguration conf = YamlConfiguration.loadConfiguration(new File(IPRegister.dir));
		if(conf.contains("Registred."+player.toLowerCase()))
			return true;
		else
			return false;
	}
	
	public static boolean isEqualIP(Player player, String IP){
		FileConfiguration conf = YamlConfiguration.loadConfiguration(new File(IPRegister.dir));
		if(conf.isList("Registred."+player.getName().toLowerCase())){
			List<String> ip = conf.getStringList("Registred."+player.getName().toLowerCase());
			if(ip.contains(IP))
				return true;
			else
				return false;
		}else{
			String ip = conf.getString("Registred."+player.getName().toLowerCase());
			if(ip.equalsIgnoreCase(IP))
				return true;
			else
				return false;
		}
	}
	
	public static boolean unregister(String player) throws IOException{
		if(isRegistred(player)){
			FileConfiguration conf = YamlConfiguration.loadConfiguration(new File(IPRegister.dir));
			conf.set("Registred."+player.toLowerCase(), null);
			conf.save(new File(IPRegister.dir));
			return true;
		}
		return false;
	}

	
}
