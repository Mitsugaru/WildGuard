package com.mitsugaru.wildguard.services;

import org.bukkit.command.CommandSender;

import com.mitsugaru.wildguard.permissions.PermissionNode;

public interface IPermissionHandler {

   /**
    * Check if the sender has the given PermissionNode.
    * 
    * @param sender
    *           - Sender to check.
    * @param node
    *           - PermissionNode to use.
    * @return True if sender has the permission node. Else false.
    */
   boolean hasPermissionNode(CommandSender sender, PermissionNode node);

}
