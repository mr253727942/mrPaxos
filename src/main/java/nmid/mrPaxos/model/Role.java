/**
 * 
 */
package nmid.mrPaxos.model;

/**
 * @author MaRong
 * @date 2016年3月31日 下午2:10:54
 * @description
 */
public enum Role {
    LEADER("leader"), ACCEPTOR("acceptor"), PROPOSER("proposer");
    
    private String value;
    private String IP;

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    Role(String value){
	this.value = value;
    }

   

}
