package cn.vove7.smartkey.settings

import cn.vove7.smartkey.tool.Vog
import com.russhwolf.settings.Settings
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*

/**
 * # PropertiesSettings
 * 基于[Properties]持久化
 * @author Vove
 * 2019/6/19
 */
class PropertiesSettings(private val configName: String) : Settings {


    companion object {
        //存储目录
        var baseDir: String? = null
    }

    private val prefix get() = if (baseDir != null) "$baseDir/properties" else "properties"
    private val configFile
        get() = File(prefix + "/$configName.properties")

    private val properties: Properties by lazy {
        Properties().apply {
            File(prefix).also {
                if (!it.exists()) it.mkdir()
            }
            configFile.let {
                if (!it.exists()) {
                    it.createNewFile()
                }
                load(FileInputStream(it))

                Vog.d("配置路径：${it.absolutePath}")
            }
        }
    }

    override fun clear() {
        properties.clear()
        properties.store(FileOutputStream(configFile), null)
    }

    override fun remove(key: String) {
        properties.remove(key)
        properties.store(FileOutputStream(configFile), null)
    }

    override fun hasKey(key: String): Boolean = properties.containsKey(key)


    override fun putInt(key: String, value: Int) {
        properties[key] = value.toString()
        properties.store(FileOutputStream(configFile), null)
    }

    override fun getInt(key: String, defaultValue: Int): Int = properties.getProperty(key)?.toIntOrNull()
        ?: defaultValue

    override fun putLong(key: String, value: Long) {
        properties[key] = value.toString()
        properties.store(FileOutputStream(configFile), null)
    }

    override fun getLong(key: String, defaultValue: Long): Long = properties.getProperty(key)?.toLongOrNull()
        ?: defaultValue


    override fun putString(key: String, value: String) {
        properties[key] = value
        properties.store(FileOutputStream(configFile), null)
    }

    override fun getString(key: String, defaultValue: String): String = properties.getProperty(key)
        ?: defaultValue

    override fun putFloat(key: String, value: Float) {
        properties[key] = value.toString()
        properties.store(FileOutputStream(configFile), null)
    }

    override fun getFloat(key: String, defaultValue: Float): Float = properties.getProperty(key)?.toFloatOrNull()
        ?: defaultValue

    override fun putDouble(key: String, value: Double) {
        properties[key] = value.toString()
        properties.store(FileOutputStream(configFile), null)
    }

    override fun getDouble(key: String, defaultValue: Double): Double = properties.getProperty(key)?.toDoubleOrNull()
        ?: defaultValue

    override fun putBoolean(key: String, value: Boolean) {
        properties[key] = value.toString()
        properties.store(FileOutputStream(configFile), null)
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean = properties.getProperty(key)?.toBoolean()
        ?: defaultValue
}
