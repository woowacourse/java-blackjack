package blackjack.domain;

import blackjack.domain.card.CardManager;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

public class BlackjackGame {

    private final CardManager cardManager;
    private final Participants participants;

    public BlackjackGame(final CardManager cardManager, final Participants participants) {
        this.cardManager = cardManager;
        this.participants = participants;
    }

    public void spreadInitialCards() {
        int cardsCount = 2 * participants.getParticipantSize();
        final Cards cards = cardManager.spreadCards(cardsCount);
        participants.spreadAllTwoCards(cards);
    }

    public boolean canPlayerMoreCard(final int index) {
        return participants.canPlayerGetMoreCard(index);
    }

    public void spreadOneCardToPlayer(final int index) {
        final Cards cards = cardManager.spreadCards(1);
        participants.spreadOneCardToPlayer(index, cards.getFirstCard());
    }

    public boolean canDealerMoreCard() {
        return participants.canDealerGetMoreCard();
    }

    public void spreadOneCardToDealer() {
        final Cards cards = cardManager.spreadCards(1);
        participants.spreadOneCardToDealer(cards.getFirstCard());
    }

    public Player getPlayer(final int index) {
        return participants.getPlayers().get(index);
    }

    public int getPlayerSize() {
        return participants.getPlayers().size();
    }

    public int sumDealerCardDenomination(final Dealer dealer) {
        return dealer.calculateMaxSum();
    }

    public int sumPlayerCardDenomination(final Player player) {
        return player.calculateMaxSum();
    }
}
