package blackjack.controller;

import blackjack.model.Game;
import blackjack.model.betting.Profit;
import blackjack.model.card.Card;
import blackjack.model.card.Deck;
import blackjack.model.card.RandomCardShuffler;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import blackjack.model.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = getPlayers();
        Game game = createGame(players);
        dealInitialHand(game);
        askHitForAllPlayer(game);
        dealerHitOrNot(game);
        displayResult(game);
    }

    private Players getPlayers() {
        List<Player> players = new ArrayList<>();
        List<String> playerNames = inputView.readPlayerNames();
        for (String name : playerNames) {
            int stake = inputView.readPlayerStake(name);
            players.add(Player.of(name, stake));
        }
        return new Players(players);
    }

    private Game createGame(Players players) {
        Deck deck = Deck.createShuffledDeck(Card.createDeck(), new RandomCardShuffler());
        Dealer dealer = new Dealer(deck);
        return new Game(dealer, players);
    }

    private void dealInitialHand(Game game) {
        game.dealInitialCards();
        outputView.printInitialCards(game.getDealerVisibleCard(), game.getPlayers());
    }

    private void askHitForAllPlayer(Game game) {
        for (Player player : game.getPlayers()) {
            askHitForPlayer(game, player);
        }
    }

    private void askHitForPlayer(Game game, Player player) {
        while (player.canHit() && inputView.readHitOrNot(player.getName())) {
            game.hitPlayer(player);
            outputView.printPlayerHand(player);
        }
    }

    private void dealerHitOrNot(Game game) {
        boolean isDealerHit = game.hitDealer();
        outputView.printDealerHit(isDealerHit);
    }

    private void displayResult(Game game) {
        outputView.printDealerHandAndTotal(game.getDealerHand(), game.getDealerTotal());
        outputView.printPlayerHandAndTotal(game.getPlayers());
        Map<Player, Profit> playersProfit = game.calculatePlayersProfit();
        Profit dealerProfit = game.calculateDealerProfit(playersProfit);
        outputView.printProfit(dealerProfit, playersProfit);
    }
}
