package cn.vove7.smartkey.key

import cn.vove7.smartkey.annotation.Config
import cn.vove7.smartkey.key.IKey.Companion.getSettingsImpl
import cn.vove7.smartkey.tool.Vog
import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation


inline fun <reified T> noCacheKey(
        defaultValue: T,
        encrypt: Boolean = false,
        key: String? = null): NoCacheKey<T> {
    return NoCacheKey(defaultValue, T::class.java, encrypt, key)
}

/**
 * # NoCacheKey
 * 无缓存
 * @author Vove
 * 2019/6/19
 */
class NoCacheKey<T>(
        private val defaultValue: T,

        /**
         * 指定泛型class
         */
        private val cls: Class<*>,

        /**
         * 加密存储数据
         */
        private val isEncrypt: Boolean = false,
        /**
         * 自定义key
         */
        private val key: String? = null
) : IKey() {

    operator fun getValue(thisRef: Any?, p: KProperty<*>): T {
        initConfigName(thisRef)
        val k = key ?: p.name
        val value = getSettingsImpl(configName).get(k, defaultValue, cls, isEncrypt)
        return value ?: defaultValue
    }


    operator fun setValue(thisRef: Any?, property: KProperty<*>, t: T) {
        initConfigName(thisRef)
        val k = key ?: property.name
        Vog.d("设置值：$k = $t")
        getSettingsImpl(configName).set(k, t, isEncrypt)
    }

}