package mc2qc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.net.Socket;

import static org.bukkit.Bukkit.getServer;

public class mc2qc extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        try{
            File ip = new File("." + File.separator + "plugins" + File.separator + "mc2qc" + File.separator + "ip");
            File path = new File("." + File.separator + "plugins" + File.separator + "mc2qc");
            if(!ip.exists()){
                path.mkdirs();
                ip.createNewFile();
                getLogger().info("************************************************");
                getLogger().info("* 请在 (spigot主目录)/plugin/mc2qc/ip 文件中填写   *");
                getLogger().info("* 机器人的IP地址，然后使用pm插件重载本插件，或重启服务端 *");
                getLogger().info("*************************************************");
            }
            else{
                new Process(this).start();
                getLogger().info("mc2qc插件启动完成");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

class Process extends Thread implements Listener{
    File ip = new File("." + File.separator + "plugins" + File.separator + "mc2qc" + File.separator + "ip");
    DataOutputStream dos;
    public mc2qc plugin;
    public Process(mc2qc plugin){
        this.plugin = plugin;
    }
    @Override
    public void run() {
        try {
            getServer().getPluginManager().registerEvents(this,plugin);
            Socket socket = new Socket(readFile(ip), 25555);
            this.dos = new DataOutputStream(socket.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) throws Exception{
        String str = "[" + event.getPlayer().getName() + "] " + event.getMessage().trim();
        dos.writeUTF(str);
        plugin.getLogger().info(str);
    }

    public static String readFile(File file){
        StringBuilder str = new StringBuilder();
        try {
            FileReader fr = new FileReader(file);
            int i ;
            while((i = fr.read()) != -1){
                str.append((char) i);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return str.toString();
    }
}