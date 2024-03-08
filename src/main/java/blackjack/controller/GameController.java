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

        printParticipantsInitialHands(dealer, players);

        askDrawUntilConfirmHands(players, deck);

        confirmDealerHands(dealer, deck);
    }

    private static void printParticipantsInitialHands(Dealer dealer, Players players) {
        OutputView.printDealerFirstCard(dealer);
        for(Player player : players.getPlayers()){
            OutputView.printParticipantHands(player);
        }
    }

    private static void confirmDealerHands(Dealer dealer, Deck deck) {
        while (dealer.draw(deck)) {
            OutputView.printDealerDrawMessage(dealer);
        }
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
