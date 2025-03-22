package model.participant;

import model.card.Card;
import model.card.CardDeck;

import java.util.List;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int STARTING_CARD_AMOUNT = 2;
    private static final int ADDITIONAL_CARD_AMOUNT = 1;
    private static final int STANDING_CONDITION = 17;


    private final String nickname;
    private final CardDeck deck;

    private Dealer(String nickname) {
        this.nickname = nickname;
        this.deck = new CardDeck();
    }

    public static Dealer create() {
        return new Dealer(DEALER_NAME);
    }

    public void distributeStartingHand(Participant participant) {
        distributeCardByAmount(participant, STARTING_CARD_AMOUNT);
    }

    public void distributeAdditionalCard(Participant participant) {
        distributeCardByAmount(participant, ADDITIONAL_CARD_AMOUNT);
    }

    private void distributeCardByAmount(Participant participant, int amount) {
        List<Card> pickCards = deck.pickCard(amount);
        participant.addCards(pickCards);
    }


    @Override
    public boolean canHit() {
        return ableToDraw();
    }

    @Override
    public boolean ableToDraw() {
        return getScore() < STANDING_CONDITION;
    }

    @Override
    public String getNickname() {
        return nickname;
    }
}
