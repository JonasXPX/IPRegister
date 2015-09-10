package me.jonasxpx.ipregister;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ManagerIP {
	
	public static String getPlayerIp(Player player){
		return player.getAddress().getHostString();
	}
	
	
	public static boolean registerPlayerIP(String playerName, String IP){
		FileConfiguration conf = YamlConfiguration.loadConfiguration(new File(IPRegister.dir));
		conf.set("Registred."+playerName.toLowerCase(), formatIP(IP));
		try {
			conf.save(new File(IPRegister.dir));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public static String formatIP(String IP){
		if(IP.indexOf(":") != -1)
			return IP.replace(".", "-").substring(0, IP.indexOf(":"));
		else
			return IP.replace(".", "-");
	}
	
	public static void main(String[] args) {
		String ip = "192.168.2.100";
		System.out.println(formatIP(ip));
	}
	
}
