package com.example.kiss;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class KissAdminCommand implements CommandExecutor {
   private final KissPlugin plugin;
   private final MessageUtils messageUtils;

   public KissAdminCommand(KissPlugin plugin) {
      this.plugin = plugin;
      this.messageUtils = plugin.getMessageUtils();
   }

   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (!sender.hasPermission("kiss.admin")) {
         this.messageUtils.sendMessage(sender, "no-permission");
         return true;
      } else if (args.length == 0) {
         this.sendHelp(sender);
         return true;
      } else if (args[0].equalsIgnoreCase("reload")) {
         this.plugin.reloadConfig();
         this.messageUtils.sendMessage(sender, "reload-success");
         this.plugin.getLogger().info("配置已重载 by " + sender.getName());
         return true;
      } else {
         this.sendHelp(sender);
         return true;
      }
   }

   private void sendHelp(CommandSender sender) {
      this.messageUtils.sendRawMessage(sender, MessageUtils.colorize("&6=== Kiss插件帮助 ==="));
      this.messageUtils.sendRawMessage(sender, MessageUtils.colorize("&e/kiss <玩家> &7- 亲吻玩家"));
      this.messageUtils.sendRawMessage(sender, MessageUtils.colorize("&e/hug <玩家> &7- 拥抱玩家"));
      this.messageUtils.sendRawMessage(sender, MessageUtils.colorize("&e/lick <玩家> &7- 舔玩家"));
      this.messageUtils.sendRawMessage(sender, MessageUtils.colorize("&e/kissadmin reload &7- 重载配置"));
      this.messageUtils.sendRawMessage(sender, MessageUtils.colorize("&6====================="));
   }
}
