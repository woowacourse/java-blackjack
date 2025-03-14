package domain.participant;

import domain.GameResult;
import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;

import java.util.*;

public class Dealer extends Participant {
    private static final int DRAW_THRESHOLD = 16;

    private final Deck deck;

    public Dealer(Cards cards, Deck deck) {
        super(cards);
        this.deck = deck;
    }

    @Override
    public List<Card> getShowCards() {
        List<Card> cards = getCards().getValues();
        return cards.subList(0, 1);
    }

    public void handoutCards(Players players) {
        List<Participant> participants = new ArrayList<>(players.get());
        participants.add(this);
        deck.handoutCards(participants);
    }

    public void giveOneCardTo(Participant participant) {
        participant.addCard(deck.pick());
    }

    public GameResult getResult(Player player) {
        if (player.isBust()) {
            return GameResult.LOSE;
        }
        if (this.isBust() || player.getCardScore() > this.getCardScore()) {
            return GameResult.WIN;
        }
        if (player.getCardScore() < this.getCardScore()) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    public int drawCards() {
        int count = 0;
        while (hasToDraw()) {
            addCard(deck.pick());
            count++;
        }
        return count;
    }

    private boolean hasToDraw() {
        return this.getCardScore() <= DRAW_THRESHOLD;
    }
}
