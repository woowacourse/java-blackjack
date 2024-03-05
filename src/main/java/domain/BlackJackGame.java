package domain;

import java.util.stream.IntStream;

public class BlackJackGame {

    private static final int INITIAL_CARD_COUNT = 2;

    public void play(final Dealer dealer, final Players players) {
        giveCards(dealer, dealer);
        players.forEach(player -> giveCards(dealer, player));
    }

    private void giveCards(final Dealer dealer, final Participant participant) {
        IntStream.range(0, INITIAL_CARD_COUNT)
                .forEach(__ -> participant.receive(dealer.drawCard()));
    }
}
