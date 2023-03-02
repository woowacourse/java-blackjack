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
            InputView.readOneCard(player.getName());
        }
        OutputView.printDealerReceiveOneMoreCard();
        OutputView.printCardsWithSum(players.getPlayers(), dealer);

        OutputView.printFinalResult(players.getPlayers(), dealer.getResults());
    }
}
