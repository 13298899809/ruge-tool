kafka的配置分为 broker、producter、consumer三个不同的配置

~每一个broker在集群中的唯一标示，要求是正数。在改变IP地址，不改变broker.id的话不会影响consumers
broker.id =1