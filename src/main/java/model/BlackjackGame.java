package model;

import java.util.List;
import model.card.Card;
import model.card.CardFactory;
import model.card.CardShuffler;
import model.card.Deck;
import model.paticipant.Dealer;
import model.paticipant.Participant;
import model.paticipant.Player;
import model.paticipant.Players;

public class BlackjackGame {

    private static final int INITIAL_DISPENSE_COUNT = 2;
    private static final int BASE_DISPENSE_COUNT = 1;

    private final Deck deck;

    public BlackjackGame(Deck deck) {
        this.deck = deck;
    }

    public static BlackjackGame setup(CardShuffler cardShuffler) {
        List<Card> fullCards = CardFactory.createFullCards();
        List<Card> shuffledCards = cardShuffler.shuffle(fullCards);
        Deck deck = new Deck(shuffledCards);
        return new BlackjackGame(deck);
    }

    public void drawInitCards(Dealer dealer, Players<? extends Player> players) {
        drawCardByParticipant(dealer, INITIAL_DISPENSE_COUNT);
        players.forEach(player -> drawCardByParticipant(player, INITIAL_DISPENSE_COUNT));
    }

    public void drawOneCard(Participant participant) {
        drawCardByParticipant(participant, BASE_DISPENSE_COUNT);
    }

    private void drawCardByParticipant(Participant participant, int count) {
        for (int i = 0; i < count; i++) {
            participant.addCard(deck.draw());
        }
    }
}
