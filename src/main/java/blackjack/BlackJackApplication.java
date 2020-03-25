package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.controller.dto.request.BettingDto;
import blackjack.controller.dto.response.GamersResultResponseDto;
import blackjack.controller.dto.response.HandResponseDto;
import blackjack.controller.dto.response.HandResponseDtos;
import blackjack.domain.card.CardFactory;
import blackjack.domain.deck.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.rule.BettingTable;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.console.ConsoleInputView;
import blackjack.view.console.ConsoleOutputView;

public class BlackJackApplication {
    public static void main(String[] args) {
        final InputView consoleInputView = new ConsoleInputView();
        final OutputView consoleOutputView = new ConsoleOutputView();
        BlackjackController blackjackController = new BlackjackController(consoleInputView, consoleOutputView);

        final Dealer dealer = new Dealer();
        final Deck deck = new Deck(CardFactory.generate());
        blackjackController.run(dealer, deck);
    }
}
