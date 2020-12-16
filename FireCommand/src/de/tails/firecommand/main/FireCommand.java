package de.tails.firecommand.main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class FireCommand extends JavaPlugin implements CommandExecutor {

	@Override
	public void onEnable() {
		getCommand("fire").setExecutor(this);
	}

	@Override
	public void onDisable() {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player))
			return true;
		Player player = (Player) sender;
		if(!(player.hasPermission("fire.cmd"))) {
			player.sendMessage("§cDazu hast du keine Rechte!");
			return true;
		}
		if(args.length == 2) {
			Player target = Bukkit.getPlayer(args[0]);
			if(target != null) {
				if(istInteger(args[1])) {
					int time = Integer.parseInt(args[1]);
					if(time > 0) {
						target.setFireTicks(time * 20); // Der Spieler wird für die angegebene Anzahl
														// an Sekunden in Brand gesetzt! (* 20 da man
														// es als Ticks angeben muss, 1 Sekunde = 20
														// Ticks)
						player.sendMessage("§aDu hast den Spieler erfolgreich verbrannt");
					} else {
						player.sendMessage("§cBitte verwende eine Zahl, die höher als 0 ist!");
					}
				} else {
					player.sendMessage("§cBitte verwende /fire <Spieler> <Zeit>");
				}
			} else {
				player.sendMessage("§cDieser Spieler ist nicht online!");
			}
		} else {
			player.sendMessage("§cBitte verwende /fire <Spieler> <Zeit>");
		}
		return false;
	}

	public static boolean istInteger(String string) {
		try {
			Integer.parseInt(string);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}