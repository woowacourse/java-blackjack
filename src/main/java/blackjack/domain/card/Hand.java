package blackjack.domain.card;

import static blackjack.domain.card.Rank.ACE_VALUE;
import static blackjack.domain.participant.Dealer.TWENTY_ONE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Hand createEmptyHand() {
        return new Hand(new ArrayList<>());
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public boolean isBust() {
        return sumAceToOne() > TWENTY_ONE;
    }

    //todo 리팩터링 필요
    private int sumAceToOne() {
        return cards.stream()
                .mapToInt(Card::getRankValue)
                .sum();
    }

    //todo 리팩터링 필요
    public int sumAceToEleven() {
        return cards.stream()
                .mapToInt(this::getAceValue)
                .sum();
    }

    private int getAceValue(Card card) {
        if (card.isAce()) {
            return ACE_VALUE;
        }
        return card.getRankValue();
    }

    public int sumTotalExceptAce() {
        return cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(Card::getRankValue)
                .sum();
    }

    public int getCountOfAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }
}
