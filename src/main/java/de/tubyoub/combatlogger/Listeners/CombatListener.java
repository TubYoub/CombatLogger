package de.tubyoub.combatlogger.Listeners;

import de.tubyoub.combatlogger.CombatLoggerPlugin;
import de.tubyoub.combatlogger.CombatManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CombatListener implements Listener {

    private CombatManager combatManager;

    public CombatListener(CombatLoggerPlugin plugin, int combatTimeoutInSeconds) {
        this.combatManager = new CombatManager(plugin, combatTimeoutInSeconds);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        combatManager.handlePlayerQuit(player);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        combatManager.handlePlayerDeath(player);
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player damager = (Player) event.getDamager();
            Player damaged = (Player) event.getEntity();
            combatManager.handlePlayerHit(damager);
            combatManager.handlePlayerHit(damaged);
        }
    }
}
