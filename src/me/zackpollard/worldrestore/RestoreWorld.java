package me.zackpollard.worldrestore;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class RestoreWorld implements Runnable {
	
	WorldRestore plugin;

	public RestoreWorld(WorldRestore plugin) {
		
		this.plugin = plugin;
	}

	@Override
	public void run() {
		
		File dir = new File(plugin.getDataFolder() + "//Worlds");
		
		for(World world : Bukkit.getWorlds()){
		
			for(File file : dir.listFiles()){
				
				if(file.isDirectory()){
				
					if(file.getName() == world.getName()){
						
						plugin.worldManager.restoreWorld(world);
					}
				}
			}
		}
	}
}
