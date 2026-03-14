package blackjack.domain.participant;

import static blackjack.global.ParticipantConstants.DEALER_DISTRIBUTE_COUNT;
import static blackjack.global.ParticipantConstants.DEALER_HIT_THRESHOLD;

import blackjack.domain.trump.Card;
import blackjack.domain.trump.Trump;
import blackjack.dto.CardDto;

import java.util.List;

public class Dealer extends Participant {

    private static final String DEALER_NICKNAME = "딜러";

    private final Trump trump;

    public Dealer(final Hand hand, final Status status, final Trump trump) {
        super(hand, status);
        this.trump = trump;
    }

    public void giveCardTo(final Player player) {
        final Card card = trump.draw();
        player.addCard(card);
    }

    public void giveCardMyself() {
        final Card card = trump.draw();
        addCard(card);
    }

    public void pitch(final List<Player> players) {
        for (int i = 0; i < DEALER_DISTRIBUTE_COUNT; i++) {
            players.forEach(this::giveCardTo);
            giveCardMyself();
        }
    }

    public void decideHit() {
        if (hand.calculateScore() > DEALER_HIT_THRESHOLD) {
            stay();
        }
    }

    public List<CardDto> getOpenCardNames() {
        final int holeIndex = 1;
        return hand.getCardNames(holeIndex);
    }

    @Override
    public String getNickname() {
        return DEALER_NICKNAME;
    }
}
