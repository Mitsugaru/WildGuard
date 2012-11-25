package com.mitsugaru.wildguard.services;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.mitsugaru.wildguard.WildGuard;

public interface IWildCommand {

   /**
    * Execution method for the command.
    * 
    * @param sender
    *           - Sender of the command.
    * @param command
    *           - Command used.
    * @param label
    *           - Label.
    * @param args
    *           - Command arguments.
    * @return True if valid command and executed. Else false.
    */
   boolean execute(WildGuard plugin, CommandSender sender, Command command, String label, String[] args);
}
