package ovh.fedox.flocksystem.command.server;


import net.md_5.bungee.api.ChatColor;
import org.mineacademy.bfo.annotation.AutoRegister;
import org.mineacademy.bfo.command.SimpleCommandGroup;

/**
 * ServerCommandGroup.java - Server command group
 * <p>
 * Created on 3/30/2025 at 5:30 AM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

@AutoRegister
public final class ServerCommandGroup extends SimpleCommandGroup {

	public ServerCommandGroup() {
		super("server|s|servers");
	}

	@Override
	protected void registerSubcommands() {
		registerSubcommand(new ServerLockCommand(this));
		registerSubcommand(new ServerUnlockCommand(this));
	}

	@Override
	protected ChatColor getTheme() {
		return ChatColor.GREEN;
	}

	@Override
	protected String getCredits() {
		return "&7Dieses Plugin wurde für &fzFlockii &7erstellt.";
	}
}
