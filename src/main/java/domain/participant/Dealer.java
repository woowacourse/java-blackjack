package domain.participant;

import domain.blackjack.Deck;
import domain.blackjack.WinStatus;
import domain.card.Card;

public class Dealer extends Participant {

    private static final int DEALER_HIT_COUNT = 16;

    private final Deck deck;

    public Dealer() {
        super(new Name("딜러"));
        this.deck = new Deck();
    }

    public boolean isNotBlackJack() {
        return !isBlackJack();
    }

    public WinStatus calculatePlayerWinStatus(Player player) {
        if (player.isBust()) {
            return WinStatus.LOSE;
        }
        if (isBust()) {
            return WinStatus.WIN;
        }
        return calculatePlayerWinStatusWhenNotBust(player);
    }

    private WinStatus calculatePlayerWinStatusWhenNotBust(Player player) {
        int playerScore = player.getScore();
        int dealerScore = getScore();
        if (playerScore == dealerScore) {
            return WinStatus.PUSH;
        }
        if (playerScore > dealerScore) {
            return WinStatus.WIN;
        }
        return WinStatus.LOSE;
    }

    @Override
    public boolean canHit() {
        return hands.calculateScore() <= DEALER_HIT_COUNT;
    }

    public Card draw() {
        return deck.draw();
    }
}
