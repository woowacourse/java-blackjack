package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.controller.dto.GamersResultResponse;
import blackjack.controller.dto.HandResponseDto;
import blackjack.domain.card.CardFactory;
import blackjack.domain.deck.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.rule.PlayerAnswer;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.console.ConsoleInputView;
import blackjack.view.console.ConsoleOutputView;

import java.util.List;

public class BlackJackApplication {

    public static void main(String[] args) {
        InputView consoleInputView = new ConsoleInputView();
        OutputView consoleOutputView = new ConsoleOutputView();
        BlackjackController blackjackController = new BlackjackController();

        Deck deck = new Deck(CardFactory.generate());
        Dealer dealer = new Dealer();
        Players players = blackjackController.createPlayers(consoleInputView.inputPlayerNames());

        List<HandResponseDto> initialHand = blackjackController.initializeHand(dealer, players, deck);
        consoleOutputView.printInitialCard(initialHand);

        for (Player player : players) {
            PlayerAnswer playerAnswer;
            HandResponseDto playerDto;
            do {
                playerAnswer = consoleInputView.inputPlayerAnswer(player.getName());
                playerDto = blackjackController.drawMoreCard(player, deck, playerAnswer);
                consoleOutputView.printHand(playerDto);
            } while (!player.isBusted() && playerAnswer.isYes());
        }

        while (dealer.shouldDrawCard()) {
            String result = blackjackController.drawMoreCard(dealer, deck);
            System.out.println(result);
        }

        List<HandResponseDto> result = blackjackController.getFinalHand(dealer, players);
        consoleOutputView.printHandWithScore(result);

        GamersResultResponse gamersResultResponse = blackjackController.getResult(dealer, players);
        consoleOutputView.printResult(gamersResultResponse);
    }
}
