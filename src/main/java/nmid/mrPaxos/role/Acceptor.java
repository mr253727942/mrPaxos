/**
 * 
 */
package nmid.mrPaxos.role;

import nmid.mrPaxos.paxosMessage.PaxosBody;
import nmid.mrPaxos.paxosMessage.PaxosReplyBody;

/**
 * @author MaRong
 * @date 2016年4月4日 下午2:36:36
 * @description 
 */
public interface Acceptor {
    
    public PaxosReplyBody prepare(Integer proposalId,Long epoch);
    
    public boolean release();
    
    public PaxosReplyBody accept(PaxosBody acceptBody);
    
    public Long lastEpoch(Integer proposalId);
    
}
