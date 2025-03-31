package ovh.fedox.flocksystem.command.maintenance;


import org.mineacademy.bfo.command.SimpleCommandGroup;
import org.mineacademy.bfo.command.SimpleSubCommand;
import ovh.fedox.flocksystem.FlockSystem;
import ovh.fedox.flocksystem.model.MaintenanceSetting;
import ovh.fedox.flocksystem.repository.MaintenanceRepository;
import ovh.fedox.flocksystem.util.SoundUtil;

import java.util.Optional;

/**
 * MaintenanceToggleCommand.java -
 * <p>
 * Created on 3/30/2025 at 5:31 AM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

public final class MaintenanceToggleCommand extends SimpleSubCommand {


	public MaintenanceToggleCommand(SimpleCommandGroup parent) {
		super(parent, "toggle");

		setDescription("Schalte den Wartungsmodus um");
	}

	@Override
	protected void onCommand() {
		checkConsole();

		MaintenanceRepository maintenanceRepository = FlockSystem.getMongoManager().getMaintenanceRepository();
		Optional<MaintenanceSetting> maintenance = FlockSystem.getMongoManager().getMaintenanceRepository().getMaintenanceSetting();
		MaintenanceSetting maintenanceSetting = maintenance.orElseGet(MaintenanceSetting::new);

		boolean maintenanceMode = maintenanceSetting.isMaintenanceMode();
		maintenanceSetting.setMaintenanceMode(!maintenanceMode);
		maintenanceRepository.save(maintenanceSetting);

		tellSuccess("Wartungsmodus erfolgreich " + (maintenanceMode ? "deaktiviert" : "aktiviert"));
		SoundUtil.playSound(getPlayer(), SoundUtil.SoundType.SUCCESS);
	}
}
