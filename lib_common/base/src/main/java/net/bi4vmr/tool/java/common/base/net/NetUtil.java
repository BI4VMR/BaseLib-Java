package net.bi4vmr.tool.java.common.base.net;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 网络相关工具。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class NetUtil {

    /**
     * IPv4 地址：所有接口。
     */
    public static final String IP_ANY = "0.0.0.0";

    /**
     * IPv4 地址：环回接口。
     */
    public static final String IP_LOOPBACK = "127.0.0.1";

    /**
     * IPv6 地址：所有接口。
     */
    public static final String IPV6_ANY = "::";

    /**
     * IPv6 地址：环回接口。
     */
    public static final String IPV6_LOOPBACK = "::1";


    /**
     * 主机可达性侦测 (TCP) 。
     *
     * @param host    目标 IP 地址或域名。
     * @param port    目标端口。
     * @param timeout 超时时间。
     * @return `true` 表示目标端口可达； `false` 表示目标端口不可达。
     */
    public static boolean portScanByTCP(String host, int port, int timeout) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), timeout);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 本地端口是否可绑定。
     *
     * @param domainOrAddress 目标 IP 地址或域名。
     * @param port            目标端口。
     * @return `true` 表示端口可用； `false` 表示端口不可用。
     */
    public static boolean isPortAvailable(String domainOrAddress, int port) {
        try (ServerSocket socket = new ServerSocket()) {
            socket.setReuseAddress(true);
            socket.bind(new InetSocketAddress(domainOrAddress, port));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 本地端口是否不可绑定。
     *
     * @param domainOrAddress 目标 IP 地址或域名。
     * @param port            目标端口。
     * @return `true` 表示端口不可用； `false` 表示端口可用。
     */
    public static boolean isPortUnavailable(String domainOrAddress, int port) {
        return !isPortAvailable(domainOrAddress, port);
    }
}
