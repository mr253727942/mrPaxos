/**
 * 
 */
package nmid.mrPaxos.message;

import java.io.Serializable;

/**
 * @author MaRong
 * @date 2016年3月31日 下午3:34:17
 * @description 
 */
public class ReplyMsg extends BaseMsg {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;


    public ReplyMsg() {
	super();
	setMsgType(MsgType.REPLY);
	
    }
    public void setBody(ReplyBody body) {
        this.body = body;
    }
    private ReplyBody body;
    
    
    public  ReplyBody getBody() {
	return body;
    }
}


