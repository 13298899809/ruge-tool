# ftp环境搭建

## ftp安装

首先检查一下你的远程服务器是否已经安装了FTP服务


```
#rpm -qa | grep vsftpd
```

如果啥都没显示，恭喜你，没有安装ftp服务，你可以安心安装。

运行下面的命令就可以完成vsftpd的安装

```
[root@ruge ~]# yum -y install vsftpd
Loaded plugins: fastestmirror
Determining fastest mirrors
base                                                                                                                                                                                         | 3.6 kB  00:00:00     
epel                                                                                                                                                                                         | 4.7 kB  00:00:00     
extras                                                                                                                                                                                       | 2.9 kB  00:00:00     
mysql-connectors-community                                                                                                                                                                   | 2.5 kB  00:00:00     
mysql-tools-community                                                                                                                                                                        | 2.5 kB  00:00:00     
mysql56-community                                                                                                                                                                            | 2.5 kB  00:00:00     
updates                                                                                                                                                                                      | 2.9 kB  00:00:00     
(1/8): epel/x86_64/group_gz                                                                                                                                                                  |  95 kB  00:00:00     
(2/8): epel/x86_64/updateinfo                                                                                                                                                                | 1.0 MB  00:00:00     
(3/8): epel/x86_64/primary_db                                                                                                                                                                | 6.9 MB  00:00:00     
(4/8): extras/7/x86_64/primary_db                                                                                                                                                            | 206 kB  00:00:00     
(5/8): updates/7/x86_64/primary_db                                                                                                                                                           | 4.5 MB  00:00:00     
(6/8): mysql-connectors-community/x86_64/primary_db                                                                                                                                          |  62 kB  00:00:00     
(7/8): mysql-tools-community/x86_64/primary_db                                                                                                                                               |  76 kB  00:00:00     
(8/8): mysql56-community/x86_64/primary_db                                                                                                                                                   | 265 kB  00:00:00     
Resolving Dependencies
--> Running transaction check
---> Package vsftpd.x86_64 0:3.0.2-27.el7 will be installed
--> Finished Dependency Resolution

Dependencies Resolved

====================================================================================================================================================================================================================
 Package                                           Arch                                              Version                                                  Repository                                       Size
====================================================================================================================================================================================================================
Installing:
 vsftpd                                            x86_64                                            3.0.2-27.el7                                             base                                            172 k

Transaction Summary
====================================================================================================================================================================================================================
Install  1 Package

Total download size: 172 k
Installed size: 353 k
Downloading packages:
vsftpd-3.0.2-27.el7.x86_64.rpm                                                                                                                                                               | 172 kB  00:00:00     
Running transaction check
Running transaction test
Transaction test succeeded
Running transaction
  Installing : vsftpd-3.0.2-27.el7.x86_64                                                                                                                                                                       1/1 
  Verifying  : vsftpd-3.0.2-27.el7.x86_64                                                                                                                                                                       1/1 

Installed:
  vsftpd.x86_64 0:3.0.2-27.el7                                                                                                                                                                                      

Complete!


```


### vsftpd服务程序常用的参数以及作用如下

```
参数                                作用
listen=[YES|NO]              是否以独立运行的方式监听服务
listen_address=IP地址        设置要监听的IP地址
listen_port=21               设置FTP服务的监听端口
download_enable＝[YES|NO]    是否允许下载文件
userlist_enable=[YES|NO]     设置用户列表为“允许”还是“禁止”操作
userlist_deny=[YES|NO]      设置用户列表为“允许”还是“禁止”操作
max_clients=0                最大客户端连接数，0为不限制
max_per_ip=0                 同一IP地址的最大连接数，0为不限制
anonymous_enable=[YES|NO]    是否允许匿名用户访问
anon_upload_enable=[YES|NO]  是否允许匿名用户上传文件
anon_umask=022               匿名用户上传文件的umask值
anon_root=/var/ftp           匿名用户的FTP根目录
anon_mkdir_write_enable=[YES|NO]    是否允许匿名用户创建目录
anon_other_write_enable=[YES|NO]    是否开放匿名用户的其他写入权限（包括重命名、删除等操作权限）
anon_max_rate=0                     匿名用户的最大传输速率（字节/秒），0为不限制
local_enable=[YES|NO]               是否允许本地用户登录FTP
local_umask=022                     本地用户上传文件的umask值
local_root=/var/ftp                 本地用户的FTP根目录
chroot_local_user=[YES|NO]          是否将用户权限禁锢在FTP目录，以确保安全
local_max_rate=0                    本地用户最大传输速率（字节/秒），0为不限制
```

## 启动

```
systemctl start vsftpd
```


### 直接向vsftpd服务程序的主配置文件（/etc/vsftpd/vsftpd.conf）中添加下面的配置参数，原本的参数都删了。


```
vim /etc/vsftpd/vsftpd.conf


anonymous_enable=YES
anon_umask=022
anon_upload_enable=YES
anon_mkdir_write_enable=YES
anon_other_write_enable=YES
local_enable=YES
write_enable=YES
local_umask=022
dirmessage_enable=YES
xferlog_enable=YES
connect_from_port_20=YES
xferlog_std_format=YES
listen=NO
listen_ipv6=YES
pam_service_name=vsftpd
userlist_enable=YES
tcp_wrappers=YES


```

然后重启FTP服务。

```
systemctl restart vsftpd
```



