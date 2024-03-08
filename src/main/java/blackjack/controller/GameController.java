package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class GameController {

    private GameController() {
    }

    public static void run() {
        OutputView.printAskNameMessage();
        Players players = new Players(InputView.readNames());
        Dealer dealer = new Dealer();
        Deck deck = Deck.createSuffledDeck();
        Game game = Game.of(deck, dealer, players);

        OutputView.printDrawInitialHandsMessage(dealer, players);
        OutputView.printParticipantsInitialHands(dealer, players);

        askDrawUntilConfirmHands(players, deck);
        confirmDealerHands(dealer, deck);

        OutputView.printFinalHandsAndScoreMessage(dealer, players);
        OutputView.printGameResult(dealer, game.makeGameResult());
    }

    private static void confirmDealerHands(Dealer dealer, Deck deck) {
        System.out.println();
        while (dealer.draw(deck)) {
            OutputView.printDealerDrawMessage(dealer);
        }
        System.out.println();
    }

    private static void askDrawUntilConfirmHands(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            askDrawToPlayer(player, deck);
        }
    }

    private static void askDrawToPlayer(Player player, Deck deck) {
        boolean isDraw = true;
        while (isDraw) {
            OutputView.printAskDrawMessage(player.getName());
            isDraw = player.draw(InputView::askDraw, deck);
            OutputView.printParticipantHands(player);
        }
    }
}
