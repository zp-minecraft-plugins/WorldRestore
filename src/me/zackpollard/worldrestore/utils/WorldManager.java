package me.zackpollard.worldrestore.utils;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.zackpollard.worldrestore.WorldRestore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;


public class WorldManager {
	
	private WorldRestore plugin;
	public final Logger log = Logger.getLogger("Minecraft");
	
	public WorldManager(WorldRestore plugin){
		this.plugin = plugin;	
	}
	
	public void restoreWorld(World world){
		
		if(deleteWorld(world)){
			copyWorld(world);
		} else {
			log.warning(ChatColor.RED + "The World was not restored, something went wrong!");
		}
	}
	
	public boolean deleteWorld(World world){
		
		plugin.getServer().unloadWorld(world, true);
		
        try {
            File worldFile = world.getWorldFolder();
            boolean deletedWorld = FileUtils.deleteFolder(worldFile);
            if (deletedWorld) {
            	log.info(ChatColor.RED + "[EndRaid]" + ChatColor.GOLD + "The world was sucessfully deleted");
            	return true;
            } else {
            	log.log(Level.WARNING, ChatColor.RED + "Something happened and the world couldn't be deleted");
            	log.log(Level.WARNING, ChatColor.RED + "Did you already delete the world?");
            	return false;
            }
        } catch (Throwable e) {
            return false;
        }
	}
	
	public void copyWorld(World world){
		
		String worldName = world.getName();
		WorldCreator creator = new WorldCreator(worldName);
		
		File source = new File(plugin.getDataFolder().getAbsolutePath() + "//Worlds//" + worldName);
		File target = new File(Bukkit.getWorldContainer() + File.separator + worldName);
		
		FileUtils.copyFolder(source, target, log);
		
		Bukkit.createWorld(creator);
	}
}