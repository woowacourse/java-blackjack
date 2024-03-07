package blackjack;

import blackjack.domain.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

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
        return Players.from(names);
    }

    private void drawStartCards(Dealer dealer, Players players, Deck deck) {
        dealer.drawStartCards(deck);
        players.drawStartCards(deck);
        outputView.printStartCards(dealer, players);
    }

    private void play(Players players, Dealer dealer, Deck deck) {
        players.play(this::playTurn, deck);
        while (dealer.isDrawable()) {
            outputView.printDealerDraw();
            dealer.add(deck.draw());
        }
    }

    private void playTurn(Player player, Deck deck) {
        while (player.isDrawable() && inputView.isPlayerWantDraw(player.getName())) {
            player.add(deck.draw());
            outputView.printPlayerCards(player);
        }
    }

    private void printResult(Dealer dealer, Players players) {
        outputView.printResultCardAndScore(dealer, players);
        outputView.printDealerMatchResult(dealer.match(players));
        for (Player player : players.getPlayers()) {
            outputView.printPlayerMatchResult(player.getName(), player.isWin(dealer));
        }
    }
}
