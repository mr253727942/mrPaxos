/**
 * 
 */
package nmid.mrPaxos.rmi;

import java.util.concurrent.TimeUnit;

import nmid.mrPaxos.message.AskMsg;
import nmid.mrPaxos.message.PingMsg;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;


/**
 * @author MaRong
 * @date 2016年4月2日 下午4:49:47
 * @description 
 */
public class NettyServerBootStrap {
    private int port;
    private SocketChannel socketChannel;
    
    public NettyServerBootStrap(int port) throws InterruptedException {
	this.port = port;
	bind();
    }
    
    private void bind() throws InterruptedException {
	EventLoopGroup boss = new NioEventLoopGroup();
	EventLoopGroup worker = new NioEventLoopGroup();
	
	ServerBootstrap bootstrap = new ServerBootstrap();
	bootstrap.group(boss,worker);
	bootstrap.channel(NioServerSocketChannel.class);
	bootstrap.option(ChannelOption.SO_BACKLOG, 128);
	bootstrap.option(ChannelOption.TCP_NODELAY, true);
	bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
	bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
	bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline p = socketChannel.pipeline();
                p.addLast(new ObjectEncoder());
                p.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                p.addLast(new NettyServerHandler());
            }
        });
        ChannelFuture f= bootstrap.bind(port).sync();
        if(f.isSuccess()){
            System.out.println("server start---------------");
        }
    }
    
    
    public static void main(String[] args) throws InterruptedException {
	NettyServerBootStrap bootstrap = new NettyServerBootStrap(9999);
	/*while(true) {
	    SocketChannel channel = (SocketChannel)NettyChannelMap.get("001");
	    if(channel != null) {
		AskMsg  askMsg = new AskMsg();
		
		channel.writeAndFlush(askMsg);
	    }
	    TimeUnit.SECONDS.sleep(5);
	    
	}*/
    }
    
}
