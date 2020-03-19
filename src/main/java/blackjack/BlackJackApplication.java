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
    private static final InputView consoleInputView = new ConsoleInputView();
    private static final OutputView consoleOutputView = new ConsoleOutputView();

    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController();

        Dealer dealer = new Dealer();
        Deck deck = new Deck(CardFactory.generate());
        Players players = blackjackController.createPlayers(consoleInputView.askPlayerNames());

        BettingDto bettingDto = consoleInputView.askBettingMoney(players.getNames());
        BettingTable bettingTable = blackjackController.createBettingTable(players, bettingDto);

        blackjackController.initializeHand(dealer, players, deck);
        HandResponseDtos handResponseDtos = blackjackController.getInitialHand(dealer, players);
        consoleOutputView.printInitialHand(handResponseDtos);

        runPlayersHitOrStay(deck, players);
        runDealerDrawMoreCard(dealer, deck);

        HandResponseDtos result = blackjackController.getFinalHand(dealer, players);
        consoleOutputView.printHandWithScore(result);

        GamersResultResponseDto gamersResultResponseDto = blackjackController.getGamersResultResponse(dealer, players, bettingTable);
        consoleOutputView.printResult(gamersResultResponseDto);
    }

    private static void runPlayersHitOrStay(Deck deck, Players players) {
        for (Player player : players) {
            runOnePlayerHitOrStay(deck, player);
        }
    }

    private static void runOnePlayerHitOrStay(Deck deck, Player player) {
        String name = player.getName();
        while (!player.isBusted() && consoleInputView.askPlayerAnswer(name).isYes()) {
            player.draw(deck.pick());
            HandResponseDto handResponseDto = HandResponseDto.of(player);
            consoleOutputView.printHand(handResponseDto);
        }
    }

    private static void runDealerDrawMoreCard(Dealer dealer, Deck deck) {
        while (dealer.shouldDrawCard()) {
            dealer.draw(deck.pick());
            consoleOutputView.printDealerDrawCard();
        }
    }

}
