package domain.card;

import domain.game.Winning;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardDeck {

    private final List<Card> deck = new ArrayList<>();

    public void takeCards(Card... cards) {
        deck.addAll(Arrays.asList(cards));
    }

    public int calculateScore() {
        int sum = deck.stream()
            .mapToInt(Card::getNumber)
            .sum();

        if (hasAce()) {
            return calculateOptimalScore(sum);
        }
        return sum;
    }

    private boolean hasAce() {
        return deck.stream()
            .anyMatch(card -> card.getRank() == Rank.ACE);
    }

    private int calculateOptimalScore(int sum){
        if (sum + Rank.ACE_LOW_HIGH_GAP <= Winning.BLACK_JACK) {
            return sum + Rank.ACE_LOW_HIGH_GAP;
        }
        return sum;
    }

    public Card getFirst() {
        return deck.getFirst();
    }

    public List<Card> getCards() {
        return List.copyOf(deck);
    }
}
