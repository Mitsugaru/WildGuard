package com.mitsugaru.wildguard.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.mitsugaru.wildguard.WildGuard;
import com.mitsugaru.wildguard.config.node.RootNode;
import com.mitsugaru.wildguard.permissions.PermissionNode;

public class BlockListener implements Listener {

   private WildGuard plugin;

   public BlockListener(WildGuard plugin) {
      this.plugin = plugin;
   }

   @EventHandler(priority = EventPriority.LOW)
   public void blockPlace(final BlockPlaceEvent event) {
      if(event.isCancelled()) {
         return;
      }
      final Player player = event.getPlayer();
      if(player == null) {
         return;
      } else if(plugin.hasPermissionNode(player, PermissionNode.IGNORE)) {
         return;
      }
      final Block block = event.getBlock();
      if(block == null) {
         return;
      } else if(!isWilderness(block.getLocation())) {
         return;
      }
      String id = "";
      if(block.getData() == 0) {
         id = "" + block.getTypeId();
      } else {
         id = block.getTypeId() + "." + block.getData();
      }
      final boolean whitelist = plugin.getRootConfig().getBoolean(RootNode.PROTECTION_PLACE_WHITELIST);
      boolean cancel = false;
      if(whitelist) {
         cancel = !plugin.getBlacklist().getPlaceItemList().contains(id);
      } else {
         cancel = plugin.getBlacklist().getPlaceItemList().contains(id);
      }
      if(cancel) {
         event.setCancelled(true);
         player.sendMessage(ChatColor.GRAY + plugin.getTag() + " Wilderness protected.");
      }
   }

   @EventHandler(priority = EventPriority.LOW)
   public void blockBreak(final BlockBreakEvent event) {
      if(event.isCancelled()) {
         return;
      }
      final Player player = event.getPlayer();
      if(player == null) {
         return;
      } else if(plugin.hasPermissionNode(player, PermissionNode.IGNORE)) {
         return;
      }
      final Block block = event.getBlock();
      if(block == null) {
         return;
      } else if(!isWilderness(block.getLocation())) {
         return;
      }
      String id = "";
      if(block.getData() == 0) {
         id = "" + block.getTypeId();
      } else {
         id = block.getTypeId() + "." + block.getData();
      }
      final boolean whitelist = plugin.getRootConfig().getBoolean(RootNode.PROTECTION_BREAK_WHITELIST);
      boolean cancel = false;
      if(whitelist) {
         cancel = !plugin.getBlacklist().getBreakItemList().contains(id);
      } else {
         cancel = plugin.getBlacklist().getBreakItemList().contains(id);
      }
      if(cancel) {
         event.setCancelled(true);
         player.sendMessage(ChatColor.GRAY + plugin.getTag() + " Wilderness protected.");
      }
   }

   /**
    * Check to see if the location is wilderness to Factions.
    * 
    * @param location
    *           - Location to check.
    * @return True if wilderness. Else false if owned.
    */
   public boolean isWilderness(Location location) {
      return Board.getFactionAt(new FLocation(location)).isNone();
   }

}
