package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.result.GameResult;

public class BlackJackGame {
    public static final int INITIAL_CARD_COUNT = 2;

    private final Deck deck;
    private final Participants participants;

    public BlackJackGame(Participants participants) {
        deck = new Deck();
        this.participants = participants;
    }

    public void drawInitialCards() {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            participants.getAllParticipants().forEach(participant -> participant.receiveCard(deck.getCard()));
        }
    }

    public void drawNewCard(Participant participant) {
        participant.receiveCard(deck.getCard());
    }

    public GameResult getGameResult() {
        return new GameResult(participants.getDealer(), participants.getPlayers());
    }

    public Participants getParticipants() {
        return participants;
    }
}
