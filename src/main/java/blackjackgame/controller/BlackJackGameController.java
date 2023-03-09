package blackjackgame.controller;

import blackjackgame.domain.DrawCommand;
import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.Cards;
import blackjackgame.domain.card.ShuffledCardsGenerator;
import blackjackgame.domain.game.BlackJackGame;
import blackjackgame.domain.game.Result;
import blackjackgame.domain.user.Dealer;
import blackjackgame.domain.user.Player;
import blackjackgame.domain.user.Players;
import blackjackgame.domain.user.User;
import blackjackgame.domain.user.dto.NameDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import blackjackgame.view.InputView;
import blackjackgame.view.OutputView;

public class BlackJackGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackJackGame blackJackGame = generateBlackJackGame();
        blackJackGame.drawDefaultCard();
        outputView.printSetUpResult(blackJackGame.getSetUpResult());
        progressPlayersTurn(blackJackGame);
        progressDealerTurn(blackJackGame);
        printUsersCardResult(blackJackGame);
        blackJackGame.judgeWinner();
        printFinalResult(blackJackGame);
    }

    private BlackJackGame generateBlackJackGame() {
        Cards cards = new Cards(new ShuffledCardsGenerator());
        Dealer dealer = new Dealer();
        Players players = repeatForValidInput(this::setUpPlayers);
        return new BlackJackGame(players, dealer, cards);
    }

    private <T> T repeatForValidInput(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return repeatForValidInput(supplier);
        }
    }

    private Players setUpPlayers() {
        return new Players(readUsersName());
    }

    private List<String> readUsersName() {
        outputView.printInputPlayerNamesMessage();
        return inputView.readPlayerNames();
    }

    private void progressPlayersTurn(BlackJackGame blackJackGame) {
        List<Player> players = blackJackGame.getPlayers();
        for (Player player : players) {
            progressPlayerTurn(blackJackGame, player);
        }
    }

    private void progressPlayerTurn(BlackJackGame blackJackGame, Player player) {
        while (player.isLessThanBustScore() && DrawCommand.DRAW == repeatForValidInput(() -> readDrawCommand(player))) {
            blackJackGame.drawOneMoreCard(player);
            printDrawResult(player);
        }
        if (player.isLessThanBustScore()) {
            printDrawResult(player);
        }
    }

    private DrawCommand readDrawCommand(Player player) {
        outputView.printAskOneMoreCardMessage(player.getName());
        return inputView.readDrawCommand();
    }

    private void printDrawResult(Player player) {
        outputView.printPlayerDrawResult(player.getName(), player.getCards());
    }

    private void progressDealerTurn(BlackJackGame blackJackGame) {
        blackJackGame.drawDealerCardUntilSatisfyingMinimumScore();

        int dealerDrawCount = blackJackGame.getDealerExtraDrawCount();
        if (dealerDrawCount > 0) {
            outputView.printDealerDrawResult(dealerDrawCount);
        }
    }

    private void printUsersCardResult(BlackJackGame blackJackGame) {
        Map<User, List<Card>> userResult = new LinkedHashMap<>();
        userResult.put(blackJackGame.getDealer(), blackJackGame.getDealer().getCards());
        blackJackGame.getPlayers().forEach(player -> userResult.put(player, player.getCards()));
        outputView.printUsersCardResult(userResult);
    }

    private void printFinalResult(BlackJackGame blackJackGame) {
        Map<Result, Integer> dealerFinalResult = blackJackGame.getDealerFinalResult();
        Map<NameDto, Result> playerFinalResult = blackJackGame.getPlayerFinalResult();
        outputView.printFinalResult(dealerFinalResult, playerFinalResult);
    }
}
