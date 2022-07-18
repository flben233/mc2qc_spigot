# mc2qc
一个bukkit插件，可以将Minecraft服务器的消息转发至指定的QQ群，需要配合mirai端插件使用

## 内容列表

- [使用说明](#使用说明)
- [插件下载](#插件下载)
- [相关仓库](#相关仓库)
- [如何贡献](#如何贡献)
- [使用许可](#使用许可)

## 使用说明
注意，本插件只能运行于1.13及以上版本的服务端

本插件属于socket客户端，因此请先启动Mirai再启动本插件
首次启动时会在以下路径创建配置文件 ip token ，你需要分别填写Mirai机器人所在的服务器的IP（若在同一主机请填写localhost）和口令（口令可以随意填写，但是需要与mirai端mc2qc口令即token文件中填写的内容一致），配置文件填写时不要换行
```
(服务端主目录)/plugin/mc2qc/
```
填写完成以后请重启服务端，如有Plugin Manager插件也可以使用以下指令重载本插件
```
pm reload mc2qc
```

## 插件下载

[服务端插件](https://github.com/flben233/mc2qc_spigot/releases)

[mirai端插件](https://github.com/flben233/mc2qc/releases)

## 相关仓库

- [mc2qc](https://github.com/flben233/mc2qc) — 本插件对应的Mirai端。
- [qc2mc](https://github.com/flben233/qc2mc) — 你可以配合这个Mirai插件实现QQ群消息推送至Minecraft服务器。

## 维护者

[@flben233](https://github.com/flben233)。

## 如何贡献

非常欢迎你的加入！[提一个 Issue](https://github.com/flben233/mc2qc_spigot/issues/new) 或者提交一个 Pull Request。

## 使用许可

[MIT](LICENSE) © flben233
