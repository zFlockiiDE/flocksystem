package ovh.fedox.flocksystem.listener;


import lombok.var;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.mineacademy.bfo.annotation.AutoRegister;
import ovh.fedox.flocksystem.FlockSystem;
import ovh.fedox.flocksystem.database.model.MaintenanceSetting;
import ovh.fedox.flocksystem.database.repository.MaintenanceRepository;
import ovh.fedox.flocksystem.settings.Settings;

import java.util.Optional;

import static ovh.fedox.flocksystem.util.ColorUtil.format;

/**
 * ProxyListener.java - Listening for proxy stuff
 * <p>
 * Created on 3/30/2025 at 4:02 AM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

@AutoRegister
public final class ProxyListener implements Listener {

	@EventHandler
	public void onProxyPing(ProxyPingEvent event) {
		MaintenanceRepository maintenanceRepository = FlockSystem.getMongoManager().getMaintenanceRepository();
		Optional<MaintenanceSetting> maintenance = FlockSystem.getMongoManager().getMaintenanceRepository().getMaintenanceSetting();
		MaintenanceSetting maintenanceSetting = maintenance.orElseGet(MaintenanceSetting::new);

		boolean maintenanceMode = maintenanceSetting.isMaintenanceMode();

		String maintenanceReason = maintenanceSetting.getMaintenanceReason() == null ? "N/A" : maintenanceSetting.getMaintenanceReason();
		String maintenanceEnd = maintenanceSetting.getMaintenanceEnd() == null ? "N/A" : maintenanceSetting.getMaintenanceEnd();

		var response = event.getResponse();
		var version = response.getVersion();


		if (maintenanceMode) {
			version.setProtocol(47);
			version.setName("§4Wartungen...");
			response.setDescriptionComponent(TextComponent.fromLegacy(
					format(
							Settings.MAINTENANCE_MOTD.replaceAll("%end_time%", maintenanceEnd).replaceAll("%reason%", maintenanceReason)
					)));
			event.setResponse(response);
			return;
		} else {
			response.setDescriptionComponent(TextComponent.fromLegacy(
					format(
							Settings.MOTD
					)));
		}

		response.setPlayers(new ServerPing.Players(6969, FlockSystem.getInstance().getProxy().getOnlineCount(), null));
		event.setResponse(response);
	}

}
