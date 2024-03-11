package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private static final int ADDITIONAL_ACE_CARD_SCORE = CardNumber.ACE.getAdditionalAceScore();
    private static final int MAX_BLACK_JACK_SCORE = 21;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int countMaxScore() {
        int score = countMatchScore();
        if (this.hasAce()) {
            score = convertAceScore(score);
        }
        if (isOver(score)) {
            return 0;
        }
        return score;
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    private int convertAceScore(int maxResultScore) {
        if (maxResultScore + ADDITIONAL_ACE_CARD_SCORE <= MAX_BLACK_JACK_SCORE) {
            maxResultScore += ADDITIONAL_ACE_CARD_SCORE;
        }
        return maxResultScore;
    }

    private int countMatchScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public boolean isOver(int score) {
        return score > MAX_BLACK_JACK_SCORE;
    }

    public int getMaxGameScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public List<String> getCardsName() {
        return cards.stream()
                .map(Card::getCardName)
                .collect(Collectors.toList());
    }
}
