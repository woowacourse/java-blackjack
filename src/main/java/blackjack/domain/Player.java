package blackjack.domain;

public class Player extends Gamer{
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int compareCardsSumTo(int sum) {
        return Integer.compare(this.getCardGroupSum(), sum);
    }
}
