package net.bi4vmr.gradle.plugin

/**
 * 私有Maven发布插件配置项。
 *
 * @author BI4VMR@outlook.com
 * @since 1.0.0
 */
open class PrivatePublishConfig {

    companion object {
        const val NAME = "privatePublishConfig"
    }

    var groupID: String? = null
    var artifactID: String? = null
    var version: String? = null
}
