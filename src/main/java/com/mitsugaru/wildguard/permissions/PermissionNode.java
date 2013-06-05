package com.mitsugaru.wildguard.permissions;

public enum PermissionNode {

   ENFORCE("enforce"),
   IGNORE("ignore"),
   ADMIN("admin");
   
   private static final String PREFIX = "WildGuard.";
   
   private String node;
   
   private PermissionNode(String path) {
      this.node = PREFIX + path;
   }
   
   public String getNode() {
      return node;
   }
}
