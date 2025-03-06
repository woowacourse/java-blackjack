package domain;

import java.util.Objects;

//GameManager
//Map<P, Cs>
//setGame

public class Player {

    private final String name;
    private final Cards cards = new Cards();

    public Player(String name) {
        this.name = name;
    }

    public void prepareGame(Cards totalCard) {
        hit(totalCard);
        hit(totalCard);
    }

    public void hit(Cards totalCards) {
        validateBurst();
        cards.add(totalCards.extractCard());
    }

    private void validateBurst() {
        if (cards.isBurst()) {
            throw new IllegalStateException("[ERROR] 버스트되면 초과하면 카드를 뽑을 수 없습니다.");
        }
    }

    public int getScore() {
        return cards.calculateTotalPoint();
    }

    public Cards getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
