package blackjack.domain.gameplayer;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class BlackJackParticipant implements User {

    protected final Cards cards;

    public BlackJackParticipant(Cards cards) {
        this.cards = cards;
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public Score calculateScore() {
        return Score.from(cards);
    }
}
