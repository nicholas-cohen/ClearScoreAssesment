package ClearScoreAssesment.repos;

import ClearScoreAssesment.models.CreditCard;
import ClearScoreAssesment.models.CreditCardRequest;
import ClearScoreAssesment.models.CreditCardResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ManageCreditCardsRepository {
    private static List<CreditCard> DB = new ArrayList<>();
    private static CreditCardRequest request;
    private static CreditCardResponse response;





    public List<CreditCard> getCreditCards(){
        response = new CreditCardResponse(DB);
        response.SortCreditCards();
        return response.getCreditCards();
    }

    public void insertCreditCard(CreditCard creditCard){
        DB.add(creditCard);
    }

    public void createRequest(CreditCardRequest newRequest){
        request = new CreditCardRequest(newRequest.getCreditScore(),newRequest.getSalary());
    }

}
