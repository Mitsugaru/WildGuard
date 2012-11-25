package com.mitsugaru.wildguard.config;

import org.bukkit.configuration.ConfigurationSection;

import com.mitsugaru.wildguard.WildGuard;
import com.mitsugaru.wildguard.config.node.RootNode;
import com.mitsugaru.wildguard.services.ModularConfig;

public class RootConfig extends ModularConfig<WildGuard> {

   public RootConfig(WildGuard plugin) {
      super(plugin);
   }

   @Override
   public void save() {
      plugin.saveConfig();
   }

   @Override
   public void set(String path, Object value) {
      plugin.getConfig().set(path, value);
   }

   @Override
   public void reload() {
      plugin.reloadConfig();
   }

   @Override
   public void loadSettings(ConfigurationSection config) {
      for(final RootNode node : RootNode.values()) {
         updateOption(node);
      }
   }

   @Override
   public void loadDefaults(ConfigurationSection config) {
      for(final RootNode node : RootNode.values()) {
         if(!config.contains(node.getPath())) {
            config.set(node.getPath(), node.getDefaultValue());
         }
      }
   }

   @Override
   public void boundsCheck() {
   }

}
