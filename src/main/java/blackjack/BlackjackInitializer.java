package blackjack;

import blackjack.domain.Deck;
import blackjack.domain.Players;
import blackjack.domain.participant.Dealer;
import blackjack.strategy.RandomShuffleStrategy;
import java.util.List;
import java.util.stream.IntStream;

public class BlackjackInitializer {

    private static final int BLACKJACK_INIT_CARD_AMOUNT = 2;

    private BlackjackInitializer() {
    }

    public static Dealer createDealer() {
        Deck deck = new Deck(new RandomShuffleStrategy());
        return new Dealer(deck);
    }

    public static Players createPlayer(final List<String> names) {
        return Players.of(names);
    }

    public static void initializeGame(final Dealer dealer, final Players players) {
        dealer.draw(BLACKJACK_INIT_CARD_AMOUNT);

        IntStream.range(0, BLACKJACK_INIT_CARD_AMOUNT)
                .forEach(i -> players.getPlayers()
                        .forEach(player -> player.draw(dealer.draw())));
    }
}
