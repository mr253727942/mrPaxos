/**
 * 
 */
package nmid.mrPaxos.paxosMessage;

import nmid.mrPaxos.message.ReplyBody;

/**
 * @author MaRong
 * @date 2016年4月4日 下午8:42:10
 * @description 
 */
public class PaxosReplyBody extends ReplyBody{

    private static final long serialVersionUID = 1L;
    private boolean flag;
    private Integer proposalId;
    private String value;
    private Long epoch;
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public Integer getProposalId() {
        return proposalId;
    }
    public void setProposalId(Integer proposalId) {
        this.proposalId = proposalId;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public Long getEpoch() {
        return epoch;
    }
    public void setEpoch(Long epoch) {
        this.epoch = epoch;
    }
    
    
    
}
