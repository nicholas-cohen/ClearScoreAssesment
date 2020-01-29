package ClearScoreAssesment.services;

import ClearScoreAssesment.models.CreditCard;
import ClearScoreAssesment.models.CreditCardRequest;
import ClearScoreAssesment.repos.ManageCreditCardsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ScoredCardService {
    @Value("${SCOREDCARDS_ENDPOINT}")
    private  String scoredCardsResourceUrl;
    @Autowired
    private ManageCreditCardsRepository creditCardsRepository;

    public void setScoredCardsResourceUrl(String scoredCardsResourceUrl) {
        this.scoredCardsResourceUrl = scoredCardsResourceUrl;
    }

    public ScoredCardsResponse[] retrieveScoredCards(CreditCardRequest request) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((MediaType.APPLICATION_JSON));

        //create ScoredCards JSON Object

        JsonObject scoredCardsJson = new JsonObject();
        scoredCardsJson.addProperty("name","John Smith");
        scoredCardsJson.addProperty("score",request.getCreditScore());
        scoredCardsJson.addProperty("salary",request.getSalary());

        HttpEntity<String> scoredCardsRequest =
                new HttpEntity<String>(scoredCardsJson.toString(), headers);

        String responseEntity = restTemplate.postForObject(scoredCardsResourceUrl,scoredCardsRequest,String.class);
        ScoredCardsResponse[] scoredCardsResponses = mapper.readValue(responseEntity,ScoredCardsResponse[].class);

        return scoredCardsResponses;
    }

    public void addCreditCards(ScoredCardsResponse[] scoredCardsResponses){
        for (ScoredCardsResponse scResponse: scoredCardsResponses) {
            CreditCard tempCreditCard = new CreditCard(
                    "ScoredCards",
                    scResponse.getCard(),
                    scResponse.getApr(),
                    scResponse.sortingScore()
            );

            creditCardsRepository.insertCreditCard(tempCreditCard);
        }
    }

    public static class ScoredCardsResponse{
        private  String card;
        private  double apr;
        private  double approvalRating;



        public String getCard() {
            return card;
        }

        public double getApr() {
            return apr;
        }

        public double getApprovalRating() {
            return approvalRating;
        }

        public void setCard(String card) {
            this.card = card;
        }

        public void setApr(double apr) {
            this.apr = apr;
        }

        public void setApprovalRating(double approvalRating) {
            this.approvalRating = approvalRating;
        }

        public double sortingScore(){
            double eligibility = approvalRating*10;
            return eligibility*Math.pow((1/apr),2);
        }
    }
}
