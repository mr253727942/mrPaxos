/**
 * 
 */
package nmid.mrPaxos.role.imp;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import nmid.mrPaxos.paxosMessage.PaxosBody;
import nmid.mrPaxos.paxosMessage.PaxosReplyBody;
import nmid.mrPaxos.role.Acceptor;

/**
 * @author MaRong
 * @date 2016年4月4日 下午3:11:12
 * @description 
 */
public class AcceptorImp implements Acceptor{
    
    //互斥锁
    private AtomicInteger mutex = new AtomicInteger(0) ;
    
    
    private ConcurrentHashMap<Integer,PaxosBody> paxosMap = new ConcurrentHashMap<Integer,PaxosBody>();
    public boolean isLock() {
	int temp = mutex.get(); 
	return temp == 1 ? true:false;
    }
    
    public void lock() {
	mutex.addAndGet(1);
    }
    @Override
    public PaxosReplyBody prepare(Integer proposalId, Long epoch) {
	PaxosReplyBody reply = new PaxosReplyBody();
	
	
	//如果不包含proposalId,则添加
	if(!paxosMap.containsKey(proposalId)) {
	    reply.setFlag(true);
	    reply.setValue(null);
	    reply.setEpoch(epoch);
	    reply.setProposalId(proposalId);
	    
	    return reply;
	}else {
	//如果包含了proposalId
	    
	    //如果请求的epoch比当前的小，直接拒绝
	    if(epoch < paxosMap.get(proposalId).getEpoch()) {
		reply.setFlag(false);
		return reply;
	    //如果请求的epoch比当前的大，首先判断是否有值
	    }else {
		String value = paxosMap.get(proposalId).getValue();
		if(value == null) {
		    reply.setFlag(true);
		    reply.setEpoch(epoch);
		    reply.setProposalId(proposalId);
		    reply.setValue(null);
		    
		    //同时增加最新访问权
		    PaxosBody body = new PaxosBody();
		    body.setEpoch(epoch);
		    body.setProposalId(proposalId);
		    body.setValue(null);
		    
		    paxosMap.put(proposalId, body);
		    
		    
		}else {
		    reply.setFlag(false);
		    reply.setEpoch(paxosMap.get(proposalId).getEpoch());
		    reply.setProposalId(proposalId);
		    reply.setValue(value);
		}
	    }
	    
	    
	    return reply;
	    
	    
	}
    }
    @Override
    public boolean release() {
	int temp = mutex.get();
	temp = temp-1;
	return mutex.compareAndSet(1, temp);
    }
    @Override
    public PaxosReplyBody accept(PaxosBody acceptBody) {
	PaxosReplyBody reply = new PaxosReplyBody();
	Integer proposalId = acceptBody.getProposalId();
	PaxosBody currentBody = paxosMap.get(proposalId);
	//如果里面还没有paxos则添加
	if(!paxosMap.containsKey(proposalId)){
	    paxosMap.put(proposalId, acceptBody);
	    reply.setFlag(true);
	    reply.setEpoch(acceptBody.getEpoch());
	    reply.setValue(acceptBody.getValue());
	    reply.setProposalId(acceptBody.getProposalId());
	    return reply;
	}else {
	    //判断是否是自己已经发放过的访问权
	    if(acceptBody.getEpoch() < currentBody.getEpoch()) {
		reply.setFlag(false);
		return reply;
	    }else {
		String  value = currentBody.getValue();
		//如果为空则让自己value成为重要的
		if(value == null) {
		    reply.setFlag(true);
		    reply.setEpoch(acceptBody.getEpoch());
		    reply.setValue(acceptBody.getValue());
		    reply.setProposalId(acceptBody.getProposalId());
		    return reply;
		}else {
		    reply.setFlag(false);
		    reply.setEpoch(currentBody.getEpoch());
		    reply.setValue(value);
		    reply.setProposalId(acceptBody.getProposalId());
		    return reply;
		}
	    }
	    
	    
	}
    }

    @Override
    public Long lastEpoch(Integer proposalId) {
	
	return paxosMap.get(proposalId).getEpoch();
    }

}
