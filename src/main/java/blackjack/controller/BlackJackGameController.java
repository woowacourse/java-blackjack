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
        String inputNames = InputView.readNames();
        BlackJackGame blackJackGame = new BlackJackGame(inputNames);

        CardMachine cardMachine = new CardMachine();

        blackJackGame.handOutInitCards(cardMachine);
        Dealer dealer = blackJackGame.getDealer();
        Players players = blackJackGame.getPlayers();

        OutputView.printInitCard(players.getPlayers(), dealer.getCards());
        for (Player player : players.getPlayers()) {
            String playerAnswer;
            do {
                playerAnswer = InputView.readOneCard(player.getName());
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
}
