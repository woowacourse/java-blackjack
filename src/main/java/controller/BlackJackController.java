package controller;

import domain.bet.BetMoney;
import domain.bet.BettingPool;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import view.InputView;
import view.OutputView;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BlackJackController {

    private static final String DELIMITER = ",";
    private static final String YES = "y";

    private final Dealer dealer = new Dealer();

    public void play() {
        try {
            Players players = enterPlayer();
            BettingPool bettingPool = betMoneyPlayers(players);

            distributeCards(players);

            playersTurn(players);
            dealerTurn();

            OutputView.printDealerExtraCardsCount(dealer.getName(), dealer.getExtraHandSize());
            OutputView.printEveryOneCardsNamesWithTotal(players, dealer);
            printResult(bettingPool);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
        }
    }

    private Players enterPlayer() {
        String playerNamesInput = InputView.getPlayerNamesInput();
        List<String> playerNames = Arrays.asList(playerNamesInput.split(DELIMITER));

        List<Player> players = playerNames.stream()
                .map(playerName -> new Player(playerName.trim()))
                .toList();
        return new Players(players);
    }

    private BettingPool betMoneyPlayers(final Players players) {
        Map<Player, BetMoney> bettingPool = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            bettingPool.put(player, new BetMoney(InputView.getBetAmountInput(player.getName())));
        }
        return new BettingPool(bettingPool);
    }

    private void distributeCards(final Players players) {
        dealer.shuffleDeck();
        drawTwoCardFromDeck(dealer);
        for (Player player : players.getPlayers()) {
            drawTwoCardFromDeck(player);
        }
        OutputView.printDistributeResult(players, dealer);
    }

    private void drawTwoCardFromDeck(final Participant participant) {
        participant.addCard(dealer.drawCard());
        participant.addCard(dealer.drawCard());
    }

    private void playersTurn(final Players players) {
        for (Player player : players.getPlayers()) {
            playerTurn(player);
        }
    }

    private void playerTurn(final Player player) { //player도 객체 안에 물어보기
        playTurn(player, () -> InputView.getYnInput(player).equalsIgnoreCase(YES));
    }

    private void dealerTurn() {
        playTurn(dealer, dealer::isBelowThreshold);
    }

    private void playTurn(final Participant participant, final Supplier<Boolean> shouldDrawMore) {
        if (participant.isMaxScore()) return;
        boolean isAlive = participant.resolveBust();
        while (isAlive && !participant.isMaxScore() && shouldDrawMore.get()) { //TODO: dealer는 isMaxScore()가 필요 없음
            isAlive = drawAdditionalCard(participant);
        }
        if (!isAlive) {
            handleBust(participant);
        }
    }

    private static void handleBust(final Participant participant) {
        participant.applyBustPenalty();
        OutputView.printBust();
    }

    private boolean drawAdditionalCard(final Participant participant) {
        participant.addCard(dealer.drawCard());
        if (participant.getClass().equals(Player.class)) {
            OutputView.printHandCardsNames(participant.getName(), participant.getHand());
        }
        return participant.resolveBust();
    }

    private void printResult(final BettingPool bettingPool) {
        Map<String,BetMoney> playerProfits =  bettingPool.computePlayersProfit(dealer);

        OutputView.printAllResult(playerProfits, dealer.getName());
    }
}
