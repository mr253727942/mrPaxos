/**
 * 
 */
package nmid.mrPaxos.model;

/**
 * @author MaRong
 * @date 2016年3月31日 下午3:24:38
 * @description 
 */
public class ClientConstants {
    private static String clientId;

    public static String getClientId() {
        return clientId;
    }

    public static void setClientId(String clientId) {
        ClientConstants.clientId = clientId;
    }
    
    
}
