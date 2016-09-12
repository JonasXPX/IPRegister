package me.jonasxpx.ipregister;

import java.io.File;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class IPRegister extends JavaPlugin{

	public static String dir = "";
	public static boolean useDatabase;
	protected static IPDataBase db;
	@Override
	public void onEnable() {
		dir = this.getDataFolder().getPath() + "\\config.yml";
		getCommand("ipregister").setExecutor(new Commands());
		getServer().getPluginManager().registerEvents(new EventPlayerLogin(), this);
		getConfig().options().copyDefaults(true);
		saveConfig();
		useDatabase = getConfig().getBoolean("usedatabase");
		if(useDatabase)
			db = new IPDataBase(getConfig().getString("Database.address").trim(), 
					getConfig().getString("Database.port").trim(), 
					getConfig().getString("Database.database").trim(),
					getConfig().getString("Database.username").trim(), 
					getConfig().getString("Database.password").trim());
	}	
	
	
	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
	}
}
