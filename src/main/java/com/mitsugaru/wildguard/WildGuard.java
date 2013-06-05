package com.mitsugaru.wildguard;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.mitsugaru.wildguard.command.handler.Commander;
import com.mitsugaru.wildguard.config.BlacklistConfig;
import com.mitsugaru.wildguard.config.RootConfig;
import com.mitsugaru.wildguard.listeners.BlockListener;
import com.mitsugaru.wildguard.permissions.PermissionNode;
import com.mitsugaru.wildguard.services.IPermissionHandler;

public class WildGuard extends JavaPlugin implements IPermissionHandler {

   private RootConfig root;
   private BlacklistConfig blacklist;

   private static final String TAG = "[WildGuard]";

   @Override
   public void onDisable() {
      // TODO Auto-generated method stub
      super.onDisable();
   }

   @Override
   public void onEnable() {
      root = new RootConfig(this);
      blacklist = new BlacklistConfig(this);
      this.getServer().getPluginManager().registerEvents(new BlockListener(this), this);
      this.getCommand("wildguard").setExecutor(new Commander(this));
   }

   public String getTag() {
      return TAG;
   }

   public RootConfig getRootConfig() {
      return root;
   }

   public BlacklistConfig getBlacklist() {
      return blacklist;
   }

   @Override
   public boolean hasPermissionNode(CommandSender sender, PermissionNode node) {
      return sender.hasPermission(node.getNode());
   }

}
