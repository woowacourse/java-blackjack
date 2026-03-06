package blackjack.domain;

import java.util.List;
import java.util.stream.IntStream;

public class Dealer extends Participant {
    public static final int DEALER_HIT_THRESHOLD = 16;

    private final Trump trump;

    public Dealer(final Hand hand,final Status status, final Trump trump) {
        super(hand, status);
        this.trump = trump;
    }

    public void giveCard(final Player player) {
        final Card card = trump.draw();
        player.addCard(card);
    }

    public void giveCard() {
        final Card card = trump.draw();
        addCard(card);
    }

    public void pitch(final List<Player> players) {
        IntStream.range(0, 2)
            .forEach(round -> {
                players.forEach(this::giveCard);
                giveCard();
            });
    }

    public void decideHit() {
        int totalScore = hand.calculateScore();
        if (totalScore > DEALER_HIT_THRESHOLD) {
            stay();
        }
    }

    @Override
    public String getNickname() {
        return "딜러";
    }

    @Override
    public List<String> getCardNames() {
        return hand.getCardNames(1);
    }

}
