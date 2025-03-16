package domain;

public class Player extends Gamer {

    public Player(String name) {
        super(name);
    }

    @Override
    public void hit(Deck totalCards) {
        validateBust();
        add(totalCards);
    }

    private void validateBust() {
        if (isBust()) {
            throw new IllegalStateException("[ERROR] 버스트되면 카드를 뽑을 수 없습니다.");
        }
    }

}

