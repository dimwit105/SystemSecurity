package com.blaxout1213.SystemSecurity;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;

public class EventListener implements Listener
{
	private SystemSecurity plugin;

	public EventListener(SystemSecurity plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		plugin = this.plugin;
	}
	
	@EventHandler
	public void onEntityAttack(EntityDamageByEntityEvent event)
	{
		if(event.getEntity() instanceof Player)
		{
			Player p = (Player) event.getEntity();
			RegionContainer rc = WorldGuard.getInstance().getPlatform().getRegionContainer();
			RegionManager rm = rc.get((World) p.getWorld());
			Vector v = new Vector(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ());
			ApplicableRegionSet set = rm.getApplicableRegions(v);
			for(ProtectedRegion pr : set)
			{
				Object flag = pr.getFlag(SystemSecurity.SECURITY);
				Bukkit.broadcastMessage("SECURITY = " + flag);
			}
		}
	}
}
