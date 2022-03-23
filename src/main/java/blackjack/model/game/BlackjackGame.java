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

    public void hitToPlayers(final GameSign gameSign, final TurnProgress turnProgress) {
        participants.hitFrom(cardDeck, gameSign, turnProgress);
    }

    public Participant getDealer() {
        return participants.getDealer();
    }

    public List<Participant> getPlayers() {
        return participants.getPlayers();
    }

    public BettingResult createBettingResult() {
        return participants.createBettingResult();
    }

    public List<Participant> getParticipant() {
        return participants.getParticipants();
    }
}
