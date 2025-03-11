package blackjack.domain;

import blackjack.domain.card.CardManager;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Gamer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Players;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BlackjackGame {

    private static final int SPREAD_SIZE = 1;

    private final CardManager cardManager;
    private final Participants participants;

    public BlackjackGame(final CardManager cardManager, final Participants participants) {
        this.cardManager = cardManager;
        this.participants = participants;
    }

    public void spreadInitialCards() {
        int cardsCount = participants.getInitialTotalCardsSize();
        final Cards cards = cardManager.spreadCards(cardsCount);
        participants.spreadAllTwoCards(cards);
    }

    public boolean canPlayerMoreCard(final int index) {
        return participants.canPlayerGetMoreCard(index);
    }

    public void spreadOneCardToPlayer(final Gamer gamer) {
        final Cards cards = cardManager.spreadCards(SPREAD_SIZE);
        gamer.receiveCards(new Cards(List.of(cards.getFirstCard())));
    }

    public void spreadOneCardToDealer() {
        final Cards cards = cardManager.spreadCards(SPREAD_SIZE);
        participants.spreadOneCardToDealer(cards.getFirstCard());
    }

    public boolean canDealerMoreCard() {
        return participants.canDealerGetMoreCard();
    }

    public int sumCardDenomination(final Gamer gamer) {
        return gamer.calculateMaxSum();
    }

    public Map<String, ResultStatus> calculateWinningResult() {
        final int dealerSum = participants.calculateDealerMaxSum();
        final Map<String, ResultStatus> result = new HashMap<>();
        for (Gamer player : participants.getPlayers().getPlayers()) {
            final int playerSum = player.calculateMaxSum();
            result.put(player.getNickname(), ResultStatus.calculateResultStatus(playerSum, dealerSum));
        }
        return Collections.unmodifiableMap(result);
    }

    public Entry<String, Cards> showInitialDealerCard() {
        return participants.showInitialDealerCards();
    }

    public Map<String, Cards> showInitialPlayersCards() {
        return participants.showInitialParticipantsCards();
    }

    public Entry<String, Cards> showDealerCard() {
        return participants.showDealerCards();
    }

    public Map<String, Cards> showPlayersCards() {
        return participants.showPlayersCards();
    }

    public Players findExtraCardsAvailablePlayers() {
        return participants.findExtraCardsAvailablePlayers();
    }

    public Gamer getPlayer(final int index) {
        return participants.getPlayers().getPlayer(index);
    }

    public List<String> getPlayersNames() {
        return participants.getPlayersNames();
    }
}
