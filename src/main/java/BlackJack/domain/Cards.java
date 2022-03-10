package BlackJack.domain;

import java.util.List;

public class Cards {
    public static final int BUST_LINE = 21;
    public static final int EXTRA_SCORE = 10;
    private List<Card> deck;

    public Cards(List<Card> deck) {
        this.deck = deck;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void add(Card card) {
        deck.add(card);
    }

    public int calculateScore() {
        int score = deck.stream()
                .mapToInt(card -> card.getNumber().getValue())
                .sum();

        return addAceScore(score);
    }

    private int addAceScore(int score) {
        long countAce = deck.stream()
                .filter(card -> card.getNumber().getDenomination().equals("A"))
                .count();

        for(int i = 0; i < countAce; i++){
            if(score + EXTRA_SCORE <= BUST_LINE){
                score += EXTRA_SCORE;
            }
        }
        return score;
    }
}
