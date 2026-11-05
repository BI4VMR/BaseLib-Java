#!/bin/bash

# 模块配置文件
CONFIG_LIBS="misc/script/config_libs"


# 判断是否在工程根目录
if [[ ! -f "settings.gradle.kts" ]]; then
    echo "请在工程根目录下运行此脚本！"
    exit 1
fi


# 清空组件在本地的缓存
echo -e "----- 开始【清除本地Gradle缓存】... -----\n"

find ~/.gradle/caches -iname "*net.bi4vmr.tool.java*" -print0 | xargs -t -r -0 -n 1 rm -rf

./gradlew clean --no-daemon

echo -e "\n----- 【清除本地Gradle缓存】完成。 -----\n"


echo -e "\n----- 开始【发布Lib组件】... -----\n"

while IFS=$'\n' read -r module || [[ -n "$module" ]]; do
    # 跳过空行和注释行
    [[ -z "$module" || "$module" =~ ^# ]] && continue

    echo -e "\n----- 开始编译模块：[$module] ... -----\n"

    # Gradle命令默认会读取标准输入，和循环语句的 `read` 命令冲突导致逻辑错误，此处需要重定向标准输入。
    ./gradlew "$module:publish" --no-daemon < /dev/null

    if [[ $? -ne 0 ]]; then
        echo "模块 [$module] 发布失败，编译终止！"
        exit 1
    fi

    echo -e "\n----- 编译模块：[$module] 完成。 -----\n"
done < "$CONFIG_LIBS"

echo -e "\n----- 【发布Lib组件】完成。 -----"
