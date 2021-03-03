package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    public static void play() {
        OutputView.printInputNames();
        BlackJackGame blackJackGame = new BlackJackGame(InputView.inputString(),
            Card.getAllCards());
        blackJackGame.prepare();
        OutputView.printGameInitializeMessage(blackJackGame.getDealer(), blackJackGame.getPlayers(),
            BlackJackGame.STARTING_CARD_COUNT);
        while (!blackJackGame.isPrepared()) {
            Player player = blackJackGame.nextPlayer();
            while (player.isContinue()) {
                OutputView.willDrawCard(player);
                player.willContinue(InputView.inputString(), blackJackGame.getDeck());
                OutputView.printParticipantStatus(player, false);
            }
        }

        while (blackJackGame.getDealer().isContinue()) {
            blackJackGame.getDealer().drawCard(blackJackGame.getDeck());
            OutputView.printDealerDrawCard(blackJackGame.getDealer());
        }

        OutputView.printParticipantsStatus(blackJackGame.getParticipants());
        OutputView.printResult(blackJackGame.getGameResult());
    }

    public static void main(String[] args) {
        BlackJackController.play();
    }

}
