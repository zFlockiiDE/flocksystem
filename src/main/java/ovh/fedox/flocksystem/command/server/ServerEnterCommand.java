package ovh.fedox.flocksystem.command.server;


import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.mineacademy.bfo.Common;
import org.mineacademy.bfo.command.SimpleCommandGroup;
import org.mineacademy.bfo.command.SimpleSubCommand;
import org.mineacademy.bfo.remain.Remain;
import ovh.fedox.flocksystem.util.SoundUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * ServerEnterCommand.java - Enter command for the server
 * <p>
 * Created on 4/1/2025 at 5:55 PM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

public class ServerEnterCommand extends SimpleSubCommand {

	public ServerEnterCommand(SimpleCommandGroup parent) {
		super(parent, "enter");

		setUsage("<server>");
		setMinArguments(1);
	}

	@Override
	protected void onCommand() {
		checkConsole();

		final ProxiedPlayer player = getPlayer();

		String server = args[0];

		Collection<ServerInfo> servers = Remain.getServers();
		ServerInfo target = servers.stream().filter(s -> s.getName().equalsIgnoreCase(server)).findFirst().orElse(null);

		List<String> serverNames = new ArrayList<>();

		for (ServerInfo serverInfo : servers) {
			serverNames.add(serverInfo.getName());
		}

		if (target == null) {
			tellError("Der Server konnte nicht gefunden werden. Verfügbare Server: " + Common.join(serverNames, ", "));
			SoundUtil.playSound(player, SoundUtil.SoundType.FAILURE);
			return;
		}

		SoundUtil.playSound(player, SoundUtil.SoundType.SUCCESS);

		player.connect(target);

		Common.tell(player, "Du wirst zum Server " + target.getName() + " verbunden.");
	}

}
