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
        try {
            Deck deck = Deck.createShuffledDeck();
            Game game = makeGame();
            Dealer gameDealer = game.getDealer();
            Players gamePlayers = game.getPlayers();

            printInitialHands(gameDealer.getName(), gameDealer.getFirstCard(), gamePlayers.getPlayers());
            confirmParticipantsHands(gamePlayers, deck, gameDealer);

            OutputView.printFinalHandsAndScoreMessage(gameDealer, gamePlayers);
            OutputView.printGameResult(gameDealer.getName(), game.makeGameResult());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Game makeGame() {
        OutputView.printAskNameMessage();
        return Game.of(InputView.readPlayerNamesAndBattings());
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
