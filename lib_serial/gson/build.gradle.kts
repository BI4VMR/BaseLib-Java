val depInTOML: MinimalExternalModuleDependency = privateLibJava.serial.gson.get()
val mvnGroupID: String = requireNotNull(depInTOML.group)
val mvnArtifactID: String = depInTOML.name
val mvnVersion: String = requireNotNull(depInTOML.version)


plugins {
    id("java-library")
    id("maven-publish")
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks.withType<Javadoc> {
    // 指定JavaDoc编码，解决系统编码与文件不一致导致错误。
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    // 连接Gradle测试任务与JUnit工具
    useJUnitPlatform()
}

dependencies {
    implementation(privateLibJava.common.base)

    implementation(libJava.gson)

    // JUnit5 BOM版本配置文件
    testImplementation(platform(libJava.junit5.bom))
    // JUnit5 平台启动器
    testImplementation(libJava.junit5.launcher)
    // Jupiter（JUnit5引擎的实现）
    testImplementation(libJava.junit5.jupiter)
}

publishing {
    repositories {
        // 私有仓库
        maven {
            name = "private"
            isAllowInsecureProtocol = true
            setUrl("http://172.16.5.1:8081/repository/maven-private/")
            credentials {
                username = "uploader"
                password = "uploader"
            }
        }
    }

    publications {
        // 创建名为"maven"的发布配置
        create<MavenPublication>("maven") {
            // 产物的基本信息
            groupId = mvnGroupID
            artifactId = mvnArtifactID
            version = mvnVersion

            // 发布程序包
            from(components.getByName("java"))

            // POM信息
            pom {
                // 打包格式
                packaging = "jar"

                name.set(mvnArtifactID)
                url.set("https://github.com/BI4VMR/BaseLib-Java")
                developers {
                    developer {
                        name.set("BI4VMR")
                        email.set("bi4vmr@outlook.com")
                    }
                }
            }
        }
    }
}

/**
 * 配置发布任务之间的依赖关系。
 *
 * 当前模块依赖某个模块，因此发布当前模块时，必须先发布被依赖的模块。
 *
 * "dependsOn()"方法指定了执行该任务时需要同时执行指定任务；"mustRunAfter()"方法则指定了该任务需要在指定任务完成后开始执行。
 */
tasks.named("publish") {
    dependsOn(":lib_common:base:publish")
    mustRunAfter(":lib_common:base:publish")
}

tasks.named("publishToMavenLocal") {
    dependsOn(":lib_common:base:publishToMavenLocal")
    mustRunAfter(":lib_common:base:publishToMavenLocal")
}
