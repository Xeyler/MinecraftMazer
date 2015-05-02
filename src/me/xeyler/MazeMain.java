package me.xeyler;

import java.io.File;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MazeMain extends JavaPlugin implements Listener{

	//setting some variables
	Reseter reseter = new Reseter();
	
	public HashMap<Player, Location> start = new HashMap<Player, Location>();
	public HashMap<Player, Location> end = new HashMap<Player, Location>();
	public HashMap<Player, BlockState> startBlock = new HashMap<Player, BlockState>();
	public HashMap<Player, BlockState> endBlock = new HashMap<Player, BlockState>();
	
	//THESE VARIABLES WILL BE USED LATER FOR MARKING THE ENTRANCE AND EXIT TO A MAZE
	private HashMap<Player, Location> entrance = new HashMap<Player, Location>();
	private HashMap<Player, Location> exit = new HashMap<Player, Location>();
	public HashMap<Player, BlockState> entranceBlock = new HashMap<Player, BlockState>();
	public HashMap<Player, BlockState> exitBlock = new HashMap<Player, BlockState>();
		
	ChatColor green = ChatColor.GREEN;
	ChatColor red = ChatColor.RED;
	ChatColor yellow = ChatColor.YELLOW;
	ChatColor blue = ChatColor.BLUE;
		
	@Override
	public void onEnable() {
		
		this.getServer().getPluginManager().registerEvents(this, this);
		
		File file = new File(getDataFolder(), "config.yml");
		if(!file.exists()) {
			
			getLogger().info("Generating config.yml...");
			saveDefaultConfig();
		
		}
	
		getLogger().info("MinecraftMazer has been enabled!");
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onDisable() {
		
		getLogger().info("Cleaning up...");
		
		for (int step = Bukkit.getOnlinePlayers().length - 1; step >= 0; step--) {
			Player player = Bukkit.getOnlinePlayers()[step];
			reseter.reset(start.get(player), startBlock.get(player), end.get(player), endBlock.get(player));
			reseter.reset(entrance.get(player), entranceBlock.get(player), exit.get(player), exitBlock.get(player));
		}
		
		getLogger().info("MinecraftMazer has been disabled!");
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMarkBlock(PlayerInteractEvent event) {
			
		Player player = event.getPlayer();
			
		if (player.hasPermission("MinecraftMazer.allowed")) {
				
			if (player.getItemInHand().getType().toString().equalsIgnoreCase(this.getConfig().getString("selectTool"))) {
				
				if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
				
					event.setCancelled(true);
						
					if (startBlock.get(player) != null && this.getConfig().getBoolean("markSelections")) {
						
						reseter.reset(start.get(player), startBlock.get(player), null, null);
						
					}
						
						start.put(player, event.getClickedBlock().getLocation());
						
					if (this.getConfig().getBoolean("markSelections")) {
						
						startBlock.put(player, event.getClickedBlock().getState());
						
						event.getClickedBlock().setType(org.bukkit.Material.WOOL);
						event.getClickedBlock().setData((byte) 4);
						
					}
						
					int x = (int) start.get(player).getX();
					int y = (int) start.get(player).getY();
					int z = (int) start.get(player).getZ();
						
					player.sendMessage(yellow + "Position one set as " + red + x + ", " + green + y + ", " + blue + z);
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 20, 1);
						
				}
			
				if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {

					event.setCancelled(true);
					
					if (endBlock.get(player) != null && this.getConfig().getBoolean("markSelections")) {
						
						reseter.reset(null, null, end.get(player), endBlock.get(player));
						
					}
					
					end.put(player, event.getClickedBlock().getLocation());
						
					if (this.getConfig().getBoolean("markSelections")) {
						
						endBlock.put(player, event.getClickedBlock().getState());
						
						event.getClickedBlock().setType(org.bukkit.Material.WOOL);
						event.getClickedBlock().setData((byte) 11);
						
					}
						
					int x = (int) end.get(player).getX();
					int y = (int) end.get(player).getY();
					int z = (int) end.get(player).getZ();
						
					player.sendMessage(yellow + "Position two set as " + red + x + ", " + green + y + ", " + blue + z);
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 20, 1);
						
				}
				
			}
			
			if (player.getItemInHand().getType().toString().equalsIgnoreCase(this.getConfig().getString("2ndSelectTool"))) { 
			
				if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
					
					event.setCancelled(true);
						
					if (entranceBlock.get(player) != null && this.getConfig().getBoolean("markSelections")) {
						
						reseter.reset(entrance.get(player), entranceBlock.get(player), null, null);
						
					}
						
						entrance.put(player, event.getClickedBlock().getLocation());
						
					if (this.getConfig().getBoolean("markSelections")) {
						
						entranceBlock.put(player, event.getClickedBlock().getState());
						
						event.getClickedBlock().setType(org.bukkit.Material.WOOL);
						event.getClickedBlock().setData((byte) 5);
						
					}
						
					int x = (int) entrance.get(player).getX();
					int y = (int) entrance.get(player).getY();
					int z = (int) entrance.get(player).getZ();
						
					player.sendMessage(yellow + "Entrance set as " + red + x + ", " + green + y + ", " + blue + z);
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 20, 1);
						
				}
			
				if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {

					event.setCancelled(true);
					
					if (exitBlock.get(player) != null && this.getConfig().getBoolean("markSelections")) {
						
						reseter.reset(null, null, exit.get(player), exitBlock.get(player));
						
					}
					
					exit.put(player, event.getClickedBlock().getLocation());
						
					if (this.getConfig().getBoolean("markSelections")) {
						
						exitBlock.put(player, event.getClickedBlock().getState());
						
						event.getClickedBlock().setType(org.bukkit.Material.WOOL);
						event.getClickedBlock().setData((byte) 14);
						
					}
						
					int x = (int) exit.get(player).getX();
					int y = (int) exit.get(player).getY();
					int z = (int) exit.get(player).getZ();
						
					player.sendMessage(yellow + "Exit set as " + red + x + ", " + green + y + ", " + blue + z);
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 20, 1);
						
				}
				
				
			}
					
		}
				
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		
		Player player = event.getPlayer();
		reseter.reset(start.get(player), startBlock.get(player), end.get(player), endBlock.get(player));
		reseter.reset(entrance.get(player), entranceBlock.get(player), exit.get(player), exitBlock.get(player));
		
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("makemaze")) {

			Player player = (Player) sender;
			
			if(!(player instanceof Player)) {
				
				player.sendMessage(red + "Whoops! You must be a player to perform this command...");
				
			} else if (start.get(player) == null || end.get(player) == null) {
				
				player.sendMessage(red + "Whoops! You haven't selected two dimensions for the maze!");
				player.sendMessage(red + "Please use a " + this.getConfig().getString("selectTool").toLowerCase() + " to select them.");
				player.playSound(player.getLocation(), Sound.ANVIL_LAND, 7, 1);
				
			} else if (entrance.get(sender) == null || exit.get(player) == null) {
				
				player.sendMessage(red + "Whoops! You haven't selected an exit/entrance for the maze!");
				player.sendMessage(red + "Please use a " + this.getConfig().getString("2ndSelectTool").toLowerCase() + " to select them.");
				player.playSound(player.getLocation(), Sound.ANVIL_LAND, 7, 1);
				
			} else if (start.get(sender).getWorld() != end.get(sender).getWorld() || start.get(sender).getWorld() != exit.get(sender).getWorld() || start.get(sender).getWorld() != entrance.get(sender).getWorld()) {	

				sender.sendMessage(red + "Whoops! It looks like a dimension isnt in the right world!");
				player.playSound(player.getLocation(), Sound.ANVIL_LAND, 7, 1);
				
			} else {	
				
				String block = null;
				Mazer maze = new Mazer();
				Calculator calc = new Calculator();
				
				if (args.length == 1) {
					
					if (isBlock(args[0].toString()) == false || args[0].toString().equalsIgnoreCase("glassrainbow") == false || args[0].toString().equalsIgnoreCase("rainbow") == false){
					
					sender.sendMessage(args[0].toString());
					
						Location[] dimensions = calc.organize(start.get(sender), end.get(player));
						
						if (calc.isOn(entrance.get(sender), dimensions[0], dimensions[1]) && calc.isOn(exit.get(player), dimensions[0], dimensions[1])) {
						
							reseter.reset(entrance.get(player), entranceBlock.get(player), exit.get(player), exitBlock.get(player));
							
							block = args[0].toUpperCase();
							maze.makeMaze(dimensions[0], dimensions[1], entrance.get(sender), exit.get(sender), block, player);
						
							start.remove(sender);
							end.remove(sender);
							entrance.remove(sender);
							exit.remove(sender);
						
						} else {
							
							sender.sendMessage(red + "Whoops! Your entrance and/or exit aren't in the right spot!");
							sender.sendMessage(red + "Make sure they are where an outer wall should be generated.");
							player.playSound(player.getLocation(), Sound.ANVIL_LAND, 7, 1);
							
						}
						
					} else {
						
						sender.sendMessage(red + "Whoops! It looks like " + args[0] + " is not a valid input.");
						player.playSound(player.getLocation(), Sound.ANVIL_LAND, 7, 1);
						
					}
						
				} else {
					
					if (isBlock(this.getConfig().getString("blockType")) || this.getConfig().getString("blockType").equalsIgnoreCase("glassrainbow") || this.getConfig().getString("blockType").equalsIgnoreCase("rainbow")) {
						
						Location[] dimensions = calc.organize(start.get(sender), end.get(player));
						
						if (calc.isOn(entrance.get(sender), dimensions[0], dimensions[1]) && calc.isOn(exit.get(player), dimensions[0], dimensions[1])) {
							
							reseter.reset(entrance.get(player), entranceBlock.get(player), exit.get(player), exitBlock.get(player));
							
							block = this.getConfig().getString("blockType").toUpperCase();
							maze.makeMaze(dimensions[0], dimensions[1], entrance.get(sender), exit.get(sender), block, player);
						
							start.remove(sender);
							end.remove(sender);
							entrance.remove(sender);
							exit.remove(sender);
							
						} else {
							
							sender.sendMessage(red + "Whoops! Your entrance and/or exit aren't in the right spot!");
							sender.sendMessage(red + "Make sure they are where an outer wall should be generated.");
							player.playSound(player.getLocation(), Sound.ANVIL_LAND, 7, 1);
							
						}
					} else {
						
						sender.sendMessage(red + "Whoops! It looks like " + this.getConfig().getString("blockType").toLowerCase() + " is not a valid block.");
						sender.sendMessage(red + "Please change \"blockType\" in MinecraftMazer's config.");
						player.playSound(player.getLocation(), Sound.ANVIL_LAND, 7, 1);
						
					}
					
				}
				
			}
			
			return true;
			
		}
		
		return false;
		
	}

	private boolean isBlock(String block) {
		try {
			Material.matchMaterial(block.toUpperCase()).isBlock();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}