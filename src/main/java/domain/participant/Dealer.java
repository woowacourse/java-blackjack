package domain.participant;

import domain.GameResult;
import domain.Score;
import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;

import java.util.*;

public class Dealer extends Participant {

    private final Deck deck;

    public Dealer(Cards cards, Deck deck) {
        super(cards);
        this.deck = deck;
    }

    @Override
    public List<Card> getShowCards() {
        List<Card> cards = getCards();
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
        Score playerScore = player.getScore();
        Score dealerScore = getScore();
        if (playerScore.isBust()) {
            return GameResult.LOSE;
        }
        if (dealerScore.isBust() || playerScore.compareTo(dealerScore) > 0) {
            return GameResult.WIN;
        }
        if (playerScore.compareTo(dealerScore) < 0) {
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
        return getScore().isDealerHasToDraw();
    }
}
