package ClearScoreAssesment.models;

public class CreditCardRequest {

    private final int creditScore;
    private final int salary;

    public CreditCardRequest(int creditScore, int salary) {

        this.creditScore = creditScore;
        this.salary = salary;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "CreditCardRequest{" +
                "creditScore=" + creditScore +
                ", salary=" + salary +
                '}';
    }
}
