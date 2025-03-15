import static card.CardDeck.DRAW_COUNT_WHEN_HIT;
import static card.CardDeck.DRAW_COUNT_WHEN_START;

import card.Card;
import card.CardDeck;
import card.RandomCardShuffler;
import game.Dealer;
import game.GameResult;
import game.Player;
import game.Players;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        CardDeck cardDeck = CardDeck.prepareDeck(new RandomCardShuffler());

        List<String> playerNames = inputView.readPlayerNames();
        List<Integer> playerBetting = inputView.readBettingMoney(playerNames);
        Players players = Players.of(playerNames, playerBetting);
        Dealer dealer = new Dealer();

        players.draw(cardDeck);
        dealer.draw(cardDeck.drawCard(DRAW_COUNT_WHEN_START));
        Card dealerCard = dealer.getSingleCard();
        outputView.printInitialGame(dealerCard, players.getPlayers());

        for (Player player : players.getPlayers()) {
            while (!player.isBust() && inputView.readDrawMoreCard(player)) {
                player.draw(cardDeck.drawCard(DRAW_COUNT_WHEN_HIT));
                outputView.printPlayerDraw(player);
            }
        }
        while (!dealer.isBust() && !dealer.isOverDrawBound()) {
            dealer.draw(cardDeck.drawCard(DRAW_COUNT_WHEN_HIT));
            outputView.printDealerDrawMessage();
        }
        outputView.printHitProcess(dealer, players);

        List<GameResult> gameResults = players.judgeGameResult(dealer);
        List<Integer> playerProfits = players.evaluate(gameResults);

        int dealerProfit = dealer.evaluate(playerProfits);

        outputView.printDealerProfit(dealerProfit);
        outputView.printPlayersProfit(players.getAllPlayerNames(), playerProfits);
    }

}
