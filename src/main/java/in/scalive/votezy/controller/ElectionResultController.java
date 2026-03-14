package in.scalive.votezy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.scalive.votezy.dto.ElectionResultRequestsDTO;
import in.scalive.votezy.dto.ElectionResultResponseDTO;
import in.scalive.votezy.entity.ElectionResult;
import in.scalive.votezy.service.ElectionResultService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/election-result")
@CrossOrigin
public class ElectionResultController {

    private ElectionResultService electionResultService;

    @Autowired
    public ElectionResultController(ElectionResultService electionResultService) {
        this.electionResultService = electionResultService;
    }

    @PostMapping("/declare")
    public ResponseEntity<ElectionResultResponseDTO> declareElectionResult(
            @RequestBody @Valid ElectionResultRequestsDTO electionResultDTO) {

        ElectionResult result =
                electionResultService.declareElectionResult(
                        electionResultDTO.getElectionName());

        ElectionResultResponseDTO responseDTO = new ElectionResultResponseDTO();
        responseDTO.setElectionName(result.getElectionName());
        responseDTO.setTotalVotes(result.getTotalVotes());
        responseDTO.setWinnerId(result.getWinnerId());
        responseDTO.setWinnerVotes(result.getWinner().getVoteCount());

        return ResponseEntity.ok(responseDTO);
    }
    
    @GetMapping
    public ResponseEntity<List<ElectionResult>> getAllResult(){
    	    List<ElectionResult>results = electionResultService.getAllResults();
    	    return  ResponseEntity.ok(results);
    }
    
    
    
}