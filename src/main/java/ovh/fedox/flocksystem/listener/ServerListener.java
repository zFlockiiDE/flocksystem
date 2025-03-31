package ovh.fedox.flocksystem.listener;


import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.mineacademy.bfo.annotation.AutoRegister;
import org.mineacademy.bfo.remain.Remain;
import ovh.fedox.flocksystem.FlockSystem;
import ovh.fedox.flocksystem.settings.Settings;
import ovh.fedox.flocksystem.util.ColorUtil;

import java.util.List;

/**
 * ServerListener.java -
 * <p>
 * Created on 3/30/2025 at 4:17 PM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

@AutoRegister
public final class ServerListener implements Listener {

	@EventHandler
	public void onServerConnect(ServerConnectEvent event) {
		StringBuilder header = new StringBuilder();
		StringBuilder footer = new StringBuilder();

		List<String> headerList = Settings.Tablist.HEADER;
		List<String> footerList = Settings.Tablist.FOOTER;

		for (String s : headerList) {
			header.append(s).append("\n");
		}

		for (String s : footerList) {
			footer.append(s).append("\n");
		}

		String formattedHeader = header.toString().replaceAll("%online%", FlockSystem.getInstance().getProxy().getOnlineCount() + "");
		String formattedFooter = footer.toString().replaceAll("%online%", FlockSystem.getInstance().getProxy().getOnlineCount() + "");

		for (ProxiedPlayer player : Remain.getOnlinePlayers()) {
			player.setTabHeader(TextComponent.fromLegacy(ColorUtil.format(formattedHeader)), TextComponent.fromLegacy(ColorUtil.format(formattedFooter)));
		}

		event.getPlayer().setTabHeader(TextComponent.fromLegacy(ColorUtil.format(formattedHeader)), TextComponent.fromLegacy(ColorUtil.format(formattedFooter)));
	}

}
