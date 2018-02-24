package me.zackpollard.worldrestore;

import java.io.File;

import me.zackpollard.worldrestore.utils.WorldManager;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldRestore extends JavaPlugin {
	
	public WorldManager worldManager = new WorldManager(this);
	
    public void onDisable() {
    	
    	
    }
    
    public void onEnable() {
    	
   		generateConfig();
   		
   		Bukkit.getScheduler().runTaskLater(this, new RestoreWorld(this), 200);
   		
   		Bukkit.getScheduler().runTaskLaterAsynchronously(this, new RestoreWorld(this), 1728000);
    }
    
    public void generateConfig(){
    	
    	File source = new File(this.getDataFolder().getAbsolutePath() + File.separator + "Worlds");
    	source.mkdirs();
    	
    	FileConfiguration config = this.getConfig();
   		config.addDefault("WorldRestore.WorldName", "WorldName");
  		config.options().copyDefaults(true);
   		saveConfig();
    }
}