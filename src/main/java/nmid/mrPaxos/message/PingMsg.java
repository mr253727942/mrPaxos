/**
 * 
 */
package nmid.mrPaxos.message;

/**
 * @author MaRong
 * @date 2016年3月31日 下午3:27:12
 * @description 
 */
public class PingMsg extends BaseMsg{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public PingMsg() {
	super();
	setMsgType(MsgType.PING);
    }
}
