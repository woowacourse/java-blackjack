package model.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import model.card.Card;

public abstract class Player implements NoticeStatus {
    protected final static int MAXIMUM_SUM = 21;

    protected final String name;
    protected List<Card> cards;

    public Player(String name) {
        validateName(name);
        this.name = name;
        this.cards = new ArrayList<>();
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("참가자의 이름은 공백이거나 null일 수 없습니다.");
        }
    }

    public abstract boolean isDealer();

    public void addCards(List<Card> card) {
        cards.addAll(card);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int sumCardNumbers() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.minimumNumber();
        }
        List<Integer> differenceNumbers = filterNonZeroDifferences();
        return differenceNumbers.stream()
                .reduce(sum, Player::sumIfUnderMaximum);
    }

    private List<Integer> filterNonZeroDifferences() {
        return cards.stream()
                .map(Card::subtractMaxMinNumber)
                .filter(subtractNumber -> subtractNumber != 0)
                .toList();
    }

    private static int sumIfUnderMaximum(Integer result, Integer number) {
        if (result + number <= MAXIMUM_SUM) {
            return result + number;
        }
        return result;
    }

    public boolean isOverMaximumSum() {
        return sumCardNumbers() > MAXIMUM_SUM;
    }

    public int findPlayerDifference() {
        return Math.abs(MAXIMUM_SUM - sumCardNumbers());
    }

    public boolean isSameName(String name) {
        return this.name.equals(name);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
