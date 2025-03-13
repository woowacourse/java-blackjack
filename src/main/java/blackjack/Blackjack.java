package blackjack;

import blackjack.card.Card;
import blackjack.gamer.Dealer;
import blackjack.gamer.Player;
import blackjack.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Blackjack {

    private static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_CARD_COUNT = 2;

    private final Dealer dealer;
    private final Players players;

    public Blackjack(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void initDealer() {
        dealer.initCardMachine();
    }

    public void makePlayers(final InputView inputView) {
        final String names = inputView.readNames();
        try {
            players.addPlayersFrom(names);
        } catch (IllegalArgumentException e) {
            inputView.printErrorMessage(e);
            makePlayers(inputView);
        }
    }

    public void betMoney(final InputView inputView) {
        for (Player player : players.getPlayers()) {
            receiveBettingMoney(inputView, player);
        }
    }

    public void deal() {
        final List<Card> dealerCards = dealer.spreadTwoCards();
        dealer.receiveCards(dealerCards);
        for (Player player : players.getPlayers()) {
            final List<Card> playerCards = dealer.spreadTwoCards();
            player.receiveCards(playerCards);
        }
    }

    public void showInitialCards(final ResultView resultView) {
        resultView.printEmptyLine();
        resultView.printCards(dealer, dealer.showInitialCards());
        for (Player player : players.getPlayers()) {
            resultView.printCards(player, player.showInitialCards());
        }
    }

    public boolean isPush() {

        if (!dealer.isBlackjack(BLACKJACK_SCORE, BLACKJACK_CARD_COUNT)) {
            return false;
        }
        return players.getPlayers().stream()
                .anyMatch(player -> player.isBlackjack(BLACKJACK_SCORE, BLACKJACK_CARD_COUNT));
    }

    public void hitOrStand(final InputView inputView, final ResultView resultView) {
        for (Player player : players.getPlayers()) {
            while (!player.isBust(BLACKJACK_SCORE)) {
                final boolean isHit = readHitOrStand(inputView, player);
                if (!isHit) {
                    break;
                }
                final Card card = dealer.spreadOneCard();
                player.receiveCard(card);
                resultView.printCards(player, player.showAllCards());
            }
        }
        while (dealer.isHit()) {
            final Card card = dealer.spreadOneCard();
            dealer.receiveCard(card);
            resultView.printDealerHit();
        }
    }

    public void showSum(final ResultView resultView) {
        resultView.printEmptyLine();
        resultView.printCardsSum(dealer, dealer.showAllCards(), dealer.sumCards());
        for (Player player : players.getPlayers()) {
            resultView.printCardsSum(player, player.showAllCards(), player.sumCards());
        }
    }

    public Map<Player, WinningStatus> calculateWinningResult() {
        Map<Player, WinningStatus> winningStatus = initWinningStatus();
        if (isPush()) {
            for (Player player : players.getPlayers()) {
                winningStatus.replace(player, WinningStatus.LOSE);
                if (player.isBlackjack(BLACKJACK_SCORE, BLACKJACK_CARD_COUNT)) {
                    winningStatus.replace(player, WinningStatus.PUSH);
                }
            }
            return winningStatus;
        }
        for (Player player : players.getPlayers()) {
            final WinningStatus playerWinningStatus = calculateWinningStatus(player, dealer);
            winningStatus.replace(player, playerWinningStatus);
        }
        return winningStatus;
    }

    public void calculateEarnedMoney(final Map<Player, WinningStatus> winningResult) {
        Map<WinningStatus, Consumer<Player>> actionMap = Map.of(
                WinningStatus.WIN, Player::win,
                WinningStatus.DRAW, Player::draw,
                WinningStatus.LOSE, Player::lose,
                WinningStatus.BLACKJACK, Player::blackjack,
                WinningStatus.PUSH, Player::push
        );

        for (Player player : players.getPlayers()) {
            final WinningStatus winningStatus = winningResult.get(player);
            Consumer<Player> action = actionMap.get(winningStatus);
            action.accept(player);
        }
    }

    private void receiveBettingMoney(final InputView inputView, final Player player) {
        try {
            player.bet(inputView.readBettingMoney(player));
        } catch (IllegalArgumentException e) {
            inputView.printErrorMessage(e);
            receiveBettingMoney(inputView, player);
        }
    }

    private boolean readHitOrStand(final InputView inputView, final Player player) {
        try {
            final String answer = inputView.readHitOrStand(player);
            final UserAnswer userAnswer = UserAnswer.of(answer);
            return userAnswer.isYes();
        } catch (IllegalArgumentException e) {
            inputView.printErrorMessage(e);
        }
        return readHitOrStand(inputView, player);
    }

    private Map<Player, WinningStatus> initWinningStatus() {
        Map<Player, WinningStatus> winningStatus = new HashMap<>();
        for (Player player : players.getPlayers()) {
            winningStatus.put(player, WinningStatus.UNDEFINED);
        }
        return winningStatus;
    }

    private WinningStatus calculateWinningStatus(final Player player, final Dealer dealer) {
        if (player.isBlackjack(BLACKJACK_SCORE, BLACKJACK_CARD_COUNT)) {
            return WinningStatus.BLACKJACK;
        }
        final int dealerSum = dealer.sumCards();
        final int playerSum = player.sumCards();
        if (playerSum > BLACKJACK_SCORE) {
            return WinningStatus.LOSE;
        }
        if (dealerSum > BLACKJACK_SCORE) {
            return WinningStatus.WIN;
        }
        if (playerSum > dealerSum) {
            return WinningStatus.WIN;
        }
        if (playerSum == dealerSum) {
            return WinningStatus.DRAW;
        }
        return WinningStatus.LOSE;
    }

}
