package com.mitsugaru.wildguard.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.mitsugaru.wildguard.WildGuard;
import com.mitsugaru.wildguard.permissions.PermissionNode;
import com.mitsugaru.wildguard.services.IWildCommand;

public class ReloadCommand implements IWildCommand {

   @Override
   public boolean execute(WildGuard plugin, CommandSender sender, Command command, String label, String[] args) {
      if(!plugin.hasPermissionNode(sender, PermissionNode.ADMIN)) {
         sender.sendMessage(ChatColor.GRAY + plugin.getTag() + ChatColor.RED + " Lack Permission: " + ChatColor.WHITE
               + PermissionNode.ADMIN.getNode());
         return true;
      }
      plugin.getRootConfig().reload();
      plugin.getBlacklist().reload();
      sender.sendMessage(ChatColor.GRAY + plugin.getTag() + ChatColor.GREEN + " Reloaded configuration.");
      return true;
   }

}
