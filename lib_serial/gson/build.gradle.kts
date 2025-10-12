val depInTOML: MinimalExternalModuleDependency = privateLibJava.serial.gson.get()
val mvnGroupID: String = requireNotNull(depInTOML.group)
val mvnArtifactID: String = depInTOML.name
val mvnVersion: String = requireNotNull(depInTOML.version)


plugins {
    id(libJava.plugins.java.library.get().pluginId)
    id(privateLibJava.plugins.repo.private.get().pluginId)
    id(privateLibJava.plugins.repo.public.get().pluginId)
    id(privateLibJava.plugins.publish.private.get().pluginId)
}

tasks.withType<Test> {
    // 连接Gradle测试任务与JUnit工具
    useJUnitPlatform()
}

dependencies {
    api(privateLibJava.common.base)

    api(libJava.gson)

    // JUnit5 BOM版本配置文件
    testImplementation(platform(libJava.junit5.bom))
    // JUnit5 平台启动器
    testImplementation(libJava.junit5.launcher)
    // Jupiter（JUnit5引擎的实现）
    testImplementation(libJava.junit5.jupiter)
}

privatePublishConfig {
    groupID = mvnGroupID
    artifactID = mvnArtifactID
    version = mvnVersion
}
