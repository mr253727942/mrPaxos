/**
 * 
 */
package nmid.mrPaxos.rmi;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MaRong
 * @date 2016年3月31日 下午3:41:13
 * @description 
 */
public class NettyChannelMap {
    private static Map<String,SocketChannel> map = new ConcurrentHashMap<String,SocketChannel>();
    public static void add(String clientId,SocketChannel socketChannel) {
	map.put(clientId,socketChannel);

    }
    public static boolean contain(String id) {
	return map.containsKey(id);
    }
    public static Channel get(String clientId) {
	return map.get(clientId);
    }
    public static void remove(SocketChannel socketChannel){
	for (Map.Entry entry:map.entrySet()){
            if (entry.getValue()==socketChannel){
                map.remove(entry.getKey());
            }
        }
    }
    
}
