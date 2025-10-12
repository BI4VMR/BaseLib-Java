// Gradle插件声明
plugins {
    alias(privateLibJava.plugins.repo.private).apply(false)
    alias(privateLibJava.plugins.repo.public).apply(false)
    alias(privateLibJava.plugins.publish.private).apply(false)
}
