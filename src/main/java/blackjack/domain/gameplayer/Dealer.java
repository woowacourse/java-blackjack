package blackjack.domain.gameplayer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public final class Dealer extends BlackJackParticipant {
    public static final Score hitUpperBound = Score.of(17);

    public Dealer() {
        super(new Cards());
    }

    @Override
    public boolean canContinue() {
        Score totalScore = calculateScore();
        return totalScore.isLessThan(hitUpperBound);
    }

    @Override
    public List<Card> showCards() {
        return List.of(cards.getOneCard());
    }

    public List<Card> showAllCards() {
        return cards.getCards();
    }
}
