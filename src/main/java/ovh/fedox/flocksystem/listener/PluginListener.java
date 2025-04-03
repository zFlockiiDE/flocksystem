package ovh.fedox.flocksystem.listener;


import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.mineacademy.bfo.Common;
import org.mineacademy.bfo.annotation.AutoRegister;
import org.mineacademy.bfo.remain.Remain;

/**
 * PluginListener.java -
 * <p>
 * Created on 4/3/2025 at 3:11 PM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

@AutoRegister
public final class PluginListener implements Listener {

	@EventHandler
	public void onPluginMessage(PluginMessageEvent event) {
		if (event.getTag().equals("BungeeCord")) {
			// Lese die Nachricht
			ByteArrayDataInput input = ByteStreams.newDataInput(event.getData());
			String message = input.readUTF();  // Stelle sicher, dass du die Nachricht korrekt liest
			String[] parts = message.split("\\|");

			if (parts.length == 2 && parts[0].equals("kickPlayer")) {
				String playerUUID = parts[1];
				ProxiedPlayer player = Remain.getPlayer(playerUUID);

				if (player != null) {
					// Kicken des Spielers vom gesamten Netzwerk
					player.disconnect("You have been permanently banned from the network.");
				}
			} else {
				// Füge Logik hinzu, falls das Nachrichtformat falsch ist
				Common.logFramed("Received an invalid message: ", message);
			}
		}
	}
}
