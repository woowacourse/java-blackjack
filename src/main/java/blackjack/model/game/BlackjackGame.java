package blackjack.model.game;

import blackjack.model.betting.BettingResult;
import blackjack.model.card.CardDeck;
import blackjack.model.participant.Participant;
import blackjack.model.participant.Participants;
import java.util.List;

public class BlackjackGame {
    private final Participants participants;
    private final CardDeck cardDeck;

    public BlackjackGame(final List<String> names, final MoneyBetter moneyBetter) {
        this.participants = new Participants(names, moneyBetter);
        this.cardDeck = new CardDeck();
    }

    public void start() {
        participants.drawFrom(cardDeck);
    }

    public void hitToParticipants(final GameSign gameSign, final TurnProgress turnProgress) {
        participants.hitFrom(cardDeck, gameSign, turnProgress);
    }

    public Participant getDealer() {
        return participants.getDealer();
    }

    public List<Participant> getPlayers() {
        return participants.getPlayers();
    }

    public List<Participant> getParticipants() {
        return participants.getParticipants();
    }

    public BettingResult createBettingResult() {
        return participants.createBettingResult();
    }
}
