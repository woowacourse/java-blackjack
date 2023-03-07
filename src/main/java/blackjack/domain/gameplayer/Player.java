package blackjack.domain.gameplayer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Player implements Person {
    private static final int BURST_NUMBER = 21;
    private static final int ACE_MAX_NUMBER = 11;
    private static final int DIFFERENCE_WITH_ACE_NUMBER = 10;
    private final Name name;
    private final List<Card> cards;

    public Player(Name name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public List<Card> showCards() {
        return Collections.unmodifiableList(cards);
    }

    public String showName() {
        return name.getName();
    }

    @Override
    public boolean isHit() {
        int totalScore = calculateScore();
        return totalScore < BURST_NUMBER;
    }

    @Override
    public int calculateScore() {
        int totalScore = cards.stream()
                .map(card -> Collections.min(card.getScore()))
                .reduce(0, Integer::sum);

        if (totalScore <= ACE_MAX_NUMBER && hasACE()) {
            return totalScore + DIFFERENCE_WITH_ACE_NUMBER;
        }
        return totalScore;
    }

    private boolean hasACE() {
        return cards.stream()
                .anyMatch(card -> card.getCardNumberToString().equals(CardNumber.ACE.getNumber()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(cards, player.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards);
    }
}
