# TimeSync
A simple plugin that synchronizes the real time with the game time on **normal** worlds
because the effect of changing the world time like **nether** or **the end** is
imperceptible.

## Blinking sun / moon
**Explanation**:
The time in the game is synchronized every 1 real minute (higher accuracy does not make sense),
this causes the sun or moon to blink (depends on the time of day) because the plugin refreshes
time slower than the server.

**Solution**:
The solution is very simple, set the ``doDaylightCycle`` rule on your world to ``false``.
<br>
If you don't know how to do this, copy this command: ``/gamerule doDaylightCycle false`` and
paste this in a world where you want to stop the server from shifting times.
