package domain;

public class Player extends Gamer {

    public Player(String name) {
        super(name);
    }

    public void prepareGame(CardDeck deck) {
        hit(deck);
        hit(deck);
    }

    @Override
    public void hit(CardDeck deck) {
        validateBurst();
        addFrom(deck);
    }

    private void validateBurst() {
        if (isBurst()) {
            throw new IllegalStateException("[ERROR] 버스트되면 카드를 뽑을 수 없습니다.");
        }
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
