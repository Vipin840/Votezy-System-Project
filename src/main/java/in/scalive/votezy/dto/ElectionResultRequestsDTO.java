package in.scalive.votezy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ElectionResultRequestsDTO {
	
	    @NotBlank(message="Election name required")
        private String electionName;
	    
	    
}
