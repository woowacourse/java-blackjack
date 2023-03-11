package blackjackgame.controller;

import blackjackgame.domain.DrawCommand;
import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.Cards;
import blackjackgame.domain.card.ShuffledCardsGenerator;
import blackjackgame.domain.game.BlackJackGame;
import blackjackgame.domain.user.Bet;
import blackjackgame.domain.user.Dealer;
import blackjackgame.domain.user.Name;
import blackjackgame.domain.user.Names;
import blackjackgame.domain.user.Player;
import blackjackgame.domain.user.Players;
import blackjackgame.domain.user.User;
import java.util.ArrayList;
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
        outputView.printFinalResult(blackJackGame.getBetResult());
    }

    private BlackJackGame generateBlackJackGame() {
        Cards cards = new Cards(new ShuffledCardsGenerator());
        Dealer dealer = new Dealer();
        Players players = setUpPlayers();
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
        Names playerNames = repeatForValidInput(this::setUpPlayerNames);
        List<Bet> playerBets = setUpPlayerBets(playerNames);
        return new Players(playerNames, playerBets);
    }

    private Names setUpPlayerNames() {
        outputView.printInputPlayerNamesMessage();
        List<String> playerNames = inputView.readPlayerNames();
        return new Names(playerNames);
    }

    private List<Bet> setUpPlayerBets(Names playerNames) {
        List<Bet> playerBetAmounts = new ArrayList<>();
        for (Name playerName : playerNames.getNames()) {
            playerBetAmounts.add(repeatForValidInput(() -> readPlayerBetAmount(playerName)));
        }
        return playerBetAmounts;
    }

    private Bet readPlayerBetAmount(Name playerName) {
        outputView.printInputPlayerBetAmountMessage(playerName.getName());
        return new Bet(inputView.readPlayerBetAmount());
    }

    private void progressPlayersTurn(BlackJackGame blackJackGame) {
        List<Player> players = blackJackGame.getPlayers();
        for (Player player : players) {
            progressPlayerTurn(blackJackGame, player);
        }
    }

    private void progressPlayerTurn(BlackJackGame blackJackGame, Player player) {
        while (player.isLessThanBustScore() && isHitCommandEntered(player)) {
            blackJackGame.hit(player);
            printDrawResult(player);
        }
        if (player.isLessThanBustScore()) {
            printDrawResult(player);
        }
    }

    private boolean isHitCommandEntered(Player player) {
        return DrawCommand.HIT == repeatForValidInput(() -> readDrawCommand(player));
    }

    private DrawCommand readDrawCommand(Player player) {
        outputView.printAskOneMoreCardMessage(player.getName());
        return inputView.readDrawCommand();
    }

    private void printDrawResult(Player player) {
        outputView.printPlayerDrawResult(player.getName(), player.cards());
    }

    private void progressDealerTurn(BlackJackGame blackJackGame) {
        blackJackGame.hitUntilSatisfyingDealerMinimumScore();

        int dealerDrawCount = blackJackGame.getDealerExtraDrawCount();
        if (dealerDrawCount > 0) {
            outputView.printDealerDrawResult(dealerDrawCount);
        }
    }

    private void printUsersCardResult(BlackJackGame blackJackGame) {
        Map<User, List<Card>> userResult = new LinkedHashMap<>();
        userResult.put(blackJackGame.getDealer(), blackJackGame.getDealer().cards());
        blackJackGame.getPlayers().forEach(player -> userResult.put(player, player.cards()));
        outputView.printUsersCardResult(userResult);
    }
}
