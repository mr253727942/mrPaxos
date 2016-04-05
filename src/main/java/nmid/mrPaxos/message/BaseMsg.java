/**
 * 
 */
package nmid.mrPaxos.message;

import java.io.Serializable;

/**
 * @author MaRong
 * @date 2016年3月31日 下午2:42:34
 * @description 
 */
public abstract class BaseMsg implements Serializable{

    
    private static final long serialVersionUID = 1L;
    
    private MsgType msgType;
    
    private String clientId;

    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    
}
