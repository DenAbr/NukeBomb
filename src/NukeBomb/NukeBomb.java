package NukeBomb;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.java.JavaPlugin;

public class NukeBomb extends JavaPlugin {
	private FileConfiguration config;
//	private Player  victims[];
	
	public void onEnable(){
		saveDefaultConfig();
		config = getConfig();
		getLogger().info("NukeBomb 1.0 activated");
	}
	
	public void onDisable(){
		saveConfig();
		getLogger().info("NukeBomb 1.0 deactivated");
	}
	
	// FOR FUTURE //
	
	//	private Player[] getVictims(Location loc){
	//	int count;
	//	Player  victims[];
	//	victims = new Player[config.getInt("maxVictims")];
	//	count = 1;
	//	for(Player onlinePlayer : Bukkit.getOnlinePlayers())
	 //   {
	 //    if(loc.distanceSquared(onlinePlayer.getLocation()) <= config.getInt("distance"))
	 //    {
	 //   	 victims[count] = onlinePlayer;
    //		 count = count + 1;
    //		 if (count > config.getInt("maxVictims"))
    //		 {
    //			 break;
    //		 }
	 //    }
	 //   }
	//	 return victims;
	//}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	    if(command.getName().equalsIgnoreCase("nuke")){
	    	if (sender.hasPermission("nuke.nuke"))
	    	{
	    		Player player = (Player) sender;
	    		Location loc = player.getLocation();
	    		final TNTPrimed bomb = loc.getWorld().spawn(loc, TNTPrimed.class);
	    		bomb.setFuseTicks(config.getInt("bombfiretime") * 25);
	    		bomb.setYield(config.getInt("distance") * 2);
	    		player.sendMessage("Nuke bomb activated!");
	    		getServer().broadcastMessage("WARNING! NUKE BOMB WAS ACTIVATED AT X=" + Math.round(loc.getX()) + ", Y=" + Math.round(loc.getY()) + ", Z=" + Math.round(loc.getZ()));
//	    		victims = getVictims(loc);
		    	return true;
	    	}
	    }      
	    return false;
	}
	
}
