package blackjack.domain.gamer;

public class Player extends Gamer {

    // TODO Dealer도 name을 갖도록 수정
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public boolean canReceiveAdditionalCards() {
        return !isBust();
    }

    public String getName() {
        return name;
    }
}
