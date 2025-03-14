import static card.CardDeck.DRAW_COUNT_WHEN_HIT;
import static card.CardDeck.DRAW_COUNT_WHEN_START;

import card.Card;
import card.CardDeck;
import card.RandomCardShuffler;
import console.Input;
import console.Output;
import game.Dealer;
import game.GameResult;
import game.Player;
import game.Players;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class BlackjackApplication {

    public static void main(String[] args) {
        Input input = new Input(new Scanner(System.in));
        Output output = new Output();

        CardDeck cardDeck = CardDeck.prepareDeck(new RandomCardShuffler());

        List<String> playerNames = input.readPlayerNames();
        List<Integer> bettingMoney = input.readBettingMoney(playerNames);

        Players players = new Players(IntStream.range(0, playerNames.size())
                .mapToObj(i -> new Player(playerNames.get(i), bettingMoney.get(i)))
                .toList());
        Dealer dealer = new Dealer();

        players.draw(cardDeck);
        dealer.draw(cardDeck.drawCard(DRAW_COUNT_WHEN_START));
        Card dealerCard = dealer.getSingleCard();
        output.printInitialGame(dealerCard, players.getPlayers());

        for (Player player : players.getPlayers()) {
            while (!player.isBust() && input.readDrawMoreCard(player)) {
                player.draw(cardDeck.drawCard(DRAW_COUNT_WHEN_HIT));
                output.printPlayerCard(player);
            }
        }
        while (!dealer.isBust() && !dealer.isOverDrawBound()) {
            dealer.draw(cardDeck.drawCard(DRAW_COUNT_WHEN_HIT));
            output.printDealerDrawMessage();
        }
        output.printGameResult(dealer, players);

        List<GameResult> gameResults = players.judgeGameResult(dealer);
        List<Integer> playerProfits = players.evaluate(gameResults);

        int dealerProfit = dealer.evaluate(playerProfits);

        output.printDealerProfit(dealerProfit);
        output.printPlayersProfit(players.getAllPlayerNames(), playerProfits);
    }

}
