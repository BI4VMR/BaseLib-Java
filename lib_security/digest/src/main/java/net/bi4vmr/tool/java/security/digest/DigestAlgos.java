package net.bi4vmr.tool.java.security.digest;

/**
 * 受支持的算法枚举。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public enum DigestAlgos {

    /**
     * JDK内置算法：MD5
     */
    MD5("MD5"),

    /**
     * JDK内置算法：SHA-1
     */
    SHA_1("SHA"),

    /**
     * JDK内置算法：SHA-256
     */
    SHA_256("SHA-256");

    private final String standardName;

    DigestAlgos(String standardName) {
        this.standardName = standardName;
    }

    /**
     * 获取该算法在JDK中的名称。
     *
     * @return 算法名称。
     */
    public String getStandardName() {
        return standardName;
    }
}
