package controller;

import domain.BlackJack;
import domain.GameResult;
import domain.Nickname;
import domain.participant.Player;
import java.util.List;
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
            List<String> names = inputView.readPlayerNames();
            List<Nickname> nicknames = names.stream().map(Nickname::new).toList();
            BlackJack blackJack = BlackJack.init(nicknames);
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
        showGameResult(blackJack);
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
            outputView.printCardsAndResult(player.getName(), player.getCards(), player.getCardScore());
        }
    }

    private void showGameResult(BlackJack blackJack) {
        Map<Player, GameResult> playerResult = blackJack.getPlayersResult();
        Map<GameResult, Integer> dealerResult = blackJack.getDealerResult();
        outputView.printResult(dealerResult, playerResult);
    }
}
