package com.mitsugaru.wildguard.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Modular configuration class that utilizes a ConfigNode enumeration as easy
 * access and storage of configuration option values.
 * 
 * @author Mitsugaru
 */
public abstract class ModularConfig<T extends JavaPlugin> {
   /**
    * Root Java plugin reference
    */
   protected T plugin;
   /**
    * Cache of options for the config.
    */
   protected final Map<ConfigNode, Object> OPTIONS = new HashMap<ConfigNode, Object>();

   public ModularConfig(T plugin) {
      this.plugin = plugin;
   }

   /**
    * This updates a configuration option from the file.
    * 
    * @param node
    *           - ConfigNode to update.
    */
   @SuppressWarnings("unchecked")
   public void updateOption(final ConfigNode node) {
      final ConfigurationSection config = plugin.getConfig();
      switch(node.getVarType()) {
      case LIST: {
         List<String> list = config.getStringList(node.getPath());
         if(list == null) {
            list = (List<String>) node.getDefaultValue();
         }
         OPTIONS.put(node, list);
         break;
      }
      case DOUBLE: {
         OPTIONS.put(node, config.getDouble(node.getPath(), (Double) node.getDefaultValue()));
         break;
      }
      case STRING: {
         OPTIONS.put(node, config.getString(node.getPath(), (String) node.getDefaultValue()));
         break;
      }
      case INTEGER: {
         OPTIONS.put(node, config.getInt(node.getPath(), (Integer) node.getDefaultValue()));
         break;
      }
      case BOOLEAN: {
         OPTIONS.put(node, config.getBoolean(node.getPath(), (Boolean) node.getDefaultValue()));
         break;
      }
      default: {
         OPTIONS.put(node, config.get(node.getPath(), node.getDefaultValue()));
      }
      }
   }

   /**
    * Saves the config.
    */
   public abstract void save();

   /**
    * Force set the value for the given configuration node.
    * 
    * Note, there is no type checking with this method.
    * 
    * @param node
    *           - ConfigNode path to use.
    * @param o
    *           - Value to use.
    */
   public void set(final ConfigNode node, final Object value) {
      set(node.getPath(), value);
   }

   /**
    * Set the given path for the given value.
    * 
    * @param path
    *           - Path to use.
    * @param o
    *           - Value to use.
    */
   public abstract void set(final String path, final Object value);

   /**
    * Get the integer value of the node.
    * 
    * @param node
    *           - Node to use.
    * @return Value of the node. Returns -1 if unknown.
    */
   public int getInt(final ConfigNode node) {
      int i = -1;
      switch(node.getVarType()) {
      case INTEGER: {
         try {
            i = ((Integer) OPTIONS.get(node)).intValue();
         } catch(NullPointerException npe) {
            i = ((Integer) node.getDefaultValue()).intValue();
         }
         break;
      }
      default: {
         // TODO throw exception
         break;
      }
      }
      return i;
   }

   /**
    * Get the string value of the node.
    * 
    * @param node
    *           - Node to use.
    * @return Value of the node. Returns and empty string if unknown.
    */
   public String getString(final ConfigNode node) {
      String out = "";
      switch(node.getVarType()) {
      case STRING: {
         out = (String) OPTIONS.get(node);
         if(out == null) {
            out = (String) node.getDefaultValue();
         }
         break;
      }
      default: {
         // TODO throw exception
         break;
      }
      }
      return out;
   }

   /**
    * Get the list value of the node.
    * 
    * @param node
    *           - Node to use.
    * @return Value of the node. Returns an empty list if unknown.
    */
   @SuppressWarnings("unchecked")
   public List<String> getStringList(final ConfigNode node) {
      List<String> list = new ArrayList<String>();
      switch(node.getVarType()) {
      case LIST: {
         final ConfigurationSection config = plugin.getConfig();
         list = config.getStringList(node.getPath());
         if(list == null) {
            list = (List<String>) node.getDefaultValue();
         }
         break;
      }
      default: {
         // TODO throw exception
         break;
      }
      }
      return list;
   }

   /**
    * Get the double value of the node.
    * 
    * @param node
    *           - Node to use.
    * @return Value of the node. Returns 0 if unknown.
    */
   public double getDouble(final ConfigNode node) {
      double d = 0.0;
      switch(node.getVarType()) {
      case DOUBLE: {
         try {
            d = ((Double) OPTIONS.get(node)).doubleValue();
         } catch(NullPointerException npe) {
            d = ((Double) node.getDefaultValue()).doubleValue();
         }
         break;
      }
      default: {
         // TODO throw exception
         break;
      }
      }
      return d;
   }

   /**
    * Get the boolean value of the node.
    * 
    * @param node
    *           - Node to use.
    * @return Value of the node. Returns false if unknown.
    */
   public boolean getBoolean(final ConfigNode node) {
      boolean b = false;
      switch(node.getVarType()) {
      case BOOLEAN: {
         b = ((Boolean) OPTIONS.get(node)).booleanValue();
         break;
      }
      default: {
         // TODO throw exception
         break;
      }
      }
      return b;
   }

   /**
    * Reloads info from yaml file(s).
    */
   public abstract void reload();

   /**
    * Update settings that can be changed on the fly.
    * 
    * @param config
    *           - Main config to load from.
    */
   public abstract void loadSettings(final ConfigurationSection config);

   /**
    * Load defaults.
    * 
    * @param config
    *           - Main config to load to.
    */
   public abstract void loadDefaults(final ConfigurationSection config);

   /**
    * Check the bounds on the parameters to make sure that all config variables
    * are legal and usable by the plugin.
    */
   public abstract void boundsCheck();

}
