package blackjack;

import blackjack.domain.card.Deck;
import blackjack.domain.money.Profit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        Deck deck = Deck.createShuffledDeck();
        Dealer dealer = new Dealer();
        Players players = createPlayers();

        drawStartCards(dealer, players, deck);
        play(players, dealer, deck);
        printResult(dealer, players);
    }

    private Players createPlayers() {
        List<String> names = inputView.inputPlayerNames();
        List<Player> players = names.stream()
                .map(name -> Player.of(name, inputView.inputBetAmount(name)))
                .toList();
        return new Players(players);
    }

    private void drawStartCards(Dealer dealer, Players players, Deck deck) {
        dealer.drawStartCards(deck);
        players.drawStartCards(deck);
        outputView.printStartStatus(dealer, players);
    }

    private void play(Players players, Dealer dealer, Deck deck) {
        for (Player player : players.getPlayers()) {
            playTurn(player, deck);
        }
        playTurn(dealer, deck);
    }

    private void playTurn(Player player, Deck deck) {
        while (player.isDrawable() && inputView.isPlayerWantDraw(player.getName())) {
            player.add(deck.draw());
            outputView.printPlayerCards(player);
        }
    }

    private void playTurn(Dealer dealer, Deck deck) {
        while (dealer.isDrawable()) {
            outputView.printDealerDraw();
            dealer.add(deck.draw());
        }
    }

    private void printResult(Dealer dealer, Players players) {
        outputView.printEndingStatus(dealer, players);

        Profit dealerProfit = dealer.calculateDealerProfit(players);
        Map<Player, Profit> playersProfit = dealer.calculatePlayersProfit(players);
        outputView.printMatchResult(dealerProfit, playersProfit);
    }
}
