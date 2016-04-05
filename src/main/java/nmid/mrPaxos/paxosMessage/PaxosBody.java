/**
 * 
 */
package nmid.mrPaxos.paxosMessage;

import nmid.mrPaxos.message.ReplyBody;

/**
 * @author MaRong
 * @date 2016年4月4日 下午2:38:07
 * @description 
 */
public class PaxosBody extends ReplyBody{

    
    private static final long serialVersionUID = 1L;
    
    private  Integer proposalId;
    
    private  String value;

    private  Long epoch;
    
    public Long getEpoch() {
        return epoch;
    }

    public void setEpoch(Long epoch) {
        this.epoch = epoch;
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
    
}
