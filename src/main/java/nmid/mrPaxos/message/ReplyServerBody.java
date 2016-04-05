/**
 * 
 */
package nmid.mrPaxos.message;

/**
 * @author MaRong
 * @date 2016年4月2日 下午4:33:53
 * @description 
 */
public class ReplyServerBody extends ReplyBody{

    private static final long serialVersionUID = 1L;
    private String serverInfo;
    public ReplyServerBody(String serverInfo) {
	this.serverInfo = serverInfo;
    }
    
    public String getServerInfo() {
	return serverInfo;
    }

    public void setServerInfo(String serverInfo) {
        this.serverInfo = serverInfo;
    }
    
    
}
