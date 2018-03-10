package com.icodehaven.cannonplatforms;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.icodehaven.cannonplatforms.events.ExplosionEvent;

public class Core extends JavaPlugin {

	public static Core instance;

	@Override
	public void onEnable() {
		instance = this;
		getServer().getPluginManager().registerEvents(new ExplosionEvent(), this);
		getConfig().options().copyHeader(true);
		getConfig().options().copyDefaults(true);
		saveConfig();
		consoleMessage("&8[&cCannonPlatforms&8] &fThe plugin has been enabled");
	}

	@Override
	public void onDisable() {
		consoleMessage("&8[&cCannonPlatforms&8] &fThe plugin has been disabled");
		instance = null;
	}

	public static Core getInstance() {
		return instance;
	}

	public void consoleMessage(String msg) {
		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
	}

}