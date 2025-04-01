package ovh.fedox.flocksystem;


import lombok.Getter;
import net.md_5.bungee.api.plugin.Listener;
import org.mineacademy.bfo.Common;
import org.mineacademy.bfo.plugin.SimplePlugin;
import ovh.fedox.flocksystem.database.MongoDBManager;
import ovh.fedox.flocksystem.database.RedisManager;
import ovh.fedox.flocksystem.settings.Settings;

/**
 * FlockSystem.java - Main class of the plugin
 * <p>
 * Created on 3/30/2025 at 12:17 AM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

@Getter
public final class FlockSystem extends SimplePlugin implements Listener {

	@Getter
	public static MongoDBManager mongoManager;

	@Getter
	public static RedisManager redisManager;

	@Override
	protected void onReloadablesStart() {
		Common.setTellPrefix("&8&l➽ &a&lFlockSystem &8&l•&7 ");

		String connectionString = Settings.MongoDB.MONGO_CONNECTION_STRING;
		String database = Settings.MongoDB.MONGO_DATABASE;

		mongoManager = new MongoDBManager(connectionString, database);

		RedisManager.connect();
		RedisManager.getJedis().keys("players:*").forEach(RedisManager.getJedis()::del);
	}

	@Override
	protected void onPluginStart() {
		Common.setTellPrefix("&8&l➽ &a&lFlockSystem &8&l•&7 ");
	}

	@Override
	protected void onPluginStop() {
		mongoManager.close();
		RedisManager.close();
	}
}
