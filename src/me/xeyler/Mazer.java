package me.xeyler;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Mazer {

	MazeMain plugin;
	Calculator calc = new Calculator();
	
	public void makeMaze( Location startLoc, Location endLoc, Location enterLoc, Location exitLoc, String block, Player sender ) {
		
		makeCube(startLoc, endLoc, enterLoc, exitLoc, block);
		generate(startLoc, endLoc, enterLoc, exitLoc, sender);
		
	}

	@SuppressWarnings("deprecation")
	private void makeCube(Location startLoc, Location endLoc, Location enterLoc, Location exitLoc, String block) {
		
		for (int x = (int) startLoc.getX(); x <= (int) endLoc.getX(); x++) {
			
			for (int y = (int) startLoc.getY();y <= endLoc.getY(); y++) {
				
				for (int z = (int) startLoc.getZ(); z <= (int) endLoc.getZ(); z++) {
					
					if (block.equalsIgnoreCase("rainbow")) {
						
						startLoc.getWorld().getBlockAt(x, y ,z).setType(Material.STAINED_CLAY);
						Byte rainbowdata = (byte) y;
						while(rainbowdata > 7) {
							rainbowdata = (byte) (rainbowdata - 7);
						}
						if (rainbowdata == 1) {
							rainbowdata = 14;
						} else if (rainbowdata == 2) {
							rainbowdata = 1;
						} else if (rainbowdata == 3) {
							rainbowdata = 4;
						} else if (rainbowdata == 4) {
							rainbowdata = 5;
						} else if (rainbowdata == 5) {
							rainbowdata = 9;
						} else if (rainbowdata == 6) {
							rainbowdata = 11;
						} else if (rainbowdata == 7) {
							rainbowdata = 2;
						}
						startLoc.getWorld().getBlockAt(x, y ,z).setData(rainbowdata);
						
						
						
					} else if (block.equalsIgnoreCase("glassrainbow")) {
						
						startLoc.getWorld().getBlockAt(x, y ,z).setType(Material.STAINED_GLASS);
						Byte rainbowdata = (byte) y;
						while(rainbowdata > 7) {
							rainbowdata = (byte) (rainbowdata - 7);
						}
						if (rainbowdata == 1) {
							rainbowdata = 14;
						} else if (rainbowdata == 2) {
							rainbowdata = 1;
						} else if (rainbowdata == 3) {
							rainbowdata = 4;
						} else if (rainbowdata == 4) {
							rainbowdata = 5;
						} else if (rainbowdata == 5) {
							rainbowdata = 9;
						} else if (rainbowdata == 6) {
							rainbowdata = 11;
						} else if (rainbowdata == 7) {
							rainbowdata = 2;
						}
						startLoc.getWorld().getBlockAt(x, y ,z).setData(rainbowdata);
						
					} else {
						
						//perhaps add something here for the block is a number issue
						startLoc.getWorld().getBlockAt(x, y ,z).setType(Material.getMaterial(block));
					
					}
						
				}
					
			}
					
			
		}
		
	}
	
	public void generate(Location startLoc, Location endLoc, Location enterLoc, Location exitLoc, Player sender) {

		sender.sendMessage("" + calc.facing(enterLoc, startLoc, endLoc));
		sender.sendMessage("" + calc.facing(exitLoc, startLoc, endLoc));
		
	}
		
}
	

