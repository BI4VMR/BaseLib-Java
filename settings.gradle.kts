// 构建工具的依赖配置
pluginManagement {
    // 声明Gradle插件仓库
    repositories {
        // 添加本地私有仓库与代理镜像，无法直连时应当禁用该配置。
        val hostName: String = java.net.InetAddress.getLocalHost().hostName
        println("Current host name is [$hostName]")
        var isInPrivateLAN = false
        run {
            java.net.NetworkInterface.getNetworkInterfaces().toList().forEach {
                it.inetAddresses.toList().forEach { addr ->
                    if ((addr is java.net.Inet4Address) && (addr.hostAddress.startsWith("172.16."))) {
                        isInPrivateLAN = true
                        return@run
                    }
                }
            }
        }
        println("Current host in private LAN? [$isInPrivateLAN]")

        if (hostName.startsWith("BI4VMR") && isInPrivateLAN) {
            println("Current host is in private network, add LAN repositorys.")
            maven {
                isAllowInsecureProtocol = true
                setUrl("http://172.16.5.1:8081/repository/maven-mirror-tencent/")
            }
            maven {
                isAllowInsecureProtocol = true
                setUrl("http://172.16.5.1:8081/repository/maven-private/")
            }
        } else {
            if (java.net.InetAddress.getByName("192.168.128.1").isReachable(5)) {
                println("Current host is not in private network, add VPN repositorys.")
                maven {
                    isAllowInsecureProtocol = true
                    setUrl("http://192.168.128.1:8081/repository/maven-mirror-tencent/")
                }
                maven {
                    isAllowInsecureProtocol = true
                    setUrl("http://192.168.128.1:8081/repository/maven-private/")
                }
            } else {
                println("Current host is not in private network, add LOCAL repositorys.")
                mavenLocal()
            }
        }

        // 腾讯云仓库镜像：Maven中心仓库
        maven { setUrl("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/") }
        // 阿里云仓库镜像：Gradle社区插件
        maven { setUrl("https://maven.aliyun.com/repository/gradle-plugin/") }

        mavenCentral()
        gradlePluginPortal()
    }
}

// 所有模块的依赖配置
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    // 声明Maven组件仓库
    repositories {
        // 添加本地私有仓库与代理镜像，无法直连时应当禁用该配置。
        val hostName: String = java.net.InetAddress.getLocalHost().hostName
        var isInPrivateLAN = false
        run {
            java.net.NetworkInterface.getNetworkInterfaces().toList().forEach {
                it.inetAddresses.toList().forEach { addr ->
                    if ((addr is java.net.Inet4Address) && (addr.hostAddress.startsWith("172.16."))) {
                        isInPrivateLAN = true
                        return@run
                    }
                }
            }
        }

        if (hostName.startsWith("BI4VMR") && isInPrivateLAN) {
            maven {
                isAllowInsecureProtocol = true
                setUrl("http://172.16.5.1:8081/repository/maven-mirror-tencent/")
            }
            maven {
                isAllowInsecureProtocol = true
                setUrl("http://172.16.5.1:8081/repository/maven-private/")
            }
        } else {
            if (java.net.InetAddress.getByName("192.168.128.1").isReachable(5)) {
                maven {
                    isAllowInsecureProtocol = true
                    setUrl("http://192.168.128.1:8081/repository/maven-mirror-tencent/")
                }
                maven {
                    isAllowInsecureProtocol = true
                    setUrl("http://192.168.128.1:8081/repository/maven-private/")
                }
            } else {
                mavenLocal()
            }
        }

        // 腾讯云仓库镜像：Maven中心仓库
        maven { setUrl("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/") }

        mavenCentral()
    }

    // 版本管理配置
    versionCatalogs {
        // 声明命名空间"libs"
        create("libs") {
            // 导入依赖版本配置文件
            from(files("script/version/dependency.toml"))
        }

        // 基础组件库
        create("baselibs") {
            from(files("script/version/dependency_baselib.toml"))
        }
    }
}

/* ----- 工程结构声明 ----- */
// 主工程名称
rootProject.name = "BaseLib-Java"


// ----- 工具库 -----
// 公共组件
include(":lib_common:base")

// 输入输出
include(":lib_io:base")

// 数据存储
include(":lib_storage:file")

// 文本处理
include(":lib_text:base")
include(":lib_text:gson")

// 数学运算
include(":lib_math:base")

// 信息安全
include(":lib_security:digest")

// 模板代码
include(":lib_template:base")

// 依赖传递
include(":lib_all")
