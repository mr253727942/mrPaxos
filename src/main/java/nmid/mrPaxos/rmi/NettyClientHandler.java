/**
 * 
 */
package nmid.mrPaxos.rmi;

import nmid.mrPaxos.message.BaseMsg;
import nmid.mrPaxos.message.MsgType;
import nmid.mrPaxos.message.PingMsg;
import nmid.mrPaxos.message.ReplyClientBody;
import nmid.mrPaxos.message.ReplyMsg;
import nmid.mrPaxos.message.ReplyServerBody;
import nmid.mrPaxos.model.ClientConstants;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

/**
 * @author MaRong
 * @date 2016年4月2日 下午5:35:29
 * @description 
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<BaseMsg>{

    
    
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
	    throws Exception {
	if(evt instanceof IdleStateEvent) {
	    IdleStateEvent e = (IdleStateEvent)evt;
	    switch(e.state()) {
	    case WRITER_IDLE:{
		PingMsg pingMsg = new PingMsg();
		pingMsg.setClientId(ClientConstants.getClientId());
		ctx.writeAndFlush(pingMsg);
		System.out.println("send ping to server");
		break;
	    } 
	    default :
		break;
	    }
	}
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, BaseMsg baseMsg) 
	    throws Exception {
	MsgType type = baseMsg.getMsgType();
	switch(type) {
	case PING:{
	    System.out.println("recive server ping");
	}break;
	
	case ASK:{
	    ReplyClientBody replyClientBody = new ReplyClientBody(" 我是客户端信息");
	    ReplyMsg replyMsg = new ReplyMsg();
	    replyMsg.setBody(replyClientBody);
	    ctx.writeAndFlush(replyMsg);
	    
	}break;
	case REPLY:{
	    ReplyMsg replyMsg = (ReplyMsg) baseMsg;
	    ReplyServerBody replyServerBody = (ReplyServerBody) replyMsg.getBody();
	    System.out.println("获得服务端的消息 " + replyServerBody.getServerInfo());
	    
	}
	default:break;
	}
	ReferenceCountUtil.release(type);
    }
    
}
