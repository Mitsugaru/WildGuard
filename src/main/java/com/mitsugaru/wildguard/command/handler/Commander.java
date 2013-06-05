package com.mitsugaru.wildguard.command.handler;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.mitsugaru.wildguard.WildGuard;
import com.mitsugaru.wildguard.command.ReloadCommand;
import com.mitsugaru.wildguard.services.CommandHandler;

public class Commander extends CommandHandler {

   public Commander(WildGuard plugin) {
      super(plugin, "wildguard");
      registerCommand("reload", new ReloadCommand());
   }

   @Override
   public boolean noArgs(CommandSender sender, Command command, String label) {
      sender.sendMessage(ChatColor.GRAY + "=================");
      sender.sendMessage(ChatColor.GOLD + "WildGuard " + ChatColor.AQUA + "v" + plugin.getDescription().getVersion());
      sender.sendMessage(ChatColor.GOLD + "By " + plugin.getDescription().getAuthors().get(0));
      sender.sendMessage(ChatColor.GRAY + "=================");
      return true;
   }

   @Override
   public boolean unknownCommand(CommandSender sender, Command command, String label, String[] args) {
      sender.sendMessage(ChatColor.GRAY + plugin.getTag() + ChatColor.RED + " Unknown command: " + ChatColor.WHITE + args[0]);
      return true;
   }

}
