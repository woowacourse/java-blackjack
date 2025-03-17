import static card.CardDeck.DRAW_COUNT_WHEN_HIT;
import static card.CardDeck.DRAW_COUNT_WHEN_START;

import card.CardDeck;
import card.RandomCardShuffler;
import game.Dealer;
import game.GameResults;
import game.Player;
import game.Players;
import game.Profits;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        CardDeck cardDeck = CardDeck.prepareDeck(new RandomCardShuffler());
        Dealer dealer = new Dealer();

        List<String> playerNames = inputView.readPlayerNames();
        List<Integer> playerBetting = inputView.readBettingMoney(playerNames);
        Players players = Players.joinWithBets(playerNames, playerBetting);

        dealer.draw(cardDeck.drawCard(DRAW_COUNT_WHEN_START));
        players.draw(cardDeck);
        outputView.printInitialGame(dealer.getSingleCard(), players.getPlayers());

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

        GameResults gameResults = GameResults.determine(dealer, players.getPlayers());
        Profits profits = Profits.evaluateProfits(players.getBettingMoneys(), gameResults.getGameResults());

        int dealerProfit = profits.evaluateDealerProfit();

        outputView.printDealerProfit(dealerProfit);
        outputView.printPlayersProfit(players.getAllPlayerNames(), profits.getProfits());
    }

}
