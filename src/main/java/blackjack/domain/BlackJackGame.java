package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.List;

public class BlackJackGame {

    private final Participants participants;
    private final Deck deck;

    public BlackJackGame(final Participants participants, final Deck deck) {
        this.participants = participants;
        this.deck = deck;
        initGame();
    }

    private void initGame() {
        deck.shuffle();
        participants.drawCard(deck, 2);
    }

    public void drawOrNot(final boolean wantMoreDraw, final Participant participant) {
        if (!wantMoreDraw) {
            participant.changeDrawable();
            return;
        }
        participant.drawCard(deck.draw());
    }

    public Dealer dealer() {
        return participants.getDealer();
    }

    public List<Player> players() {
        return participants.getPlayers();
    }
}
