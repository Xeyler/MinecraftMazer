package me.xeyler;

import org.bukkit.Location;
import org.bukkit.block.BlockState;

public class Reseter {

	public void reset(Location oldLoc, BlockState oldBlock, Location oldLoc2, BlockState oldBlock2) {
		
		if (oldLoc != null && oldBlock != null) {
			oldLoc.getBlock().setType(oldBlock.getType());
			oldLoc.getBlock().getState().setData(oldBlock.getData());
			oldBlock.update(false, false);
		}
		if(oldLoc2 != null && oldBlock2 != null) {
			oldLoc2.getBlock().setType(oldBlock2.getType());
			oldLoc2.getBlock().getState().setData(oldBlock2.getData());
			oldBlock2.update(false, false);
		}
			
	}
	
}
