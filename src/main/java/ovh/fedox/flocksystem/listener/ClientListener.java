package ovh.fedox.flocksystem.listener;


import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.mineacademy.bfo.Common;
import org.mineacademy.bfo.annotation.AutoRegister;
import ovh.fedox.flocksystem.FlockSystem;
import ovh.fedox.flocksystem.model.MaintenanceSetting;
import ovh.fedox.flocksystem.repository.MaintenanceRepository;

import java.util.Optional;

/**
 * ClientListener.java - Listening for client stuff
 * <p>
 * Created on 3/30/2025 at 4:43 AM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

@AutoRegister
public final class ClientListener implements Listener {

	@EventHandler
	public void onClientConnect(PostLoginEvent event) {

		MaintenanceRepository maintenanceRepository = FlockSystem.getMongoManager().getMaintenanceRepository();
		Optional<MaintenanceSetting> maintenance = FlockSystem.getMongoManager().getMaintenanceRepository().getMaintenanceSetting();
		MaintenanceSetting maintenanceSetting = maintenance.orElseGet(MaintenanceSetting::new);

		boolean maintenanceMode = maintenanceSetting.isMaintenanceMode();

		if (maintenanceMode) {
			if (!event.getPlayer().hasPermission("flocki.team")) {
				Common.logFramed("Maintenance mode is enabled, disconnecting " + event.getPlayer().getName());
				event.getPlayer().disconnect("§4Wartungsmodus aktiviert\n\n§7Grund: §c" + maintenanceSetting.getMaintenanceReason() + "\n§7Vorrausichtliche Endzeit: §c" + maintenanceSetting.getMaintenanceEnd());

				for (ProxiedPlayer proxiedPlayer : FlockSystem.getInstance().getProxy().getPlayers()) {
					Common.tell(proxiedPlayer, "§7" + event.getPlayer().getName() + " §7hat versucht, den Server zu betreten, während der Wartungsmodus aktiviert ist.");
				}
			}
		}

	}

}
