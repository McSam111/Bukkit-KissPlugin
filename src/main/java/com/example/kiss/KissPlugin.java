package com.example.kiss;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class KissPlugin extends JavaPlugin {
   private static KissPlugin instance;
   private FileConfiguration config;
   private MessageUtils messageUtils;
   private final Map<UUID, Long> cooldowns = new HashMap();
   private String particleType;
   private boolean particlesEnabled;
   private boolean soundsEnabled;
   private String kissSound;
   private String hugSound;
   private String lickSound;
   private boolean actionBarEnabled;
   private int cooldownTime;

   public void onEnable() {
      instance = this;
      this.saveDefaultConfig();
      this.reloadConfig();
      this.config = this.getConfig();
      this.loadConfig();
      this.messageUtils = new MessageUtils(this);
      this.getCommand("kiss").setExecutor(new KissCommand(this, "kiss"));
      this.getCommand("hug").setExecutor(new KissCommand(this, "hug"));
      this.getCommand("lick").setExecutor(new KissCommand(this, "lick"));
      this.getCommand("kissadmin").setExecutor(new KissAdminCommand(this));
      this.getLogger().info("Kiss插件已启用！");
      this.getLogger().info("支持命令: /kiss, /hug, /lick");
   }

   public void onDisable() {
      this.getLogger().info("Kiss插件已禁用！");
   }

   public void reloadConfig() {
      super.reloadConfig();
      this.config = this.getConfig();
      this.loadConfig();
   }

   private void loadConfig() {
      this.particlesEnabled = this.config.getBoolean("particles.enabled", true);
      this.particleType = this.config.getString("particles.type", "HEART");
      this.soundsEnabled = this.config.getBoolean("sounds.enabled", true);
      this.kissSound = this.config.getString("sounds.kiss", "ENTITY_VILLAGER_YES");
      this.hugSound = this.config.getString("sounds.hug", "ENTITY_LLAMA_EAT");
      this.lickSound = this.config.getString("sounds.lick", "ENTITY_SLIME_SQUISH");
      this.actionBarEnabled = this.config.getBoolean("action-bar", true);
      this.cooldownTime = this.config.getInt("cooldown", 3);
      this.cooldowns.clear();
   }

   public boolean isInCooldown(UUID uuid) {
      Long cooldownEnd = (Long)this.cooldowns.get(uuid);
      if (cooldownEnd == null) {
         return false;
      } else {
         return System.currentTimeMillis() < cooldownEnd;
      }
   }

   public int getRemainingCooldown(UUID uuid) {
      Long cooldownEnd = (Long)this.cooldowns.get(uuid);
      if (cooldownEnd == null) {
         return 0;
      } else {
         long remaining = (cooldownEnd - System.currentTimeMillis()) / 1000L;
         return (int)Math.max(0L, remaining);
      }
   }

   public void setCooldown(UUID uuid) {
      long endTime = System.currentTimeMillis() + (long)this.cooldownTime * 1000L;
      this.cooldowns.put(uuid, endTime);
   }

   public String getMessage(String key) {
      return this.config.getString("messages." + key, "&c消息未配置: " + key);
   }

   public String getPrefix() {
      return this.config.getString("messages.prefix", "&8[&d&lKiss&8] &r");
   }

   public boolean isParticlesEnabled() {
      return this.particlesEnabled;
   }

   public String getParticleType() {
      return this.particleType;
   }

   public boolean isSoundsEnabled() {
      return this.soundsEnabled;
   }

   public String getKissSound() {
      return this.kissSound;
   }

   public String getHugSound() {
      return this.hugSound;
   }

   public String getLickSound() {
      return this.lickSound;
   }

   public boolean isActionBarEnabled() {
      return this.actionBarEnabled;
   }

   public int getCooldownTime() {
      return this.cooldownTime;
   }

   public static KissPlugin getInstance() {
      return instance;
   }

   public MessageUtils getMessageUtils() {
      return this.messageUtils;
   }
}
