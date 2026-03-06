package blackjack.domain;

import java.util.List;
import java.util.stream.IntStream;

public class Dealer {

    private final Hand hand;
    private final Trump trump;

    public Dealer(Hand hand, Trump trump) {
        this.hand = hand;
        this.trump = trump;
    }

    public void giveCard(final Player player) {
        final Card card = trump.draw();
        player.addCard(card);
    }

    public void giveCard() {
        final Card card = trump.draw();
        hand.add(card);
    }

    public void pitch(final List<Player> players) {
        IntStream.range(0, 2)
            .forEach(round -> {
                players.forEach(this::giveCard);
                giveCard();
            });
    }

    public List<String> getOpenedCardNames() {
        return hand.getCardNames(1);
    }
}
