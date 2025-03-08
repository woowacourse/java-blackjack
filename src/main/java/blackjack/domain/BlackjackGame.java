package blackjack.domain;

import blackjack.domain.card.CardManager;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Gamer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    public int sumCardDenomination(final Gamer gamer) {
        return gamer.calculateMaxSum();
    }

    public Map<String, ResultStatus> calculateWinningResult() {
        final int dealerSum = participants.calculateDealerMaxSum();
        final Map<String, ResultStatus> result = new HashMap<>();
        for (Player player : participants.getPlayers()) {
            final int playerSum = player.calculateMaxSum();
            result.put(player.getNickname(), calculateResultStatus(playerSum, dealerSum));
        }
        return Collections.unmodifiableMap(result);
    }

    public ResultStatus calculateResultStatus(final int sum, final int comparedSum) {
        if (sum <= BLACKJACK_NUMBER) {
            return calculateResultStatusUnder21(sum, comparedSum);
        }
        return ResultStatus.LOSE;
    }

    private ResultStatus calculateResultStatusUnder21(final int sum, final int comparedSum) {
        if (comparedSum <= BLACKJACK_NUMBER) {
            return calculateResultStatusBothUnder21(sum, comparedSum);
        }
        return ResultStatus.WIN;
    }

    private ResultStatus calculateResultStatusBothUnder21(final int sum, final int comparedSum) {
        if (sum > comparedSum) {
            return ResultStatus.WIN;
        }
        if (sum == comparedSum) {
            return ResultStatus.DRAW;
        }
        return ResultStatus.LOSE;
    }

    public Player getPlayer(final int index) {
        return participants.getPlayers().get(index);
    }

    public int getPlayerSize() {
        return participants.getPlayers().size();
    }
}
