package ovh.fedox.flocksystem.command;


import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.mineacademy.bfo.annotation.AutoRegister;
import org.mineacademy.bfo.command.SimpleCommand;
import ovh.fedox.flocksystem.FlockSystem;
import ovh.fedox.flocksystem.util.SoundUtil;

/**
 * HubCommand.java - Command to get to the hub
 * <p>
 * Created on 3/31/2025 at 1:43 PM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

@AutoRegister
public final class HubCommand extends SimpleCommand {

	public HubCommand() {
		super("hub|l|lobby|spawn|leave");

		setDescription("Teleportiere dich zu der Hub");
		setPermission(null);
	}

	@Override
	protected void onCommand() {
		checkConsole();

		final ProxiedPlayer player = getPlayer();
		final String serverName = player.getServer().getInfo().getName();

		if (serverName.equalsIgnoreCase("lobby")) {
			tellError("Du bist bereits auf dem Lobby-Server.");
			SoundUtil.playSound(player, SoundUtil.SoundType.FAILURE);
		} else {
			player.connect(FlockSystem.getInstance().getProxy().getServerInfo("lobby"));
			tellSuccess("Du wirst zu der Lobby teleportiert.");
			SoundUtil.playSound(player, SoundUtil.SoundType.SUCCESS);
		}
	}
}
