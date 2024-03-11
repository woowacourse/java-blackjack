package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class GameController {

    private GameController() {
    }

    // TODO 함수형 인터페이스를 적극적으로 사용해서 도메인으로 끌어내려보기
    // TODO 필요한 정보만 추출하여 넘겨보기

    public static void run() {
        // TODO suffle -> shuffle
        Deck deck = Deck.createSuffledDeck();
        Game game = makeGame(deck);
        Dealer gameDealer = game.getDealer();
        Players gamePlayers = game.getPlayers();

        printInitialHands(gameDealer, gamePlayers);
        // TODO game을 활용하는 코드로 리팩터해보기
        confirmParticipantsHands(gamePlayers, deck, gameDealer);

        OutputView.printFinalHandsAndScoreMessage(gameDealer, gamePlayers);
        OutputView.printGameResult(gameDealer, game.makeGameResult());
    }

    private static Game makeGame(Deck deck) {
        OutputView.printAskNameMessage();
        Players players = new Players(InputView.readNames());
        Dealer dealer = new Dealer();
        return Game.of(deck, dealer, players);
    }

    private static void confirmParticipantsHands(Players players, Deck deck, Dealer dealer) {
        askDrawUntilConfirmHands(players, deck);
        confirmDealerHands(dealer, deck);
    }

    private static void printInitialHands(Dealer dealer, Players players) {
        OutputView.printDrawInitialHandsMessage(dealer, players);
        OutputView.printParticipantsInitialHands(dealer, players);
    }

    private static void confirmDealerHands(Dealer dealer, Deck deck) {
        System.out.println(); // TODO 뷰로직의 책임으로 옮기기
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
