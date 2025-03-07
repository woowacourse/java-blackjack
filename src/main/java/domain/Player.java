package domain;

import java.util.Objects;

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
            throw new IllegalStateException("[ERROR] 버스트되면 카드를 뽑을 수 없습니다.");
        }
    }

    public boolean isBurst() {
        return cards.isBurst();
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
        return Objects.equals(getName(), player.getName()) && Objects.equals(getCards(), player.getCards());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCards());
    }

    public String getName() {
        return this.name;
    }
}
