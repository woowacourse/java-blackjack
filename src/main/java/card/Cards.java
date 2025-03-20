package card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    private int totalWithoutAce() {
        return cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(card -> card.getCardNumber().getValues().getFirst())
                .sum();
    }

    private int aceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public int calculateScore() {
        int score = totalWithoutAce();

        if (aceCount() >= 1) {
            int aceScoreOne = CardNumber.ACE.getValues().get(0);
            int aceScoreEleven = CardNumber.ACE.getValues().get(1);

            int scoreOneAce = (score + aceScoreOne) + (aceScoreOne * (aceCount() - 1));
            int scoreElevenAce = (score + aceScoreEleven) + (aceScoreOne * (aceCount() - 1));

            if (scoreOneAce > 21 && scoreElevenAce > 21) {
                return Math.min(scoreOneAce, scoreElevenAce);
            }
            return Math.max(scoreOneAce, scoreElevenAce);
        }

        return score;
    }

    public Card get(int index) {
        return cards.get(index);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int size() {
        return cards.size();
    }
}
