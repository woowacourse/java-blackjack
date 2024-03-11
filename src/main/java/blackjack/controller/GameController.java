package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class GameController {

    private GameController() {
    }

    // TODO 함수형 인터페이스를 적극적으로 사용해서 도메인으로 끌어내려보기
    // TODO 필요한 정보만 추출하여 넘겨보기

    public static void run() {
        Deck deck = Deck.createShuffledDeck();
        Game game = makeGame(deck);
        Dealer gameDealer = game.getDealer();
        Players gamePlayers = game.getPlayers();

        printInitialHands(gameDealer.getName(), gameDealer.getFirstCardName(), gamePlayers.getPlayers());
        // TODO game을 활용하는 코드로 리팩터해보기
        confirmParticipantsHands(gamePlayers, deck, gameDealer);

        OutputView.printFinalHandsAndScoreMessage(gameDealer, gamePlayers);
        OutputView.printGameResult(gameDealer.getName(), game.makeGameResult());
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

    private static void printInitialHands(String dealerName, String dealerFirstCardName, List<Player> players) {
        OutputView.printDrawInitialHandsMessage(dealerName, players);
        OutputView.printParticipantsInitialHands(dealerName, dealerFirstCardName, players);
    }

    private static void confirmDealerHands(Dealer dealer, Deck deck) {
        while (dealer.draw(deck) == PlayerState.RUNNING) {
            OutputView.printDealerDrawMessage(dealer);
        }
    }

    private static void askDrawUntilConfirmHands(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            askDrawToPlayer(player, deck);
        }
    }

    private static void askDrawToPlayer(Player player, Deck deck) {
        PlayerState playerState = PlayerState.RUNNING;
        while (playerState == PlayerState.RUNNING) {
            OutputView.printAskDrawMessage(player.getName());
            playerState = player.draw(InputView::askDraw, deck);
            OutputView.printParticipantHands(player.getName(), player.getHandsCards());
        }
    }
}
