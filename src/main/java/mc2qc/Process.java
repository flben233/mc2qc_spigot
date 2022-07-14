package mc2qc;

import mc2qc.utils.Md5Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static mc2qc.utils.FileUtil.readFile;
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
            String tokenMd5 = Md5Util.toMd5String(new Scanner(token).next().trim());
            if(tokenMd5 != null) {
                Socket socket = new Socket(readFile(ip), 25555);
                this.dos = new DataOutputStream(socket.getOutputStream());
                dos.writeUTF(tokenMd5);
            }else {
                plugin.getLogger().info("【mc2qc】请输入连接密码");
            }
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


}
