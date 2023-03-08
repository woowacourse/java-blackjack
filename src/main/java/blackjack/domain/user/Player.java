package blackjack.domain.user;

import blackjack.domain.card.CardGroup;
import blackjack.domain.result.Score;
import blackjack.domain.result.WinningStatus;

public class Player extends User {


    static final String NAME_IS_DEALER_NAME_EXCEPTION_MESSAGE = "플레이어의 이름은 '딜러'일 수 없습니다.";
    private static final int FIRST_OPEN_CARD_COUNT = 2;

    protected Player(final String name, final CardGroup cardGroup) {
        super(name, cardGroup);
        validateIsDealerName(name);
    }

    private void validateIsDealerName(final String name) {
        if (Dealer.DEALER_NAME.equals(name)) {
            throw new IllegalArgumentException(NAME_IS_DEALER_NAME_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public CardGroup getFirstOpenCardGroup() {
        return getCardGroups().getSubCardGroup(FIRST_OPEN_CARD_COUNT);
    }

    public boolean isSameName(final String name) {
        return getName().equals(name);
    }

    public WinningStatus calculateWinningStatus(final Dealer dealer) {
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
