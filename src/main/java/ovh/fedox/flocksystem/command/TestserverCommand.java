package ovh.fedox.flocksystem.command;


import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.mineacademy.bfo.annotation.AutoRegister;
import org.mineacademy.bfo.command.SimpleCommand;
import org.mineacademy.bfo.remain.Remain;
import ovh.fedox.flocksystem.util.SoundUtil;

/**
 * TestserverCommand.java - Teleports onto the testserver
 * <p>
 * Created on 4/3/2025 at 1:47 PM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

@AutoRegister
public final class TestserverCommand extends SimpleCommand {

	public TestserverCommand() {
		super("test/testserver");

		setDescription("Teleportiere dich zum Testserver");
	}

	@Override
	protected void onCommand() {
		checkConsole();

		final ProxiedPlayer player = getPlayer();
		final ServerInfo target = Remain.getServers().stream().filter(s -> s.getName().equalsIgnoreCase("testserver")).findFirst().orElse(null);

		if (target == null) {
			tellError("Der Testserver konnte nicht gefunden werden.");
			return;
		}

		player.connect(target);
		tellSuccess("Du wirst zum Testserver verbunden.");
		SoundUtil.playSound(player, SoundUtil.SoundType.SUCCESS);
	}
}
