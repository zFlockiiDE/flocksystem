package ovh.fedox.flocksystem.command;


import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.mineacademy.bfo.annotation.AutoRegister;
import org.mineacademy.bfo.command.SimpleCommand;
import org.mineacademy.bfo.remain.Remain;
import ovh.fedox.flocksystem.util.SoundUtil;

/**
 * BauserverCommand.java - Send a player to the Bauserver
 * <p>
 * Created on 4/1/2025 at 10:00 PM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

@AutoRegister
public final class BauserverCommand extends SimpleCommand {

	public BauserverCommand() {
		super("bau/bauserver");

		setDescription("Teleportiere dich zum Bauserver");
	}

	@Override
	protected void onCommand() {
		checkConsole();

		final ProxiedPlayer player = getPlayer();
		final ServerInfo target = Remain.getServers().stream().filter(s -> s.getName().equalsIgnoreCase("bauserver")).findFirst().orElse(null);

		if (target == null) {
			tellError("Der Bauserver konnte nicht gefunden werden.");
			return;
		}

		player.connect(target);
		tellSuccess("Du wirst zum Bauserver verbunden.");
		SoundUtil.playSound(player, SoundUtil.SoundType.SUCCESS);
	}
}
