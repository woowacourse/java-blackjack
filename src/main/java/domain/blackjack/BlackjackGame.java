package domain.blackjack;

import domain.card.Card;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Players;
import domain.card.Deck;
import java.util.List;

public class BlackjackGame {
    private static final int INITIAL_CARD_AMOUNT = 2;

    private final Deck deck;
    private final BlackjackParticipants blackjackParticipants;

    private BlackjackGame(Deck deck, BlackjackParticipants blackjackParticipants) {
        this.deck = deck;
        this.blackjackParticipants = blackjackParticipants;
    }

    public static BlackjackGame from(Players players, Deck deck) {
        BlackjackParticipants blackjackParticipants = BlackjackParticipants.from(players);
        return new BlackjackGame(deck, blackjackParticipants);
    }

    public void handOutInitialCards() {
        for (Participant participant : blackjackParticipants.getAllParticipants()) {
            Cards cards = Cards.of(deck.drawCard(), deck.drawCard());
            participant.start(cards);
        }
    }

    private void handOutCardTo(Participant participant) {
        Card card = deck.drawCard();
        participant.receive(card);
    }

    public void playByAction(Participant participant, BlackjackAction blackjackAction) {
        validateParticipated(participant);

        if (isAbleToContinue(participant, blackjackAction)) {
            handOutCardTo(participant);
        }
    }

    public boolean isAbleToContinue(Participant participant, BlackjackAction blackjackAction) {
        validateParticipated(participant);

        return participant.isAbleToReceiveCard()
                && blackjackAction == BlackjackAction.HIT;
    }

    public void handOutAdditionalCardToDealer() {
        Participant dealer = getDealer();
        while (dealer.isAbleToReceiveCard()) {
            handOutCardTo(dealer);
        }
    }

    private void validateParticipated(Participant participant) {
        if (blackjackParticipants.getAllParticipants().contains(participant)) {
            return;
        }

        throw new IllegalStateException();
    }

    public int getParticipantHitCardCount(Participant participant) {
        validateParticipated(participant);
        return participant.getCurrentCardAmount() - INITIAL_CARD_AMOUNT;
    }

    public List<Participant> getParticipants() {
        return blackjackParticipants.getAllParticipants();
    }

    public Players getPlayers() {
        return blackjackParticipants.getPlayers();
    }

    public Dealer getDealer() {
        return blackjackParticipants.getDealer();
    }
}
