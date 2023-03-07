package blackjack.domain.user;

import blackjack.domain.card.CardGroup;
import blackjack.domain.result.Score;
import blackjack.domain.result.WinningStatus;

public class Player extends User {

    private static final int FIRST_OPEN_CARD_COUNT = 2;

    protected Player(String name, CardGroup cardGroup) {
        super(name, cardGroup);
    }

    @Override
    public CardGroup getFirstOpenCardGroup() {
        return getCardGroups().getSubCardGroup(FIRST_OPEN_CARD_COUNT);
    }

    public boolean isSameName(final String name) {
        return getName().equals(name);
    }

    public WinningStatus calculatePlayerWinningStatus(final Dealer dealer) {
        if (dealer.isBust()) {
            return compareByBust();
        }
        return compareByScore(dealer);
    }

    private WinningStatus compareByBust() {
        if (isBust()) {
            return WinningStatus.LOSE;
        }
        return WinningStatus.WIN;
    }

    private WinningStatus compareByScore(final Dealer dealer) {
        final Score playerScore = getScore();
        final Score dealerScore = dealer.getScore();
        if (playerScore.isBust() || dealerScore.isBigger(playerScore)) {
            return WinningStatus.LOSE;
        }
        if (playerScore.isEqual(dealerScore)) {
            return WinningStatus.TIE;
        }
        return WinningStatus.WIN;
    }
}
