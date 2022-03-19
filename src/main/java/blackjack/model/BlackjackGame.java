package blackjack.model;

import blackjack.model.card.CardDeck;
import blackjack.model.participant.Participant;
import blackjack.model.participant.Participants;
import blackjack.view.TurnProgress;
import blackjack.view.GameSign;
import blackjack.view.MoneyBetter;
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

    public List<Participant> getParticipants() {
        return participants.getParticipants();
    }

    public void hitUntilPossible(final GameSign gameSign, final TurnProgress turnProgress) {
        participants.hitFrom(cardDeck, gameSign, turnProgress);
    }
}
