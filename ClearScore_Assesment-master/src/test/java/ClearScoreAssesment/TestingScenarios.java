package ClearScoreAssesment;

import ClearScoreAssesment.models.CreditCard;
import ClearScoreAssesment.models.CreditCardRequest;
import ClearScoreAssesment.repos.ManageCreditCardsRepository;
import ClearScoreAssesment.services.CsCardService;
import ClearScoreAssesment.services.ScoredCardService;

import java.util.ArrayList;
import java.util.List;

public class TestingScenarios {
    public static final CreditCardRequest cardRequest = new CreditCardRequest(500,28000);
    public static final CreditCard creditCard1 = new CreditCard("CSCard","SuperSaver",3.8,19.3);
    public static final CreditCard creditCard2 = new CreditCard("ScoredCard","SuperSaverScored",2.5,112.3);
    public static  CsCardService.CsCardResponse csCardresponse;


    public static ManageCreditCardsRepository createCreditCardList(){
        ManageCreditCardsRepository repo = new ManageCreditCardsRepository();
        repo.insertCreditCard(creditCard1);
        repo.insertCreditCard(creditCard2);
        return repo;
    }

    public static CsCardService.CsCardResponse CreateSortingScoreTest (){
        csCardresponse= new CsCardService.CsCardResponse();
        csCardresponse.setApr(21.4);
        csCardresponse.setCardName("SuperSaver");
        csCardresponse.setEligibility(6.3);

        return csCardresponse;
    }
}
