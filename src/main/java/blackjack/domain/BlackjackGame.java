package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardManager;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.List;

public class BlackjackGame {

    private final CardManager cardManager;
    private final Participants participants;

    public BlackjackGame(final CardManager cardManager, final Participants participants) {
        this.cardManager = cardManager;
        this.participants = participants;
    }

    public void spreadInitialCards() {
        int cardsCount = 2 * participants.getParticipantSize();
        final List<Card> cards = cardManager.spreadCards(cardsCount);
        participants.spreadAllTwoCards(cards);
    }

    public boolean canSpread(final int index) {
        return participants.canGetMoreCard(index);
    }

    public void spreadOneCard(final int index) {
        final List<Card> cards = cardManager.spreadCards(1);
        participants.spreadOneCard(index, cards.getFirst());
    }

    public Player getPlayer(final int index) {
        return participants.getPlayers().get(index);
    }

    public int getPlayerSize() {
        return participants.getPlayers().size();
    }
}
