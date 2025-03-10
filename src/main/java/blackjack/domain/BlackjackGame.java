package blackjack.domain;

import static blackjack.domain.card.Cards.BLACKJACK_NUMBER;

import blackjack.domain.card.CardManager;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BlackjackGame {

    private final CardManager cardManager;
    private final Participants participants;

    public BlackjackGame(final CardManager cardManager, final Participants participants) {
        this.cardManager = cardManager;
        this.participants = participants;
    }

    public void spreadInitialCards() {
        int cardsCount = participants.calculateInitialCardsCount();
        final Cards cards = cardManager.spreadCards(cardsCount);
        participants.spreadAllTwoCards(cards);
    }

    public boolean canPlayerMoreCard(final Player player) {
        return participants.canPlayerGetMoreCard(player);
    }

    public void spreadOneCardToPlayer(final Player player) {
        final Cards cards = cardManager.spreadCards(1);
        participants.spreadOneCardToPlayer(player, cards.getFirstCard());
    }

    public boolean canDealerMoreCard() {
        return participants.canDealerGetMoreCard();
    }

    public void spreadOneCardToDealer() {
        final Cards cards = cardManager.spreadCards(1);
        participants.spreadOneCardToDealer(cards.getFirstCard());
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

    public void calculateWinningResult(boolean isPush) {
        if (isPush) {
            calculatePushPlayersEarnings();
        }

        if (!isPush) {
            Map<Player, ResultStatus> playersResultStatus = makePlayersResultStatus();
            calculatePlayersEarnings(playersResultStatus);
        }

        long playersBetAmountSum = participants.getPlayers().stream()
                .mapToLong(Player::getEarnedMoney)
                .sum();
        participants.getDealer().setEarnedMoney(-1 * playersBetAmountSum);
    }

    private void calculatePushPlayersEarnings() {
        for (Player player : participants.getPlayers()) {
            calculatePushPlayerEarnings(player);
        }
    }

    private static void calculatePushPlayerEarnings(final Player player) {
        if (player.calculateMaxSum() == BLACKJACK_NUMBER) {
            player.draw();
        }
        if (player.calculateMaxSum() != BLACKJACK_NUMBER) {
            player.lose();
        }
    }

    private Map<Player, ResultStatus> makePlayersResultStatus() {
        Map<Player, ResultStatus> playersResultStatus = new HashMap<>();
        int dealerSum = participants.calculateDealerMaxSum();
        for (Player player : participants.getPlayers()) {
            int playerSum = player.calculateMaxSum();
            ResultStatus resultStatus = calculateResultStatus(playerSum, dealerSum);
            resultStatus = calculateIfBlackjack(player, resultStatus);
            playersResultStatus.put(player, resultStatus);
        }
        return playersResultStatus;
    }

    private static ResultStatus calculateIfBlackjack(final Player player, ResultStatus resultStatus) {
        if (resultStatus == ResultStatus.WIN && player.isBlackjack()) {
            resultStatus = ResultStatus.BLACKJACK;
        }
        return resultStatus;
    }

    private void calculatePlayersEarnings(final Map<Player, ResultStatus> playersResultStatus) {
        for (Entry<Player, ResultStatus> playerResultStatusEntry : playersResultStatus.entrySet()) {
            calculatePlayerEarnings(playerResultStatusEntry);
        }
    }

    private void calculatePlayerEarnings(final Entry<Player, ResultStatus> playerResultStatusEntry) {
        if (playerResultStatusEntry.getValue() == ResultStatus.WIN) {
            playerResultStatusEntry.getKey().win();
        }
        if (playerResultStatusEntry.getValue() == ResultStatus.LOSE) {
            playerResultStatusEntry.getKey().lose();
        }
        if (playerResultStatusEntry.getValue() == ResultStatus.DRAW) {
            playerResultStatusEntry.getKey().lose();
        }
        if (playerResultStatusEntry.getValue() == ResultStatus.BLACKJACK) {
            playerResultStatusEntry.getKey().blackjack();
        }
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

    public void bet(final Player player, final long betAmount) {
        player.bet(betAmount);
    }

    public boolean checkBlackjack() {
        int dealerSum = participants.calculateDealerMaxSum();
        Map<Player, Integer> playerSum = new HashMap<>();
        for (Player player : participants.getPlayers()) {
            playerSum.put(player, player.calculateMaxSum());
        }
        return (dealerSum == BLACKJACK_NUMBER && playerSum.containsValue(BLACKJACK_NUMBER));
    }
}
