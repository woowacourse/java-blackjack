package blackjack.controller;

import blackjack.domain.BetManager;
import blackjack.domain.Game;
import blackjack.domain.GameResult;
import blackjack.domain.deck.Deck;
import blackjack.domain.deck.ShuffledDeckCreateStrategy;
import blackjack.domain.participant.BetMoney;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGameBoard {

    private BlackJackGameBoard() {
    }

    public static void startGame() {
        Deck deck = new Deck(new ShuffledDeckCreateStrategy());
        Game game = makeGame(deck);
        Dealer gameDealer = game.getDealer();
        Players gamePlayers = game.getPlayers();
        BetManager betManager = readPlayersBetMoney(gamePlayers);

        printInitialHands(gameDealer, gamePlayers);

        confirmParticipantsHands(gameDealer, gamePlayers, deck);

        OutputView.printFinalHandsAndScoreMessage(gameDealer, gamePlayers);

        GameResult gameResult = game.makeGameResult();
        OutputView.printGameResult(gameDealer, gameResult);
        OutputView.printProfitResult(gameDealer, gamePlayers, gameResult, betManager);
    }

    private static BetManager readPlayersBetMoney(Players players) {
        Map<Player, BetMoney> betMoneys = new HashMap<>();
        for (Player player : players.getPlayers()) {
            BetMoney betMoney = new BetMoney(InputView.readBetMoney(player.getName()));
            betMoneys.put(player, betMoney);
        }
        return new BetManager(betMoneys);
    }

    private static Game makeGame(Deck deck) {
        OutputView.printAskNameMessage();

        List<String> playerNames = InputView.readNames();
        Players players = createPlayers(playerNames, deck);

        Dealer dealer = Dealer.createDealerWithCards(deck.drawInitialCards());
        return new Game(dealer, players);
    }

    private static Players createPlayers(List<String> playerNames, Deck deck) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            Player player = Player.createPlayer(new Name(playerName), deck.drawInitialCards());
            players.add(player);
        }
        return new Players(players);
    }

    private static void printInitialHands(Dealer dealer, Players players) {
        OutputView.printDrawInitialHandsMessage(dealer, players);
        OutputView.printParticipantsInitialHands(dealer, players);
    }

    private static void confirmParticipantsHands(Dealer dealer, Players players, Deck deck) {
        askDrawUntilConfirmHands(players, deck);
        confirmDealerHands(dealer, deck);
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
        while (player.canDraw() && !player.isBust()) {
            OutputView.printAskDrawMessage(player.getName());
            player.draw(InputView::askDraw, deck);
            OutputView.printPlayerHands(player);
        }
    }
}
