package com.icodehaven.cannonplatforms.events;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import com.icodehaven.cannonplatforms.Core;

public class ExplosionEvent implements Listener {
	
	List<String> materials = Core.getInstance().getConfig().getStringList("settings.materials");

	@EventHandler
	public void onTNTExplode(EntityExplodeEvent event) {
		if (event.isCancelled())
			return;
		if (!(event.getEntity() instanceof TNTPrimed))
			return;
		for (int c = 0; c < event.blockList().size(); c++) {
			Block block = (Block) event.blockList().get(c);
			Location loc = block.getLocation();
			int x = loc.getBlockX();
			int z = loc.getBlockZ();
			int y = loc.getBlockY();
			if (materials.contains(String.valueOf(block.getType()))) {
				event.blockList().remove(c);
				c--;
			} else {
				for (int i = y; i >= 0; i--) {
					if (materials.contains(String.valueOf(loc.getWorld().getBlockAt(x, i, z).getType())) && i != 0) {
						event.blockList().remove(c);
						c--;
						break;
					}
				}
			}
		}
	}

}
