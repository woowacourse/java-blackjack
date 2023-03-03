package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.card.ShufflingMachine;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackGameController {

    private static final String YES_COMMAND = "y";
    private static final String NO_COMMAND = "n";
    private static final int DEALER_DRAWING_BOUNDARY = 16;
    private static final int PLAYER_BUST_BOUNDARY = 21;

    public void run() {
        final BlackJackGame blackJackGame = generateBlackJackGame();
        final ShufflingMachine shufflingMachine = new ShufflingMachine();

        blackJackGame.handOutInitCards(shufflingMachine);
        final Dealer dealer = blackJackGame.getDealer();
        final Players players = blackJackGame.getPlayers();

        OutputView.printInitCard(players.getPlayers(), dealer.getFirstCard());

        for (final Player player : players.getPlayers()) {
            String playerAnswer;

            do {
                playerAnswer = inputGameCommandToGetOneMoreCard(player);
                if (playerAnswer.equals(YES_COMMAND)) {
                    blackJackGame.handOutCardTo(shufflingMachine, player);
                    OutputView.printParticipantCards(player.getName(), player.getCards());
                }
                if (playerAnswer.equals(NO_COMMAND)) {
                    OutputView.printParticipantCards(player.getName(), player.getCards());
                    break;
                }
            } while (player.isUnderThanBoundary(PLAYER_BUST_BOUNDARY));
        }

        while (dealer.isUnderThanBoundary(DEALER_DRAWING_BOUNDARY)) {
            blackJackGame.handOutCardTo(shufflingMachine, dealer);
            OutputView.printDealerReceiveOneMoreCard();
        }

        blackJackGame.findWinner();
        OutputView.printCardsWithSum(players.getPlayers(), dealer);
        OutputView.printFinalResult(players.getPlayers(), dealer.getResults());
    }

    private BlackJackGame generateBlackJackGame() {
        try {
            final String inputNames = InputView.readNames();
            return new BlackJackGame(inputNames);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return generateBlackJackGame();
        }
    }

    private String inputGameCommandToGetOneMoreCard(final Player player) {
        try {
            final String gameCommand = InputView.readGameCommandToGetOneMoreCard(player.getName());
            validateCorrectCommand(gameCommand);
            return gameCommand;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputGameCommandToGetOneMoreCard(player);
        }
    }

    private static void validateCorrectCommand(final String gameCommand) {
        if (!(gameCommand.equals(YES_COMMAND) || gameCommand.equals(NO_COMMAND))) {
            throw new IllegalArgumentException("y 또는 n만 입력 가능합니다.");
        }
    }
}
