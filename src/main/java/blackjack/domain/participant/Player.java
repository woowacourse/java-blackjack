package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import java.util.List;

public class Player extends Participant {

    private static final int PLAYER_DISTRIBUTE_CARD_THRESHOLD = 21;
    private static final double BLACKJACK_BONUS_RATE = 1.5;
    private static final int DRAW_PAYOUT = 0;

    private final String name;
    private int payout;

    public Player(final String name) {
        validateName(name);
        this.name = name;
        this.payout = 0;
    }

    public void bet(final int amount) {
        this.payout += amount;
    }

    public int calculatePayout(final Dealer dealer) {
        GameResult result = GameResult.getPlayerGameResultFrom(dealer, this);
        if (result == GameResult.LOSE) {
            return convertToNegativeSign(payout);
        }
        if (isBlackjack()) {
            return calculatePayoutWithBlackjack(dealer);
        }
        if (result == GameResult.DRAW) {
            return DRAW_PAYOUT;
        }
        return payout;
    }

    private int calculatePayoutWithBlackjack(final Dealer dealer) {
        if (dealer.isBlackjack()) {
            return payout;
        }
        return (int) Math.floor(payout * BLACKJACK_BONUS_RATE);
    }

    private int convertToNegativeSign(final int value) {
        return -value;
    }

    @Override
    public List<Card> openInitialCards() {
        return super.cards;
    }

    @Override
    public boolean isPossibleToAdd() {
        return super.calculateDenominations() < PLAYER_DISTRIBUTE_CARD_THRESHOLD;
    }

    public String getName() {
        return name;
    }

    private void validateName(final String name) {
        boolean isAllLetter = name.chars().allMatch(Character::isLetter);
        if (isAllLetter) {
            return;
        }
        throw new IllegalArgumentException("이름은 알파벳 소문자만 입력 가능합니다.");
    }
}
