package blackjack;

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
        BetManager betManager = readPlayersBetMoney(game.getPlayers());

        printInitialHands(game.getDealer(), game.getPlayers());

        confirmParticipantsHands(game, deck);

        OutputView.printFinalHandsAndScoreMessage(game.getDealer(), game.getPlayers());

        GameResult gameResult = game.makeGameResult();
        OutputView.printGameResult(game.getDealer(), gameResult);
        OutputView.printProfitResult(game.getDealer(), game.getPlayers(), gameResult, betManager);
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
            Player player = Player.createPlayerWithCards(new Name(playerName), deck.drawInitialCards());
            players.add(player);
        }
        return new Players(players);
    }

    private static void printInitialHands(Dealer dealer, Players players) {
        OutputView.printDrawInitialHandsMessage(dealer, players);
        OutputView.printParticipantsInitialHands(dealer, players);
    }

    private static void confirmParticipantsHands(Game game, Deck deck) {
        game.confirmPlayersHands(deck, InputView::askDraw, OutputView::printPlayerHands);
        game.confirmDealerHands(deck, OutputView::printDealerDrawMessage);
    }
}
