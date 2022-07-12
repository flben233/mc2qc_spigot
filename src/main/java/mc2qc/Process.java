package mc2qc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static org.bukkit.Bukkit.getServer;

class Process extends Thread implements Listener {
    File ip = new File("." + File.separator + "plugins" + File.separator + "mc2qc" + File.separator + "ip");
    File token = new File("." + File.separator + "plugins" + File.separator + "mc2qc" + File.separator + "token");
    DataOutputStream dos;
    mc2qc plugin;

    public Process(mc2qc plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        try {
            getServer().getPluginManager().registerEvents(this, plugin);
            Socket socket = new Socket(readFile(ip), 25555);
            this.dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(new Scanner(token).next().trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) throws Exception {
        String str = "[" + event.getPlayer().getName() + "] " + event.getMessage().trim();
        dos.writeUTF(str);
        plugin.getLogger().info(str);
    }

    public static String readFile(File file) {
        StringBuilder str = new StringBuilder();
        try {
            FileReader fr = new FileReader(file);
            int i;
            while ((i = fr.read()) != -1) {
                str.append((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }
}
