package ClearScoreAssesment;

import ClearScoreAssesment.models.CreditCard;
import ClearScoreAssesment.repos.ManageCreditCardsRepository;
import ClearScoreAssesment.services.CreditCardService;
import ClearScoreAssesment.services.CsCardService;
import ClearScoreAssesment.services.ScoredCardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UnitTest {



    @Test
    public void CsCardRetrievalTest() throws JsonProcessingException {
        CsCardService testedService = new CsCardService();
        testedService.setCsCardsResourceUrl("https://app.clearscore.com/api/global/backend-tech-test/v1/cards");
        CsCardService.CsCardResponse[] responses = testedService.retrieveCSCards(TestingScenarios.cardRequest);

        assertEquals(responses.length,2);
    }

    @Test
    public void ScoredCardRetrievalTest() throws JsonProcessingException {
        ScoredCardService testedService = new ScoredCardService();
        testedService.setScoredCardsResourceUrl("https://app.clearscore.com/api/global/backend-tech-test/v2/creditcards");
        ScoredCardService.ScoredCardsResponse[] responses = testedService.retrieveScoredCards(TestingScenarios.cardRequest);

        assertEquals(responses.length,1);
    }

    @Test
    public void getCreditCardsTestRepo(){
        ManageCreditCardsRepository testedRepo = TestingScenarios.createCreditCardList();

        List<CreditCard> newList = testedRepo.getCreditCards();
        assertEquals(2,newList.size());
    }

    @Test
    public void creditCardScoreTest(){
        CsCardService.CsCardResponse response = TestingScenarios.CreateSortingScoreTest();
        double sortingRating = response.sortingScore();

        assertEquals(0.013756659970303087,sortingRating);
    }




}
