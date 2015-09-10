package me.jonasxpx.ipregister;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class EventPlayerLogin implements Listener{

	private IPRegister plugin;
	
	public EventPlayerLogin(IPRegister plugin){this.plugin = plugin;}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void playerLoginEvent(final PlayerJoinEvent e){
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if(ManagerPlayer.isRegistred(e.getPlayer()))
				{
					if(!ManagerPlayer.isEqualIP(e.getPlayer(), ManagerIP.formatIP(ManagerIP.getPlayerIp(e.getPlayer())))){
						e.getPlayer().kickPlayer("§cSeu IP não é o mesmo cadastrado no sistema para logar neste Nick");
					}
				}
			
				
			}
		}.runTaskLater(this.plugin, 20*2);
				
	}
	
}
