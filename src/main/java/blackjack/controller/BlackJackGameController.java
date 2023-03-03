package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.card.CardMachine;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackGameController {

    public void run() {
        BlackJackGame blackJackGame = generateBlackJackGame();
        CardMachine cardMachine = new CardMachine();

        blackJackGame.handOutInitCards(cardMachine);
        Dealer dealer = blackJackGame.getDealer();
        Players players = blackJackGame.getPlayers();

        OutputView.printInitCard(players.getPlayers(), dealer.getFirstCard());

        for (Player player : players.getPlayers()) {
            String playerAnswer;

            do {
                playerAnswer = inputGameCommandToGetOneMoreCard(player);
                if (playerAnswer.equals("y")) {
                    blackJackGame.handOutCardTo(cardMachine, player);
                    OutputView.printParticipantCards(player.getName(), player.getCards());
                }
                if (playerAnswer.equals("n")) {
                    OutputView.printParticipantCards(player.getName(), player.getCards());
                    break;
                }
            } while(player.isUnderThanBoundary(21));
        }

        while (dealer.isUnderThanBoundary(16)) {
            blackJackGame.handOutCardTo(cardMachine, dealer);
            OutputView.printDealerReceiveOneMoreCard();
        }

        blackJackGame.findWinner();
        OutputView.printCardsWithSum(players.getPlayers(), dealer);
        OutputView.printFinalResult(players.getPlayers(), dealer.getResults());
    }

    private BlackJackGame generateBlackJackGame() {
        try {
            String inputNames = InputView.readNames();
            return new BlackJackGame(inputNames);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return generateBlackJackGame();
        }
    }

    private String inputGameCommandToGetOneMoreCard(final Player player) {
        try {
            String gameCommand = InputView.readGameCommandToGetOneMoreCard(player.getName());
            validateCorrectCommand(gameCommand);
            return gameCommand;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputGameCommandToGetOneMoreCard(player);
        }
    }

    private static void validateCorrectCommand(final String gameCommand) {
        if (!(gameCommand.equals("y") || gameCommand.equals("n"))) {
            throw new IllegalArgumentException("y 또는 n만 입력 가능합니다.");
        }
    }
}
