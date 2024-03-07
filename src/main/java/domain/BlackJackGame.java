package domain;

import java.util.stream.IntStream;

public class BlackJackGame {

    public static final int INITIAL_CARD_COUNT = 2;
    public static final int DEALER_THRESHOLD = 16;

    public void ready(final Dealer dealer, final Players players) {
        giveCards(dealer, dealer);
        players.forEach(player -> giveCards(dealer, player));
    }

    private void giveCards(final Dealer dealer, final Participant participant) {
        IntStream.range(0, INITIAL_CARD_COUNT)
                .forEach(__ -> dealer.deal(participant));
    }
}
