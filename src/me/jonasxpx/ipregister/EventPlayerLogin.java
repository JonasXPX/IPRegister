package me.jonasxpx.ipregister;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class EventPlayerLogin implements Listener{

	private IPRegister plugin;
	
	public EventPlayerLogin(IPRegister plugin){this.plugin = plugin;}
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void playerLoginEvent_pre(PlayerLoginEvent e){
		if(ManagerPlayer.isRegistred(e.getPlayer())){
			if(!ManagerPlayer.isEqualIP(e.getPlayer(), e.getAddress().getHostAddress())){
				e.setResult(Result.KICK_OTHER);
				e.setKickMessage("§cSeu IP não é o mesmo cadastrado no sistema para logar neste Nick");
				return;
			}
		}
	}
}
