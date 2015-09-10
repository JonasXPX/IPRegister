package me.jonasxpx.ipregister;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class IPRegister extends JavaPlugin{

	public static String dir = "";
	
	@Override
	public void onEnable() {
		saveConfig();
		dir = this.getDataFolder().getPath() + "\\config.yml";
		getCommand("ipregister").setExecutor(new Commands());
		getServer().getPluginManager().registerEvents(new EventPlayerLogin(this), this);
	}	
	
	
	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
	}
}
