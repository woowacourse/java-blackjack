package domain.game;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import domain.result.WinningResult;
import java.util.List;

public final class BlackJackGame {

    private final Participants participants;
    private final CardDeck cardDeck;

    public BlackJackGame(final Participants participants, final CardDeck cardDeck) {
        this.participants = participants;
        this.cardDeck = cardDeck;
    }

    public void distributeInitialCards() {
        participants.distributeInitialCards(cardDeck);
    }

    public void giveCardTo(Participant participant) {
        participant.receiveCard(cardDeck.getCard());
    }

    public WinningResult makeResult() {
        return new WinningResult(participants);
    }

    public Participants getParticipants() {
        return participants;
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public void addBet(final Player player, final Money money) {

    }
}
