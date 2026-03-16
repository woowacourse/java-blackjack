package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.judgement.Status;
import blackjack.domain.card.Trump;
import java.util.List;
import java.util.stream.IntStream;

public class Dealer extends Participant {

    public static final int DEALER_HIT_THRESHOLD = 16;

    private final Trump trump;
    private final Name nickname;

    public Dealer(final Hand hand, final Status status, final Trump trump) {
        super(hand, status);
        this.trump = trump;
        nickname = new DealerName();
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
        final int distributeCount = 2;
        IntStream.range(0, distributeCount)
                .forEach(round -> {
                    players.forEach(this::giveCard);
                    giveCard();
                });
    }

    public void decideHit() {
        int totalScore = getScore();
        if (totalScore > DEALER_HIT_THRESHOLD) {
            stay();
        }
    }

    public List<String> getOpenCardNames() {
        final int holeIndex = 1;
        List<String> allCardNames = getCardNames();
        return allCardNames.subList(holeIndex, allCardNames.size());
    }

    @Override
    public Name getName() {
        return nickname;
    }
}
