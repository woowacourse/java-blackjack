package blackjack;

import blackjack.domain.card.CardFactory;
import blackjack.domain.deck.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackApplication {

    public static void main(String[] args) {
        Deck deck = new Deck(CardFactory.generate());
        Dealer dealer = new Dealer();
        List<Player> players = initializePlayers(InputView.askPlayerNames());

        drawInitialCards(dealer, players, deck);

        OutputView.printInitialCards(dealer, players);

        while (dealer.shouldDrawCard()) {
            dealer.add(deck.pick());
        }

    }

    private static void drawInitialCards(Dealer dealer, List<Player> players, Deck deck) {
        for (int i = 0; i < 2; i++) {
            drawCards(dealer, players, deck);
        }
    }

    private static void drawCards(Dealer dealer, List<Player> players, Deck deck) {
        dealer.add(deck.pick());
        for (Player player : players) {
            player.add(deck.pick());
        }
    }

    private static List<Player> initializePlayers(String names) {
        return Arrays.stream(names.split(","))
                .map(Player::new)
                .collect(Collectors.toList());
    }
}
