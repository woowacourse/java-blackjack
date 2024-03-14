package blackjack.controller;

import blackjack.domain.Game;
import blackjack.domain.GameResult;
import blackjack.domain.deck.Card;
import blackjack.domain.deck.Deck;
import blackjack.domain.participant.BetMoney;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class GameMachine {

    private GameMachine() {
    }

    public static void run() {
        Deck deck = Deck.createShuffledDeck();
        Game game = makeGame(deck);
        Dealer gameDealer = game.getDealer();
        Players gamePlayers = game.getPlayers();

        printInitialHands(gameDealer, gamePlayers);

        confirmParticipantsHands(gamePlayers, deck, gameDealer);

        OutputView.printFinalHandsAndScoreMessage(gameDealer, gamePlayers);
        GameResult gameResult = game.makeGameResult();
        OutputView.printGameResult(gameDealer, gameResult);
        OutputView.printProfitResult(gameDealer, gamePlayers, gameResult);
    }

    private static Game makeGame(Deck deck) {
        OutputView.printAskNameMessage();

        List<String> playerNames = InputView.readNames();
        Players players = createPlayers(playerNames, deck);

        Dealer dealer = Dealer.createDealerWithCards(drawInitialCards(deck));
        return new Game(dealer, players);
    }

    private static List<Card> drawInitialCards(Deck deck) {
        List<Card> cards = new ArrayList<>();
        cards.add(deck.draw());
        cards.add(deck.draw());
        return cards;
    }

    private static Players createPlayers(List<String> playerNames, Deck deck) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            BetMoney betMoney = new BetMoney(InputView.readBetAmount(playerName));
            Player player = Player.createPlayer(new Name(playerName), drawInitialCards(deck), betMoney);
            players.add(player);
        }
        return new Players(players);
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
        dealer.confirmDealerHands(deck, OutputView::printDealerDrawMessage);
    }

    private static void askDrawUntilConfirmHands(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            askDrawToPlayer(player, deck);
        }
    }

    private static void askDrawToPlayer(Player player, Deck deck) {
        boolean isDraw = true;
        while (player.canDraw() && isDraw) {
            OutputView.printAskDrawMessage(player.getName());
            isDraw = player.attemptDraw(InputView::askDraw, deck);
            OutputView.printPlayerHands(player);
        }
    }
}
