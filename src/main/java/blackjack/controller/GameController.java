package blackjack.controller;

import blackjack.domain.card.Card;
import blackjack.domain.game.Deck;
import blackjack.domain.game.Game;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class GameController {

    private GameController() {
    }

    public static void run() {
        Game game = makeGame();
        assert game != null;
        Dealer gameDealer = game.getDealer();
        Players gamePlayers = game.getPlayers();
        Deck deck = game.getDeck();

        printInitialHands(gameDealer.getName(), gameDealer.getFirstCard(), gamePlayers.getPlayers());
        confirmParticipantsHands(gamePlayers, deck, gameDealer);

        OutputView.printFinalHandsAndScoreMessage(gameDealer, gamePlayers);
        OutputView.printGameResult(gameDealer.getName(), game.makeGameResult());
    }

    // TODO 재귀호출을 하거나 Exception 내기
    private static Game makeGame() {
        Game game = null;
        try {
            // TODO 생성자 로직 확장성 고민해보기
            OutputView.printAskNameMessage();
            return Game.of(InputView.readPlayerNamesAndBattings());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            makeGame();
        }
        return game;
    }

    private static void confirmParticipantsHands(Players players, Deck deck, Dealer dealer) {
        askDrawUntilConfirmPlayerHands(players, deck);
        confirmDealerHands(dealer, deck);
    }

    private static void printInitialHands(String dealerName, Card dealerFirstCard, List<Player> players) {
        OutputView.printDrawInitialHandsMessage(dealerName, players);
        OutputView.printParticipantsInitialHands(dealerName, dealerFirstCard, players);
    }

    private static void confirmDealerHands(Dealer dealer, Deck deck) {
        while (dealer.canAddCard()) {
            dealer.addCard(deck.draw());
            OutputView.printDealerDrawMessage(dealer);
        }
    }

    private static void askDrawUntilConfirmPlayerHands(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            askDrawToPlayer(player, deck);
        }
    }

    private static void askDrawToPlayer(Player player, Deck deck) {
        while (InputView.askDraw(player.getName()) && player.canAddCard()) {
            player.addCard(deck.draw());
            OutputView.printParticipantHands(player.getName(), player.getHandsCards());
        }
    }
}
