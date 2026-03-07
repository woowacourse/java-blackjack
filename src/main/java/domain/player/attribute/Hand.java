package domain.player.attribute;

import static util.Constants.ACE_HIGH_SCORE;
import static util.Constants.ACE_LOW_SCORE;
import static util.Constants.BLACK_JACK;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {

    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getCardsSize() {
        return cards.size();
    }

    public boolean isBust() {
        return calculateScore() > BLACK_JACK;
    }

    public int calculateScore() {
        int sum = calculateBaseScore();
        int aceCount = countAce();

        while (sum > BLACK_JACK && aceCount > 0) {
            sum -= (ACE_HIGH_SCORE - ACE_LOW_SCORE);
            aceCount--;
        }

        return sum;
    }

    public List<String> getCardsInfo() {
        return cards.stream()
                .map(Card::getCardInfo)
                .collect(Collectors.toList());
    }

    public String getFirstCardInfo() {
        return cards.get(0).getCardInfo();
    }

    private int calculateBaseScore() {
        int sum = 0;

        for (Card card : cards) {
            sum += getCardScore(card);
        }

        return sum;
    }

    private int countAce() {
        int aceCount = 0;

        for (Card card : cards) {
            aceCount += getAceToInt(card);
        }

        return aceCount;
    }

    private int getCardScore(Card card) {
        if (card.isAce()) {
            return ACE_HIGH_SCORE;
        }
        return card.getScore();
    }

    private int getAceToInt(Card card) {
        if (card.isAce()) {
            return 1;
        }
        return 0;
    }
}
