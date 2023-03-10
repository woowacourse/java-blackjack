package blackjack.domain.gameplayer;

import blackjack.domain.Score;
import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class BlackJackParticipant implements User {

    private final List<Card> cards;

    public BlackJackParticipant() {
        this.cards = new ArrayList<>();
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public List<Card> showCards() {
        return null;
    }

    @Override
    public boolean canContinue() {
        return false;
    }

    @Override
    public Score calculateScore() {
        return null;
    }
}
