logback是一套日志框架，由log4j的优化版，由同一个作者开发，在速度和性能上都超过其他日志框架，再结合slf4j，已成为当前最流行的日志框架。

根节点configuration，有以下属性
a. scan,当此属性设置为true时，配置文件如果发生改变，将会被重新加载，默认值为true。
b. scanPeriod,设置监测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒。当scan为true时，此属性生效。默认的时间间隔为1分钟。
c. debug,当此属性设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。默认值为false。

property可以用来设置变量，可以通过${name}来访问，有以下的属性

a. name，用于${name}访问的key

b. value，用于${name}访问的value

c. file ，用于指定配置文件的路径，他的作用在于，如果你有多个配置信息的话，可以直接写在配置文件中，然后通过file引入