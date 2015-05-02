package me.xeyler;

import org.bukkit.Location;

public class Calculator {

	public int facing(Location Loc, Location startLoc, Location endLoc) {

		int startZ= (int) startLoc.getZ();
		int endX = (int) endLoc.getX();
		int endZ = (int) endLoc.getZ();
		int LocX = (int) Loc.getX();
		int LocZ = (int) Loc.getZ();
		
		if (startZ == LocZ) {
			
			return 1;
			
		} else if (endX == LocX) {
			
			return 2;
			
		} else if (endZ == LocZ) {
			
			return 3;
			
		} else {
			
			return 4;
			
		}
		
	}
	
	public boolean isOn(Location Loc, Location startLoc, Location endLoc) {
		
		int startX = (int) startLoc.getX();
		int startZ= (int) startLoc.getZ();
		int endX = (int) endLoc.getX();
		int endZ = (int) endLoc.getZ();
		int LocX = (int) Loc.getX();
		int LocZ = (int) Loc.getZ();
		
		if (startX == LocX || endX == LocX) {
			
			if (LocZ > startZ && LocZ < endZ) {
				
			return true;
			
			}
			
		} else if (startZ == LocZ || endZ == LocZ) {
			
			if (LocX > startX && LocX < endX) {
				
				return true;
				
			}
			
		}	
		
		return false;
		
	}
	
	public Location[] organize(Location startLoc, Location endLoc) {
		
		int startX;
		int endX;
		if (startLoc.getX() < endLoc.getX()) {
			startX = (int) startLoc.getX();
			endX = (int) endLoc.getX();
		} else {
			startX = (int) endLoc.getX();
			endX = (int) startLoc.getX();
		}
		
		int startY;
		int endY;
		if (startLoc.getY() < endLoc.getY()) {
			startY = (int) startLoc.getY();
			endY = (int) endLoc.getY();
		} else {
			startY = (int) endLoc.getY();
			endY = (int) startLoc.getY();
		}
		
		int startZ;
		int endZ;
		if (startLoc.getZ() < endLoc.getZ()) {
			startZ = (int) startLoc.getZ();
			endZ = (int) endLoc.getZ();
		} else {
			startZ = (int) endLoc.getZ();
			endZ = (int) startLoc.getZ();
		}
	
		startLoc = new Location(startLoc.getWorld(), startX, startY, startZ);
		endLoc = new Location(endLoc.getWorld(), endX, endY, endZ);
		Location[] locations = {startLoc, endLoc};
		return locations;
		
		}

	}
