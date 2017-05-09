# `长卫医疗`

> 长卫医疗服务端

## 开始

```


```


## 数据库初始化：
```
mongo 112.74.166.172:27017
use admin
db.auth()

use cw
db.createUser({ user: 'user', pwd: 'KR!mongodb', roles: [ {role:'readWrite', db:'cw'} ]})
```