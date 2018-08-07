# Errors 

## 1. Unable to lock the administration directory (/var/lib/dpkg/) is another process using it?

现象：尝试使用 apt-get 相关命令时出现如下错误

~~~bash
E: Could not get lock /var/lib/dpkg/lock - open (11 Resource temporarily unavailable)
E: Unable to lock the administration directory (/var/lib/dpkg/) is another process using it?  
~~~

原因：

锁定文件出现异常，删除锁定文件即可

解决方法：

~~~bash
sudo rm /var/lib/apt/lists/lock
sudo rm /var/cache/apt/archives/lock
sudo rm /var/lib/dpkg/lock
~~~