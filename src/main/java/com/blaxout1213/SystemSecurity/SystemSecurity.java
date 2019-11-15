package com.blaxout1213.SystemSecurity;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DoubleFlag;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
@SuppressWarnings("unused")
public class SystemSecurity extends JavaPlugin
{
	public WorldGuard worldGuard = WorldGuard.getInstance();
	private WorldGuardPlugin worldGuardPlugin;
	private EventListener ev;
	public static final Flag SECURITY = new DoubleFlag("security");

	public void onEnable()
	{
		worldGuardPlugin = getWorldGuardPlugin();
		ev = new EventListener(this);
		FlagRegistry registry = worldGuard.getFlagRegistry();
		registry.register(SECURITY);
	}
	public void onDisable()
	{
		
	}

	public WorldGuardPlugin getWorldGuardPlugin() 
	{
	    Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
	
	    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) 
	    {
	        System.out.println("Worldguard not found! Get your shit together!");
	        this.onDisable();
	    }
	
	    return (WorldGuardPlugin) plugin;
	}
}
