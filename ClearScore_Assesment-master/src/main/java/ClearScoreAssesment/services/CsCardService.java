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
public class CsCardService {
    @Value("${CSCARDS_ENDPOINT}")
    private String csCardsResourceUrl;

    @Autowired
    private  ManageCreditCardsRepository creditCardsRepository;

    public void setCsCardsResourceUrl(String csCardsResourceUrl) {
        this.csCardsResourceUrl = csCardsResourceUrl;
    }

    public CsCardResponse[] retrieveCSCards(CreditCardRequest request) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        //setup http headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((MediaType.APPLICATION_JSON));

        //Create CSCard JSON object
        JsonObject csCardJson = new JsonObject();
        csCardJson.addProperty("name","John Smith");
        csCardJson.addProperty("creditScore",request.getCreditScore());

        //Retrieve JSON and Parse into CSCardResponse Object
        HttpEntity<String> csCardsRequest =
                new HttpEntity<String>(csCardJson.toString(), headers);
        String responseEntity = restTemplate.postForObject(csCardsResourceUrl,csCardsRequest,String.class);
        CsCardResponse[] csCardResponses = mapper.readValue(responseEntity,CsCardResponse[].class);

        return csCardResponses;
    }

    public void addCreditCards(CsCardResponse[] csCardResponses){
        //Create Credit Card model from response
        for (CsCardResponse csCardResponse:
                csCardResponses) {
            CreditCard tempCreditCard = new CreditCard (
                    "CsCard",csCardResponse.getCardName(),
                    csCardResponse.getApr(),
                    csCardResponse.sortingScore()
            );

            //add to repos
           creditCardsRepository.insertCreditCard(tempCreditCard);
        }
    }



    public static class CsCardResponse {
        private  String cardName;
        private  double apr;
        private  double eligibility;



        public String getCardName() {
            return cardName;
        }

        public double getApr() {
            return apr;
        }

        public double getEligibility() {
            return eligibility;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public void setApr(double apr) {
            this.apr = apr;
        }

        public void setEligibility(double eligibility) {
            this.eligibility = eligibility;
        }

        public double sortingScore(){
            return eligibility * Math.pow((1/apr),2);
        }


    }
}

