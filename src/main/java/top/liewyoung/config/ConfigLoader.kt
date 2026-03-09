package top.liewyoung.config

import java.io.FileInputStream
import java.io.IOException
import java.util.Properties

const val configPath = "src/main/Config/config.prop"

// 缓存已加载的配置，避免每次调用都重新读取磁盘；by lazy 保证线程安全且只初始化一次
private val cachedProperties: Properties? by lazy {
    try {
        Properties().also { prop ->
            prop.load(FileInputStream(configPath))
        }
    } catch (e: IOException) {
        println(e.message)
        null
    }
}

/**
 * 获得价值
 * @param [key] 说明
 * @return [String]
 */
fun getValue(key: String): String {
    return cachedProperties?.getProperty(key) ?: ""
}