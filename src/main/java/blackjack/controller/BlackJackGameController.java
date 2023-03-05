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

    private final ShufflingMachine shufflingMachine;

    public BlackJackGameController(final ShufflingMachine shufflingMachine) {
        this.shufflingMachine = shufflingMachine;
    }

    public void run() {
        final BlackJackGame blackJackGame = generateBlackJackGame();

        blackJackGame.handOutCards(shufflingMachine);
        final Dealer dealer = blackJackGame.getDealer();
        final Players players = blackJackGame.getPlayers();

        OutputView.printInitCard(players.getPlayers(), dealer.getFirstCard());

        handOutCardToDealer(blackJackGame, dealer);
        handOutCardToPlayers(blackJackGame, players);

        blackJackGame.findWinner();
        OutputView.printCardsWithSum(players.getPlayers(), dealer);
        OutputView.printFinalResult(players.getPlayers(), dealer.getResults());
    }

    private void handOutCardToDealer(final BlackJackGame blackJackGame, final Dealer dealer) {
        while (dealer.isUnderThanBoundary(DEALER_DRAWING_BOUNDARY)) {
            blackJackGame.handOutCardTo(shufflingMachine, dealer);
            OutputView.printDealerReceiveOneMoreCard();
        }
    }

    private void handOutCardToPlayers(final BlackJackGame blackJackGame, final Players players) {
        for (final Player player : players.getPlayers()) {
            handOutCardToEachPlayer(blackJackGame, player);
        }
    }

    private void handOutCardToEachPlayer(final BlackJackGame blackJackGame, final Player player) {
        String playerAnswer = inputGameCommandToGetOneMoreCard(player);
        while (player.isUnderThanBoundary(PLAYER_BUST_BOUNDARY) &&
                handOutCardByCommand(blackJackGame, player, playerAnswer)) {
            playerAnswer = inputGameCommandToGetOneMoreCard(player);
        }
    }

    private boolean handOutCardByCommand(final BlackJackGame blackJackGame, final Player player,
                                         final String playerAnswer) {
        if (playerAnswer.equals(YES_COMMAND)) {
            blackJackGame.handOutCardTo(shufflingMachine, player);
            OutputView.printParticipantCards(player.getName(), player.getCards());
        }
        if (playerAnswer.equals(NO_COMMAND)) {
            OutputView.printParticipantCards(player.getName(), player.getCards());
            return true;
        }
        return false;
    }

    private BlackJackGame generateBlackJackGame() {
        try {
            final String inputNames = InputView.readNames();
            return new BlackJackGame(new Dealer(), inputNames);
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

    private void validateCorrectCommand(final String gameCommand) {
        if (!(YES_COMMAND.equals(gameCommand) || NO_COMMAND.equals(gameCommand))) {
            throw new IllegalArgumentException("y 또는 n만 입력 가능합니다.");
        }
    }
}
