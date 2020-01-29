package ClearScoreAssesment.models;



public class CreditCard{

    private final String provider;
    private final String name;
    private final double apr;
    private final double cardScore;

    public CreditCard( String provider,String name, double apr, double cardScore) {

        this.provider = provider;
        this.name = name;
        this.apr = apr;
        this.cardScore = cardScore;
    }


    public String getProvider() {
        return provider;
    }
    public String getName() {
        return name;
    }
    public double getApr() {
        return apr;
    }
    public double getCardScore() {
        return cardScore;
    }


}
