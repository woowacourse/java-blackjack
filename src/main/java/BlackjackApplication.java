import static card.CardDeck.DRAW_COUNT_WHEN_HIT;
import static card.CardDeck.DRAW_COUNT_WHEN_START;

import card.Card;
import card.CardDeck;
import card.RandomCardShuffler;
import console.InputConsole;
import console.OutputConsole;
import game.Dealer;
import game.GameResult;
import game.Player;
import game.Players;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class BlackjackApplication {

    public static void main(String[] args) {
        InputConsole inputConsole = new InputConsole(new Scanner(System.in));
        OutputConsole outputConsole = new OutputConsole();

        CardDeck cardDeck = CardDeck.prepareDeck(new RandomCardShuffler());

        List<String> playerNames = inputConsole.readPlayerNames();
        List<Integer> bettingMoney = inputConsole.readBettingMoney(playerNames);

        Players players = new Players(IntStream.range(0, playerNames.size())
                .mapToObj(i -> new Player(playerNames.get(i), bettingMoney.get(i)))
                .toList());
        Dealer dealer = new Dealer();

        players.draw(cardDeck);
        dealer.draw(cardDeck.drawCard(DRAW_COUNT_WHEN_START));
        Card dealerCard = dealer.getSingleCard();

        outputConsole.printInitialGame(dealerCard, players.getPlayers());

        for (Player player : players.getPlayers()) {
            while (!player.isBust() && inputConsole.readDrawMoreCard(player)) {
                player.draw(cardDeck.drawCard(DRAW_COUNT_WHEN_HIT));
                outputConsole.printPlayerCard(player);
            }
        }
        while (!dealer.isBust() && !dealer.isOverDrawBound()) {
            dealer.draw(cardDeck.drawCard(DRAW_COUNT_WHEN_HIT));
            outputConsole.printDealerDrawMessage();
        }
        outputConsole.printGameResult(dealer, players);

        List<GameResult> gameResults = players.judgeGameResult(dealer);
        List<Integer> playerProfits = players.evaluate(gameResults);

        int dealerProfit = dealer.evaluate(playerProfits);

        outputConsole.printDealerProfit(dealerProfit);
        outputConsole.printPlayersProfit(players.getAllPlayerNames(), playerProfits);
    }

}
