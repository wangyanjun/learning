# Installation
1. Install
```bash
sudo apt-get install nginx
```

2. Start
```bash
sudo nginx -s reload
```

3. Stop
```bash
sudo nginx -t
```

4. Default config
```
server {
	listen 80 default_server;
	listen [::]:80 default_server;
        location / {
		proxy_pass http://localhost:5000;
		proxy_http_version 1.1;
		proxy_set_header Upgrade $http_upgrade;
		proxy_set_header Connection keep-alive;
		proxy_set_header Host $Host;
		proxy_cache_bypass $http_upgrade;
	}
}
```