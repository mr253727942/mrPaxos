/**
 * 
 */
package nmid.mrPaxos.rmi;

import nmid.mrPaxos.message.AskMsg;
import nmid.mrPaxos.message.BaseMsg;
import nmid.mrPaxos.message.PingMsg;
import nmid.mrPaxos.message.ReplyBody;
import nmid.mrPaxos.message.ReplyClientBody;
import nmid.mrPaxos.message.ReplyMsg;
import nmid.mrPaxos.message.ReplyServerBody;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;

/**
 * @author MaRong
 * @date 2016年4月2日 下午3:58:05
 * @description
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<BaseMsg> {

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
	// TODO Auto-generated method stub
	NettyChannelMap.remove((SocketChannel) ctx.channel());
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, BaseMsg baseMsg)
	    throws Exception {
	switch (baseMsg.getMsgType()) {
	case PING: {
	    PingMsg pingMsg = (PingMsg)baseMsg;
	    PingMsg replyPing = new PingMsg();
	    System.out.println(pingMsg.getClientId() +"sss");
	    String id = pingMsg.getClientId();
	    
	    if(!NettyChannelMap.contain(id)) {
		NettyChannelMap.add(id, (SocketChannel) ctx.channel());
	    }
	    NettyChannelMap.get(pingMsg.getClientId()).writeAndFlush(replyPing);
	}break;
	
	case ASK:{
	    AskMsg askMsg = (AskMsg)baseMsg;
	    String id = askMsg.getClientId();
	    
	    if(!NettyChannelMap.contain(id)) {
		NettyChannelMap.add(id, (SocketChannel) ctx.channel());
	    }
	    ReplyServerBody replyBody = new ReplyServerBody("server info get");
	    ReplyMsg replyMsg = new ReplyMsg();
	    replyMsg.setBody(replyBody);
	    NettyChannelMap.get(askMsg.getClientId()).writeAndFlush(replyMsg);
	    
	    
	}break;
	
	case REPLY:{
	    ReplyMsg replyMsg = (ReplyMsg)baseMsg;
	    ReplyClientBody clientBody = (ReplyClientBody)replyMsg.getBody();
	    System.out.println("recieve client msg "+clientBody.getClientInfo());
	}break;
	
	default:break;
	}
	ReferenceCountUtil.release(baseMsg);

    }

}
