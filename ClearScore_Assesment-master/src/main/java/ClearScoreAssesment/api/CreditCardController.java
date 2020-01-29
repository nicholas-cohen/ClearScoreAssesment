package ClearScoreAssesment.api;

import ClearScoreAssesment.models.CreditCard;
import ClearScoreAssesment.models.CreditCardRequest;
import ClearScoreAssesment.services.CreditCardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping
@RestController
public class CreditCardController {
    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping("/creditcards")
    public ResponseEntity<List<CreditCard>> getResponseCards(@RequestBody CreditCardRequest request) throws JsonProcessingException {
        creditCardService.executeCreditCardRequest(request);
        List<CreditCard> creditCardList = creditCardService.getCreditCards();
        return new ResponseEntity<List<CreditCard>>(creditCardList, HttpStatus.OK);
    }

}
