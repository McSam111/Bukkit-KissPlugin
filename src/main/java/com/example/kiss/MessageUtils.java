package com.example.kiss;

import java.time.Duration;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.Title.Times;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageUtils {
   private final KissPlugin plugin;

   public MessageUtils(KissPlugin plugin) {
      this.plugin = plugin;
   }

   public void sendMessage(CommandSender sender, String key, String... replacements) {
      String message = this.plugin.getMessage(key);

      for(int i = 0; i < replacements.length; i += 2) {
         if (i + 1 < replacements.length) {
            message = message.replace("{" + replacements[i] + "}", replacements[i + 1]);
         }
      }

      String var10001 = this.plugin.getPrefix();
      sender.sendMessage(colorize(var10001 + message));
   }

   public void sendRawMessage(CommandSender sender, String message) {
      sender.sendMessage(colorize(message));
   }

   public void sendActionBar(Player player, String message) {
      player.sendActionBar(Component.text(colorize(message)));
   }

   public void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
      player.showTitle(Title.title(Component.text(colorize(title)), Component.text(colorize(subtitle)), Times.times(Duration.ofMillis((long)fadeIn * 50L), Duration.ofMillis((long)stay * 50L), Duration.ofMillis((long)fadeOut * 50L))));
   }

   public static String colorize(String message) {
      return message == null ? "" : ChatColor.translateAlternateColorCodes('&', message);
   }
}
