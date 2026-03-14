package in.scalive.votezy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.scalive.votezy.dto.VoteRequestDTO;
import in.scalive.votezy.dto.VoteResponseDTO;
import in.scalive.votezy.entity.Vote;
import in.scalive.votezy.service.VotingService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/votes")
@CrossOrigin
public class VotingController {

    private VotingService votingService;

    @Autowired
    public VotingController(VotingService votingService) {
        this.votingService = votingService;
    }

    @PostMapping("/cast")
    public ResponseEntity<VoteResponseDTO> castVote(@RequestBody @Valid VoteRequestDTO voteRequest) {

        Vote vote = votingService.castVote(
                voteRequest.getVoterId(),
                voteRequest.getCandidateId()
        );

        VoteResponseDTO response = new VoteResponseDTO(
                "vote casted successfully",
                true,
                vote.getVoter().getId(),
                vote.getCandidate().getId()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Vote>> getAllVotes(){
    	List<Vote> voteList = votingService.getAllVotes();
    	return new ResponseEntity<>(voteList,HttpStatus.OK);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}