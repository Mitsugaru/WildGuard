package com.mitsugaru.wildguard.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import com.mitsugaru.wildguard.WildGuard;
import com.mitsugaru.wildguard.services.ConfigNode;
import com.mitsugaru.wildguard.services.ModularConfig;

public class BlacklistConfig extends ModularConfig<WildGuard> {

   private File file;
   private YamlConfiguration config;
   private final Set<String> placeItemList = new HashSet<String>();
   private final Set<String> breakItemList = new HashSet<String>();

   public BlacklistConfig(WildGuard plugin) {
      super(plugin);
      file = new File(plugin.getDataFolder().getAbsolutePath() + "/list.yml");
      config = YamlConfiguration.loadConfiguration(file);
      reload();
      save();
   }

   public Set<String> getPlaceItemList() {
      return placeItemList;
   }

   public Set<String> getBreakItemList() {
      return breakItemList;
   }

   @Override
   public void save() {
      try {
         config.save(file);
      } catch(IOException e) {
         plugin.getLogger().severe("File I/O Exception on saving player lives");
         e.printStackTrace();
      }
   }

   @Override
   public void set(String path, Object value) {
      config.set(path, value);
   }

   @Override
   public void reload() {
      // Reload config from file.
      try {
         config.load(file);
         placeItemList.clear();
         breakItemList.clear();
         loadSettings(config);
      } catch(FileNotFoundException e) {
         e.printStackTrace();
      } catch(IOException e) {
         e.printStackTrace();
      } catch(InvalidConfigurationException e) {
         e.printStackTrace();
      }
   }

   @Override
   public void loadSettings(ConfigurationSection config) {
      // TODO load items into hashmap
      List<String> list = config.getStringList("place");
      if(list != null) {
         placeItemList.addAll(list);
      }
      list = config.getStringList("break");
      if(list != null) {
         breakItemList.addAll(list);
      }
   }

   /*
    * Invalid operations below.
    */

   @Override
   public void loadDefaults(ConfigurationSection config) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void boundsCheck() {
   }

   @Override
   public void updateOption(ConfigNode node) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void set(ConfigNode node, Object value) {
      throw new UnsupportedOperationException();
   }

   @Override
   public int getInt(ConfigNode node) {
      throw new UnsupportedOperationException();
   }

   @Override
   public String getString(ConfigNode node) {
      throw new UnsupportedOperationException();
   }

   @Override
   public List<String> getStringList(ConfigNode node) {
      throw new UnsupportedOperationException();
   }

   @Override
   public double getDouble(ConfigNode node) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean getBoolean(ConfigNode node) {
      throw new UnsupportedOperationException();
   }

}
