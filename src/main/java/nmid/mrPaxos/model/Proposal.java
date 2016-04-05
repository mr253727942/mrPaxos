/**
 * 
 */
package nmid.mrPaxos.model;

import java.io.Serializable;

/**
 * @author MaRong
 * @date 2016年4月3日 下午5:38:45
 * @description 
 */
public class Proposal implements Serializable{
   
    private static final long serialVersionUID = 1L;
    private Integer proposalId;
    private String value;
    private Long epoch ; 
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
