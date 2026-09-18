# Kiss Plugin - README

## English

### 📋 Description
A fun social interaction plugin that allows players to express emotions through kisses, hugs, and licks. Perfect for community servers looking to add some fun interactions!

### ✨ Features
- 💋 **Kiss** - Show your love with heart particles
- 🤗 **Hug** - Warm hugs with cute sounds  
- 👅 **Lick** - Playful interaction with slime sounds
- 🎨 **Particle Effects** - Heart particles floating between players
- 🔊 **Custom Sounds** - Each action has unique sound effects
- ⏱️ **Cooldown System** - Prevent spam (default 3 seconds)
- 📢 **Action Bar Messages** - Real-time feedback
- 🎬 **Title Animations** - Epic title display on interaction
- 🌍 **Broadcast** - Everyone nearby sees the interaction

### 📋 Commands

| Command | Description | Permission |
|---------|-------------|------------|
| `/kiss <player>` | Kiss another player | kiss.use |
| `/hug <player>` | Hug another player | kiss.use |
| `/lick <player>` | Lick another player | kiss.use |

### 🔐 Permissions

| Permission | Description | Default |
|------------|-------------|---------|
| `kiss.use` | Use all interaction commands | `true` |
| `kiss.*` | All permissions | `op` |

### 🎮 Usage Examples

```minecraft
# Kiss someone
/kiss Steve

# Hug your friend  
/hug Alex

# Playful lick
/lick Bob
```

### ⚙️ Configuration

File: `plugins/Kiss/config.yml`

```yaml
# Particle settings
particles:
  enabled: true
  type: "HEART"  # HEART, CLOUD, NOTE, END_ROD

# Sound settings  
sounds:
  enabled: true
  kiss: "ENTITY_VILLAGER_YES"
  hug: "ENTITY_LLAMA_EAT"
  lick: "ENTITY_SLIME_SQUISH"

# Cooldown (seconds)
cooldown: 3
```

### 🎯 Effects Preview

| Action | Particle | Sound | Title |
|--------|----------|-------|-------|
| Kiss | 💖 HEART | Villager Yes | KISS! |
| Hug | 💝 HEART | Llama Eat | HUG! |
| Lick | 💗 HEART | Slime Squish | LICK! |

### 📦 Installation

1. Download `kiss-plugin-1.0.0.jar`
2. Place in server's `plugins` folder
3. Restart server or run `/plugman load Kiss`
4. Configure in `plugins/Kiss/config.yml`

### 🔌 PlaceholderAPI Support

| Variable | Description |
|----------|-------------|
| `%kiss_count%` | Total kisses given |
| `%hug_count%` | Total hugs given |
| `%lick_count%` | Total licks given |

### 💾 Requirements

- **Minecraft**: 1.17 - 1.21.11
- **Java**: 17 or higher
- **Server**: Paper / Purpur / Spigot

### 📌 Notes

- Players cannot kiss themselves
- Configurable cooldown to prevent spam  
- All messages support color codes (&a, &c, etc.)
- Particles work across all Minecraft versions

---

## 中文

### 📋 插件介绍
一个有趣的社交互动插件，让玩家可以通过亲吻、拥抱、舔来表达情感。非常适合需要增添趣味互动的社区服务器！

### ✨ 功能特点
- 💋 **亲吻** - 用爱心粒子表达爱意
- 🤗 **拥抱** - 温暖的拥抱加上可爱的音效
- 👅 **舔** - 有趣的互动，史莱姆音效
- 🎨 **粒子效果** - 玩家之间飘浮的爱心粒子
- 🔊 **自定义音效** - 每个动作都有独特的声音
- ⏱️ **冷却系统** - 防止刷屏（默认3秒）
- 📢 **动作栏消息** - 实时反馈
- 🎬 **标题动画** - 炫酷的标题展示
- 🌍 **广播** - 附近所有玩家都能看到互动

### 📋 命令列表

| 命令 | 描述 | 权限 |
|------|------|------|
| `/kiss <玩家>` | 亲吻其他玩家 | kiss.use |
| `/hug <玩家>` | 拥抱其他玩家 | kiss.use |
| `/lick <玩家>` | 舔其他玩家 | kiss.use |

### 🔐 权限节点

| 权限 | 描述 | 默认值 |
|------|------|--------|
| `kiss.use` | 使用所有互动命令 | `true` |
| `kiss.*` | 所有权限 | `op` |

### 🎮 使用示例

```minecraft
# 亲吻某人
/kiss Steve

# 拥抱朋友
/hug Alex

# 淘气地舔一下
/lick Bob
```

### ⚙️ 配置说明

配置文件：`plugins/Kiss/config.yml`

```yaml
# 粒子设置
particles:
  enabled: true
  type: "HEART"  # HEART, CLOUD, NOTE, END_ROD

# 音效设置
sounds:
  enabled: true
  kiss: "ENTITY_VILLAGER_YES"
  hug: "ENTITY_LLAMA_EAT"
  lick: "ENTITY_SLIME_SQUISH"

# 冷却时间（秒）
cooldown: 3
```

### 🎯 效果预览

| 动作 | 粒子 | 音效 | 标题 |
|------|------|------|------|
| 亲吻 | 💖 爱心 | 村民赞同 | 亲吻！ |
| 拥抱 | 💝 爱心 | 羊驼吃草 | 拥抱！ |
| 舔 | 💗 爱心 | 史莱姆挤压 | 舔！ |

### 📦 安装方法

1. 下载 `kiss-plugin-1.0.0.jar`
2. 放入服务器的 `plugins` 文件夹
3. 重启服务器或运行 `/plugman load Kiss`
4. 在 `plugins/Kiss/config.yml` 中配置

### 🔌 PlaceholderAPI 支持

| 变量 | 说明 |
|------|------|
| `%kiss_count%` | 总亲吻次数 |
| `%hug_count%` | 总拥抱次数 |
| `%lick_count%` | 总舔次数 |

### 💾 环境要求

- **Minecraft**: 1.17 - 1.21.11
- **Java**: 17 或更高
- **服务端**: Paper / Purpur / Spigot

### 📌 注意事项

- 不能亲吻自己
- 可配置冷却时间防止刷屏
- 所有消息支持颜色代码（&a, &c 等）
- 粒子效果在所有 Minecraft 版本都能工作

---

## 📝 Version History

**v1.0.0** (2026-05-13)
- Initial release
- Added kiss, hug, lick commands
- Particle effects and sounds
- Cooldown system
- Configurable messages

**Spread love on your server!** 💕
