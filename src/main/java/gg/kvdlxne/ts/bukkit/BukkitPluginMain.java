package gg.kvdlxne.ts.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Properties;

import static java.nio.charset.StandardCharsets.UTF_8;

@SuppressWarnings("unused")
public final class BukkitPluginMain extends JavaPlugin {

  @Override
  public void onEnable() {
    final String name = "settings.properties";
    this.saveResource(name, false);
    final File file = new File(this.getDataFolder(), name);
    final Properties settings = new Properties();
    try (final Reader reader = new InputStreamReader(new FileInputStream(file), UTF_8)) {
      settings.load(reader);
    } catch (final IOException e) {
      e.printStackTrace();
    }
    int hoursOffset;
    try {
      final String property = settings.getProperty("hoursOffset", "0");
      hoursOffset = Integer.parseInt(property);
    } catch (final NumberFormatException ignore) {
      hoursOffset = 0;
    }
    final ZoneId zoneId = ZoneOffset.ofHours(hoursOffset);
    Bukkit.getScheduler().runTaskTimer(this, () -> {
      final LocalDateTime time = LocalDateTime.now(zoneId);
      final int days = time.getDayOfYear() * 24000;
      final int hours = time.getHour();
      final int offset = 6 > hours ? 18000 : 6000;
      final double minutes = time.getMinute() * (1000.0d / 60.0d);
      final long fullTime = (long) Math.ceil(days + hours * 1000L - offset + minutes);
      Bukkit.getWorlds().forEach(world -> {
        if (World.Environment.NORMAL == world.getEnvironment()) {
          world.setFullTime(fullTime);
        }
      });
    }, 20L, 1200L);
  }
}
