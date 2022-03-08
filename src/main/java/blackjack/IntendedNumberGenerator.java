package blackjack;

public class IntendedNumberGenerator implements NumberGenerator {

    int number;

    public IntendedNumberGenerator(int number) {
        this.number = number;
    }
    @Override
    public int generateNumber() {
        return number;
    }
}
