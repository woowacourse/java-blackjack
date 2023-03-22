package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.RandomDeckGenerator;
import blackjack.domain.money.Money;
import blackjack.domain.result.CardResult;
import blackjack.domain.user.Name;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.ViewRenderer;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        final BlackjackGame blackJackGame = initBlackJackGame();
        betPlayers(blackJackGame);
        printFirstOpenCardGroups(blackJackGame);
        playPlayersTurn(blackJackGame);
        playDealerTurn(blackJackGame);
        printUserNameAndCardResults(blackJackGame);
        printUserNameAndProfits(blackJackGame);
    }

    private BlackjackGame initBlackJackGame() {
        try {
            final List<String> playerNames = inputView.readPlayerNames();
            return new BlackjackGame(playerNames, new RandomDeckGenerator());
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            return initBlackJackGame();
        }
    }

    private void betPlayers(final BlackjackGame blackJackGame) {
        for (final Name playerName : blackJackGame.getPlayerNames()) {
            betPlayerMoney(blackJackGame, playerName);
        }
    }

    private void betPlayerMoney(final BlackjackGame blackJackGame, final Name playerName) {
        try {
            final int bettingMoney = inputView.readBettingMoney(playerName.getValue());
            blackJackGame.betPlayer(playerName, bettingMoney);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            betPlayerMoney(blackJackGame, playerName);
        }
    }

    private void printFirstOpenCardGroups(final BlackjackGame blackJackGame) {
        final Map<Name, CardGroup> userNameAndFirstOpenCardGroups = blackJackGame.getUserNameAndFirstOpenCardGroups();
        final List<Name> userNames = userNameAndFirstOpenCardGroups.keySet()
                .stream()
                .collect(Collectors.toUnmodifiableList());
        outputView.printFirstCardGroupInfoMessage(ViewRenderer.renderNames(userNames));
        for (final Name userName : userNames) {
            final List<String> renderedUserNameAndCardGroup = ViewRenderer.renderCardGroup(
                    userNameAndFirstOpenCardGroups.get(userName));
            outputView.printUserNameAndCardGroup(userName.getValue(), renderedUserNameAndCardGroup);
        }
    }

    private void playPlayersTurn(final BlackjackGame blackJackGame) {
        final List<Name> playerNames = blackJackGame.getPlayerNames();
        for (final Name playerName : playerNames) {
            playPlayerTurn(blackJackGame, playerName);
        }
    }

    private void playPlayerTurn(final BlackjackGame blackJackGame, final Name playerName) {
        DrawOrStay drawOrStay = DrawOrStay.DRAW;
        while (drawOrStay.isDraw() && blackJackGame.isContinuous(playerName)) {
            drawOrStay = repeatUntilReadValidateDrawInput(playerName);
            blackJackGame.playPlayer(playerName, drawOrStay);
            final CardGroup playerCardGroup = blackJackGame.getCardGroupBy(playerName);
            outputView.printUserNameAndCardGroup(playerName.getValue(), ViewRenderer.renderCardGroup(playerCardGroup));
        }
    }

    private DrawOrStay repeatUntilReadValidateDrawInput(final Name playerName) {
        try {
            return DrawOrStay.from(inputView.readDrawOrStay(playerName.getValue()));
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            return repeatUntilReadValidateDrawInput(playerName);
        }
    }

    private void playDealerTurn(BlackjackGame blackJackGame) {
        int dealerDrawCount = blackJackGame.drawDealerUntilUnderLimit();

        while (dealerDrawCount-- > 0) {
            outputView.printDealerDrawInfoMessage();
        }
    }

    private void printUserNameAndProfits(final BlackjackGame blackJackGame) {
        final Map<Name, Money> playerNameAndProfits = blackJackGame.getPlayerNameAndProfits();
        final Money dealerProfit = blackJackGame.getDealerProfit();
        final Map<String, Integer> renderedUserNameAndProfit = ViewRenderer.renderUserNameAndProfit(
                playerNameAndProfits, dealerProfit);
        outputView.printUsersProfits(renderedUserNameAndProfit);
    }

    private void printUserNameAndCardResults(BlackjackGame blackJackGame) {
        final Map<Name, CardResult> userNameAndCardResults = blackJackGame.getUserNameAndCardResults();
        final Map<String, String> renderedUserNameAndCardResults = ViewRenderer
                .renderUserNameAndCardResults(userNameAndCardResults);
        outputView.printUserNameAndCardResults(renderedUserNameAndCardResults);
    }
}
