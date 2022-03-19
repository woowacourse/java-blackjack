package blackjack.model.game;

import blackjack.model.card.CardDeck;
import blackjack.model.participant.Participant;
import blackjack.model.participant.Participants;
import java.util.List;
import java.util.Map;

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

    public List<Participant> getParticipants() {
        return participants.getParticipants();
    }

    public Map<String, Double> createBettingResult() {
        return participants.createBettingResult();
    }
}
