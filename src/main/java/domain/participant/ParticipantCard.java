package domain.participant;

import domain.card.Card;
import domain.card.Score;

import java.util.ArrayList;
import java.util.List;

import static domain.card.CardNumber.ACE;

public class ParticipantCard {

    private static final int FIRST_CARD_INDEX = 0;
    private static final int BLACKJACK_SIZE = 2;
    private static final Score ACE_HIGH_POINTS = Score.create(11);
    private static final Score BLACKJACK_SCORE = Score.create(21);

    private final List<Card> cards;

    private ParticipantCard() {
        this.cards = new ArrayList<>();
    }

    public static ParticipantCard create() {
        return new ParticipantCard();
    }

    void addCard(final Card card) {
        cards.add(card);
    }

    Card getFirstCard() {
        return cards.get(FIRST_CARD_INDEX);
    }

    Score calculateScore() {
        Score score = sumCards();
        if (ACE_HIGH_POINTS.isGreaterThanAndEqual(score) && hasAce()) {
            score = score.add(ACE_HIGH_POINTS.subtract(ACE.getScore()));
        }
        return score;
    }

    boolean isBust() {
        return calculateScore().isGreaterThan(BLACKJACK_SCORE);
    }

    boolean isBlackJack() {
        return cards.size() == BLACKJACK_SIZE && calculateScore().equals(BLACKJACK_SCORE);
    }

    private Score sumCards() {
        return cards.stream()
                .map(Card::findCardScore)
                .reduce(Score.create(0), Score::add);
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    List<Card> getCards() {
        return List.copyOf(cards);
    }
}
