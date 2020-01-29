package ClearScoreAssesment.models;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class CreditCardResponse {
    private final List<CreditCard> creditCards;

    public CreditCardResponse(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    //Sort the credit cards according to score with a custom comparator
    public void SortCreditCards(){
        Collections.sort(creditCards, new Comparator<CreditCard>() {
            @Override
            public int compare(CreditCard c1, CreditCard c2) {
                 double d1 =c1.getCardScore();
                 double d2 =c2.getCardScore();

                 return Double.compare(d1,d2);
            }
        }.reversed());
    }
}