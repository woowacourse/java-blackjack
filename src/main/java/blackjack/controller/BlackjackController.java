package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.DrawOrStay;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.RandomDeckGenerator;
import blackjack.domain.result.CardResult;
import blackjack.domain.result.UserNameProfits;
import blackjack.domain.user.Name;
import blackjack.domain.user.PlayerName;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.ViewRenderer;
import java.util.List;
import java.util.Map;

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
        for (final PlayerName playerName : blackJackGame.getPlayerNames()) {
            betPlayerMoney(blackJackGame, playerName);
        }
    }

    private void betPlayerMoney(final BlackjackGame blackJackGame, final PlayerName playerName) {
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
        final List<PlayerName> playerNames = blackJackGame.getPlayerNames();
        outputView.printFirstCardGroupInfoMessage(ViewRenderer.renderNames(playerNames));
        for (final Name userName : userNameAndFirstOpenCardGroups.keySet()) {
            final List<String> renderedUserNameAndCardGroup = ViewRenderer.renderCardGroup(
                    userNameAndFirstOpenCardGroups.get(userName));
            outputView.printUserNameAndCardGroup(userName.getValue(), renderedUserNameAndCardGroup);
        }
    }

    private void playPlayersTurn(final BlackjackGame blackJackGame) {
        final List<PlayerName> playerNames = blackJackGame.getPlayerNames();
        for (final PlayerName playerName : playerNames) {
            playPlayerTurn(blackJackGame, playerName);
        }
    }

    private void playPlayerTurn(final BlackjackGame blackJackGame, final PlayerName playerName) {
        boolean isPlay;
        do {
            DrawOrStay drawOrStay = repeatUntilReadValidateDrawInput(playerName);
            isPlay = blackJackGame.playPlayer(playerName, drawOrStay);
            final CardGroup playerCardGroup = blackJackGame.getCardGroupBy(playerName);
            outputView.printUserNameAndCardGroup(playerName.getValue(),
                    ViewRenderer.renderCardGroup(playerCardGroup));
        }
        while (isPlay && blackJackGame.isContinuous(playerName));
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

    private void printUserNameAndCardResults(BlackjackGame blackJackGame) {
        final Map<Name, CardResult> userNameAndCardResults = blackJackGame.getUserNameAndCardResults();
        final Map<String, String> renderedUserNameAndCardResults = ViewRenderer
                .renderUserNameAndCardResults(userNameAndCardResults);
        outputView.printUserNameAndCardResults(renderedUserNameAndCardResults);
    }

    private void printUserNameAndProfits(final BlackjackGame blackJackGame) {
        final UserNameProfits userNameAndProfits = blackJackGame.getUserNameAndProfits();
        final Map<String, Integer> renderedUserNameAndProfit = ViewRenderer.renderUserNameAndProfits(
                userNameAndProfits.getUserNameProfitMapper());
        outputView.printUsersProfits(renderedUserNameAndProfit);
    }
}
