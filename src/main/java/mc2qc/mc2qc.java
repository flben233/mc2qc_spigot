package mc2qc;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.*;


public class mc2qc extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        try{
            File ip = new File("." + File.separator + "plugins" + File.separator + "mc2qc" + File.separator + "ip");
            File token = new File("." + File.separator + "plugins" + File.separator + "mc2qc" + File.separator + "token");
            File path = new File("." + File.separator + "plugins" + File.separator + "mc2qc");
            if(!ip.exists() || !token.exists()){
                path.mkdirs();
                ip.createNewFile();
                token.createNewFile();
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

