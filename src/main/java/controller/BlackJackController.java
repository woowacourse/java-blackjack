package controller;

import domain.bet.BetMoney;
import domain.bet.BettingPool;
import domain.bet.Profit;
import domain.card.Deck;
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
    private static final int THRESHOLD = 16;

    private final Dealer dealer = new Dealer();
    private final Deck deck = new Deck();

    public void play() {
        try {
            Players players = enterPlayer();
            BettingPool bettingPool = betMoneyPlayers(players);
            distributeCards(players);
            playersTurn(players);
            dealerTurn();

            printResult(players, bettingPool);
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
        deck.shuffle();

        drawTwoCardFromDeck(dealer);
        for (Player player : players.getPlayers()) {
            drawTwoCardFromDeck(player);
        }
        OutputView.printDistributeResult(players, dealer);
    }

    private void drawTwoCardFromDeck(final Participant participant) {
        participant.addCard(deck.draw());
        participant.addCard(deck.draw());
    }

    private void playersTurn(final Players players) {
        for (Player player : players.getPlayers()) {
            playerTurn(player);
        }
    }

    private void playerTurn(final Player player) {
        playTurn(player, () -> !player.isMaxScore() && InputView.getYnInput(player).equalsIgnoreCase(YES));
    }

    private void dealerTurn() {
        playTurn(dealer, () -> dealer.getHandTotal() <= THRESHOLD);
    }

    private void playTurn(final Participant participant, final Supplier<Boolean> shouldDrawMore) {
        if (participant.isMaxScore()) return;

        boolean isAlive = participant.resolveBust();
        while (isAlive && shouldDrawMore.get()) {
            isAlive = drawAdditionalCard(participant);
        }
        if (!isAlive) {
            handleBust(participant);
        }
    }

    private boolean drawAdditionalCard(final Participant participant) {
        participant.addCard(deck.draw());
        if (participant.isPlayer()) {
            OutputView.printHandCardsNames(participant.getName(), participant.getHand());
        }
        return participant.resolveBust();
    }

    private static void handleBust(final Participant participant) {
        participant.applyBustPenalty();

        if(participant.isPlayer()) {
            OutputView.printBust();
        }
    }

    private void printResult(final Players players, final BettingPool bettingPool) {
        OutputView.printDealerExtraCardsCount(dealer.getName(), dealer.getExtraHandSize());
        OutputView.printEveryOneCardsNamesWithTotal(players, dealer);

        Map<String, Profit> playerProfits =  bettingPool.computePlayersProfit(dealer);

        OutputView.printAllResult(playerProfits, dealer.getName());
    }
}
