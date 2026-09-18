package com.example.kiss;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KissCommand implements CommandExecutor {
   private final KissPlugin plugin;
   private final MessageUtils messageUtils;
   private final String actionType;

   public KissCommand(KissPlugin plugin, String actionType) {
      this.plugin = plugin;
      this.messageUtils = plugin.getMessageUtils();
      this.actionType = actionType;
   }

   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (!(sender instanceof Player player)) {
         this.messageUtils.sendMessage(sender, "player-only");
         return true;
      } else if (!player.hasPermission("kiss.use")) {
         this.messageUtils.sendMessage(player, "no-permission");
         return true;
      } else if (args.length < 1) {
         this.messageUtils.sendMessage(player, "player-not-found", "player", "?");
         return true;
      } else {
         String targetName = args[0];
         Player target = Bukkit.getPlayer(targetName);
         if (target == null) {
            this.messageUtils.sendMessage(player, "player-not-found", "player", targetName);
            return true;
         } else if (target.equals(player)) {
            this.messageUtils.sendMessage(player, "cannot-kiss-self");
            return true;
         } else if (this.plugin.isInCooldown(player.getUniqueId())) {
            int remaining = this.plugin.getRemainingCooldown(player.getUniqueId());
            this.messageUtils.sendMessage(player, "cooldown", "time", String.valueOf(remaining));
            return true;
         } else {
            this.executeAction(player, target);
            this.plugin.setCooldown(player.getUniqueId());
            return true;
         }
      }
   }

   private void executeAction(Player player, Player target) {
      String actionKey = this.actionType;
      String senderMsgKey = actionKey + "-sender";
      String targetMsgKey = actionKey + "-target";
      String broadcastMsgKey = actionKey + "-broadcast";
      this.messageUtils.sendMessage(player, senderMsgKey, "target", target.getName());
      this.messageUtils.sendMessage(target, targetMsgKey, "player", player.getName());

      for(Player online : Bukkit.getOnlinePlayers()) {
         if (!online.equals(player) && !online.equals(target)) {
            this.messageUtils.sendMessage(online, broadcastMsgKey, "player", player.getName(), "target", target.getName());
         }
      }

      if (this.plugin.isParticlesEnabled()) {
         this.playParticles(player, target);
      }

      if (this.plugin.isSoundsEnabled()) {
         this.playSound(player, target);
      }

      MessageUtils var10002 = this.messageUtils;
      String var9 = this.actionType;
      this.messageUtils.sendActionBar(player, MessageUtils.colorize("&d❤ " + var9 + " " + target.getName() + " ❤"));
      MessageUtils var10000 = this.messageUtils;
      MessageUtils var10 = this.messageUtils;
      String var11 = player.getName();
      var10000.sendActionBar(target, MessageUtils.colorize("&d❤ " + var11 + " " + this.actionType + " you ❤"));
      this.messageUtils.sendTitle(player, "&d" + this.actionType.toUpperCase() + "!", "&7" + target.getName(), 10, 20, 10);
   }

   private void playParticles(Player player, Player target) {
      String particleType = this.plugin.getParticleType();

      Particle particle;
      try {
         particle = Particle.valueOf(particleType);
      } catch (IllegalArgumentException var15) {
         particle = Particle.HEART;
      }

      player.getWorld().spawnParticle(particle, player.getLocation().add((double)0.0F, (double)1.0F, (double)0.0F), 20, (double)0.5F, (double)0.5F, (double)0.5F, 0.1);
      target.getWorld().spawnParticle(particle, target.getLocation().add((double)0.0F, (double)1.0F, (double)0.0F), 20, (double)0.5F, (double)0.5F, (double)0.5F, 0.1);
      double distance = player.getLocation().distance(target.getLocation());

      for(double t = (double)0.0F; t <= (double)1.0F; t += 0.05) {
         double x = player.getLocation().getX() + (target.getLocation().getX() - player.getLocation().getX()) * t;
         double y = player.getLocation().getY() + (double)1.0F + (target.getLocation().getY() - player.getLocation().getY()) * t;
         double z = player.getLocation().getZ() + (target.getLocation().getZ() - player.getLocation().getZ()) * t;
         player.getWorld().spawnParticle(particle, x, y, z, 1, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F);
      }

   }

   private void playSound(Player player, Player target) {
      String soundName;
      switch (this.actionType) {
         case "hug" -> soundName = this.plugin.getHugSound();
         case "lick" -> soundName = this.plugin.getLickSound();
         default -> soundName = this.plugin.getKissSound();
      }

      try {
         sound = Sound.valueOf(soundName);
      } catch (IllegalArgumentException var6) {
         sound = Sound.ENTITY_VILLAGER_YES;
      }

      player.playSound(player.getLocation(), sound, 1.0F, 1.0F);
      target.playSound(target.getLocation(), sound, 1.0F, 1.0F);
      player.getWorld().playSound(player.getLocation(), sound, 0.5F, 1.0F);
   }
}
