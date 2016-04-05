/**
 * 
 */
package nmid.mrPaxos.message;

import java.io.Serializable;

/**
 * @author MaRong
 * @date 2016年3月31日 下午3:31:04
 * @description 
 */
public class AskMsg extends BaseMsg{
    public AskMsg() {
	super();
	setMsgType(MsgType.ASK);
    }
    
    private AskParams params;

    public AskParams getParams() {
        return params;
    }

    public void setParams(AskParams params) {
        this.params = params;
    }
    
    
}

class AskParams implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String auth;
    public String getAuth() {
        return auth;
    }
    public void setAuth(String auth) {
        this.auth = auth;
    }
    
    
    
}
