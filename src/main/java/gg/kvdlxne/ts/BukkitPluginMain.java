package gg.kvdlxne.ts;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
public final class BukkitPluginMain extends JavaPlugin implements Runnable {

  private static final String DO_DAYLIGHT_CYCLE = "doDaylightCycle";
  private final Set<World> worlds = new HashSet<>(6);

  @Override
  public void onEnable() {
    for (final World world : Bukkit.getWorlds()) {
      if ("false".equals(world.getGameRuleValue(DO_DAYLIGHT_CYCLE))) {
        world.setGameRuleValue(DO_DAYLIGHT_CYCLE, "true");
      }
      worlds.add(world);
    }
    Bukkit.getScheduler().runTaskTimerAsynchronously(this, this, 20L, 60 * 20L);
  }

  @Override
  public void onDisable() {
    for (final World world : worlds) {
      world.setGameRuleValue(DO_DAYLIGHT_CYCLE, "false");
    }
  }

  @Override
  public void run() {
    final LocalTime localTime = LocalTime.now();
    for (final World world : worlds) {
      final int minecraftMinutes = localTime.getMinute() * (1000 / 60);
      final int hours = localTime.getHour();
      world.setFullTime((hours * 1000 - (6 > hours ? 18000 : 6000)) + minecraftMinutes);
    }
  }
}
