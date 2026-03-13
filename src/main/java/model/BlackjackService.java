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

public class BlackjackService {

    private static final int INITIAL_DISPENSE_COUNT = 2;
    private static final int BASE_DISPENSE_COUNT = 1;

    private final Deck deck;

    public BlackjackService(Deck deck) {
        this.deck = deck;
    }

    public static BlackjackService createDefaultService(CardShuffler cardShuffler) {
        List<Card> fullCards = CardFactory.createFullCards();
        List<Card> shuffledCards = cardShuffler.shuffle(fullCards);
        Deck deck = new Deck(shuffledCards);
        return new BlackjackService(deck);
    }

    public void drawTwoCards(Dealer dealer, Players players) {
        drawCardByParticipant(dealer, INITIAL_DISPENSE_COUNT);
        players.forEach(player -> drawCardByPlayer(player, INITIAL_DISPENSE_COUNT));
    }

    public void drawOneCard(Participant participant) {
        drawCardByParticipant(participant, BASE_DISPENSE_COUNT);
    }

    public void drawOneCard(Player player) {
        drawCardByPlayer(player, BASE_DISPENSE_COUNT);
    }

    private void drawCardByParticipant(Participant participant, int count) {
        for (int i = 0; i < count; i++) {
            participant.addCard(deck.draw());
        }
    }

    private void drawCardByPlayer(Player player, int count) {
        for (int i = 0; i < count; i++) {
            player.addCard(deck.draw());
        }
    }
}
