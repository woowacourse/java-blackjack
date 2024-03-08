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

        // 초기패 분배
        OutputView.printDrawInitialHandsMessage(dealer, players);
        printParticipantsInitialHands(dealer, players);

        //참여자들의 패 확정
        askDrawUntilConfirmHands(players, deck);
        confirmDealerHands(dealer, deck);

        //최종패 출력
        OutputView.printFinalHandsAndScoreMessage(dealer, players);
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
