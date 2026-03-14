package in.scalive.votezy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.scalive.votezy.controller.VoterController;
import in.scalive.votezy.entity.Candidate;
import in.scalive.votezy.entity.Vote;
import in.scalive.votezy.exception.ResourceNotFoundException;
import in.scalive.votezy.repository.CandidateRepository;

@Service
public class CandidateService {

    private final VoterController voterController;

    private CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository, VoterController voterController) {
        this.candidateRepository = candidateRepository;
        this.voterController = voterController;
    }

    public Candidate addCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Candidate getCandidateById(Long id) {
        Candidate candidate = candidateRepository.findById(id).orElse(null);
        if (candidate == null) {
            throw new ResourceNotFoundException("Candidate with id: " + id + " Not Found");
        }
        return candidate;
    }

    public Candidate updateCandidate(Long id, Candidate updatedCandidate) {
        Candidate candidate = getCandidateById(id);
        if(updatedCandidate.getName()!=null) {
        	candidate.setParty(updatedCandidate.getName());
        }
        
        if(updatedCandidate.getParty()!=null) {
        	candidate.setParty(updatedCandidate.getParty());
        }
        
        return candidateRepository.save(candidate);
    }
    
    
    

    public void deleteCandidate(Long id) {
    	Candidate candidate = getCandidateById(id);
    	List<Vote>votes = candidate.getVotes();  
    	for(Vote v : votes) {
    		v.setCandidate(null);
    	}
    	candidate.getVotes().clear();
    	candidateRepository.delete(candidate);
    }
    
    
    
    
    
    
    
    
}