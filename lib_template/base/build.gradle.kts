plugins {
    id("java")
}

tasks.withType<Test> {
    // 连接Gradle测试任务与JUnit工具
    useJUnitPlatform()
}

dependencies {
    testImplementation(libJava.junit5.jupiter)
}
