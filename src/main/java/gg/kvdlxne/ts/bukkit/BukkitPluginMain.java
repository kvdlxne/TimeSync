package gg.kvdlxne.ts.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDateTime;

public final class BukkitPluginMain extends JavaPlugin {

  @Override
  public void onEnable() {
    // bukkit does not support asynchronous time shift
    Bukkit.getScheduler().runTaskTimer(this, () -> {
      final LocalDateTime time = LocalDateTime.now();
      final int days = time.getDayOfYear() * 24000;
      final int hours = time.getHour();
      final int offset = 6 > hours ? 18000 : 6000;
      final double minutes = time.getMinute() * (1000.0d / 60.0d);
      final long fullTime = (long) Math.ceil(days + hours * 1000L - offset + minutes);
      Bukkit.getWorlds().forEach(world -> world.setFullTime(fullTime));
    }, 20L, 1200L);
  }
}
