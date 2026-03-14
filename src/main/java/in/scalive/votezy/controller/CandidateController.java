package in.scalive.votezy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.scalive.votezy.entity.Candidate;
import in.scalive.votezy.service.CandidateService;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin
public class CandidateController {

    private CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/add")
    public ResponseEntity<Candidate> addCandidate(@RequestBody Candidate candidate) {
        Candidate savedCandidate = candidateService.addCandidate(candidate);
        return new ResponseEntity<>(savedCandidate, HttpStatus.CREATED);
    }

       @GetMapping
       public ResponseEntity<List<Candidate>> getAllCandidates() {
        List<Candidate> candidates = candidateService.getAllCandidates();
        return new ResponseEntity<List<Candidate>>(candidates, HttpStatus.OK);
      }
  
      @GetMapping("/{id}")
      public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) {
        Candidate candidate = candidateService.getCandidateById(id);
        return new ResponseEntity<Candidate>(candidate, HttpStatus.OK);
     }

       @PutMapping("/update/{id}")
       public ResponseEntity<Candidate> updateCandidate(@PathVariable Long id, @RequestBody Candidate candidate) {
        Candidate updatedCandidate = candidateService.updateCandidate(id, candidate);
        return new ResponseEntity<>(updatedCandidate, HttpStatus.OK);
      }

      @DeleteMapping("/delete/{id}")
      public ResponseEntity<String> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return new ResponseEntity<>("Candidate with id " + id + " deleted successfully", HttpStatus.OK);
    }
}