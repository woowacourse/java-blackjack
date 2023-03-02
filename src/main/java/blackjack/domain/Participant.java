package blackjack.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Participant {
    private static final int MAX_SCORE_TO_RECEIVE = 21;

    List<Card> cards;
    Score score;

    public Participant(List<Card> cards) {
        this.cards = cards;
        this.score = new Score();
    }

    private List<TrumpNumber> extractNumbers() {
        return cards.stream()
                .map(Card::getTrumpNumber)
                .collect(Collectors.toList());
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public boolean isAbleToReceive() {
        score.calculateScore(extractNumbers());
        return score.getScore() <= MAX_SCORE_TO_RECEIVE;
    }

    public List<Card> getCards() {
        return cards;
    }

}
