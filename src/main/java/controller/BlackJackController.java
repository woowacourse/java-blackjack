package controller;

import domain.BlackJack;
import domain.Money;
import domain.participant.Player;
import java.util.Map;
import java.util.Set;

import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        try {
            Map<Player, Money> players = inputView.readPlayers();
            BlackJack blackJack = BlackJack.init(players);
            runGameWith(blackJack);
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e);
        }
    }

    private void runGameWith(BlackJack blackJack) {
        blackJack.handoutCards();
        outputView.printHandoutCards(blackJack.getShowDealerCards(), blackJack.getPlayers());
        runBlackjack(blackJack);
    }

    private void runBlackjack(BlackJack blackJack) {
        askNewCardToAllPlayers(blackJack);
        setupDealerCards(blackJack);
        showCardsResult(blackJack);
        showRevenues(blackJack);
    }

    private void askNewCardToAllPlayers(BlackJack blackJack) {
        Set<Player> players = blackJack.getPlayers();
        for (Player player : players) {
            askNewCardToPlayer(blackJack, player);
        }
    }

    private void askNewCardToPlayer(BlackJack blackJack, Player player) {
        while (blackJack.canDraw(player) && inputView.readYesOrNo(player).isYes()) {
            blackJack.giveOneCardTo(player);
            outputView.printPlayerCards(player);
        }
    }

    private void setupDealerCards(BlackJack blackJack) {
        final int count = blackJack.getDealerDrawnCount();
        if (count > 0) {
            outputView.printDealerDrawCount(count);
        }
    }

    private void showCardsResult(BlackJack blackJack) {
        outputView.printDealerCardsAndResult(blackJack.getDealerCards(), blackJack.getDealerScore());
        for (Player player : blackJack.getPlayers()) {
            outputView.printCardsAndResult(player.getName(), player.getCards(), player.getScore().value());
        }
    }

    private void showRevenues(BlackJack blackJack) {
        final int dealerRevenue = blackJack.getDealerRevenue();
        Map<Player, Integer> playerRevenues = blackJack.getPlayerRevenues();
        outputView.printRevenueResult(dealerRevenue, playerRevenues);
    }
}
