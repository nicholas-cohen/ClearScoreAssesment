package ClearScoreAssesment.services;

import ClearScoreAssesment.models.CreditCard;
import ClearScoreAssesment.models.CreditCardRequest;
import ClearScoreAssesment.repos.ManageCreditCardsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CreditCardService {
    @Autowired
    private  ManageCreditCardsRepository creditCardsRepository;
    @Autowired
    private  CsCardService csCardService;
    @Autowired
    private  ScoredCardService scoredCardService;



    public void executeCreditCardRequest(CreditCardRequest request) throws JsonProcessingException {
        //add request to repo
        creditCardsRepository.createRequest(request);
        //call CsCards api, convert to CreditCard model and add to repo
        csCardService.addCreditCards(csCardService.retrieveCSCards(request));
        //call ScoredCards api, convert to CreditCard model and add to repo
        scoredCardService.addCreditCards(scoredCardService.retrieveScoredCards(request));

    }


    public List<CreditCard> getCreditCards(){
        return creditCardsRepository.getCreditCards();
    }
}
