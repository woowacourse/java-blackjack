package controller;

import domain.BlackJack;
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

public class BlackJackController {

    private static final String DELIMITER = ",";
    private static final String YES = "y";

    private final Deck deck = new Deck();

    public void play() {
        try {
            Players players = enterPlayer();
            BettingPool bettingPool = betMoneyPlayers(players);
            BlackJack blackJack = new BlackJack(players, deck);

            prepareBlackJack(blackJack, players);
            playBlackJack(blackJack, players);

            printResult(players, blackJack.getDealer(), bettingPool);
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

    private void prepareBlackJack(final BlackJack blackJack, final Players players) {
        blackJack.prepareGame();
        OutputView.printDistributeResult(players, blackJack.getDealer());
    }

    private void playBlackJack(final BlackJack blackJack, final Players players) {
        playersTurn(blackJack, players);
        playTurn(blackJack, blackJack.getDealer());
    }

    private void playersTurn(final BlackJack blackJack, final Players players) {
        for (Player player : players.getPlayers()) {
            playTurn(blackJack, player);
        }
    }

    private void playTurn(final BlackJack blackJack, final Participant participant) {
        if (participant.isMaxScore() || participant.isHandBust()) return;

        boolean isAlive = participant.resolveBust();
        while (isAlive && participant.shouldDrawMore() && (!participant.isPlayer() || InputView.getYnInput(participant).equalsIgnoreCase(YES))) {
            isAlive = drawAdditionalCard(blackJack, participant);
        }
        if (!isAlive) {
            handleBust(blackJack, participant);
        }
    }

    private boolean drawAdditionalCard(final BlackJack blackJack, final Participant participant) {
        blackJack.passAdditionalCard(participant);
        if (participant.isPlayer()) {
            OutputView.printHandCardsNames(participant.getName(), participant.getHand());
        }
        return participant.resolveBust();
    }

    private static void handleBust(final BlackJack blackJack, final Participant participant) {
        blackJack.handleParticipantBust(participant);

        if(participant.isPlayer()) {
            OutputView.printBust();
        }
    }

    private void printResult(final Players players, final Dealer dealer, final BettingPool bettingPool) {
        OutputView.printDealerExtraCardsCount(dealer.getName(), dealer.getExtraHandSize());
        OutputView.printEveryOneCardsNamesWithTotal(players, dealer);

        Map<String, Profit> playerProfits =  bettingPool.computePlayersProfit(dealer);

        OutputView.printAllResult(playerProfits, dealer.getName());
    }
}
