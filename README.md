# TimeSync
A simple plugin that synchronizes the real time with the time in the game.

## Blinking sun / moon
**Explanation**:
The time in the game is synchronized every 1 real minute (higher accuracy does not make sense),
this causes the sun or moon to blink (depends on the time of day) because the plugin refreshes
time slower than the server.

**Solution**:
The solution is very simple, set the ``doDaylightCycle`` rule on your world to ``false``.
<br>
If you don't know how to do this, copy this command: ``/gamerule doDaylightCycle false``
