package blackjack.domain.user;

import blackjack.domain.card.CardGroup;
import blackjack.domain.result.Score;

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

    public boolean isSameName(final Name name) {
        return getName().isSame(name);
    }

    public boolean isDrawable() {
        return getScore().isDrawAble();
    }

    public Double calculateProfitRate(final Dealer dealer) {
        final Score playerScore = getScore();
        final Score dealerScore = dealer.getScore();
        if (playerScore.isBlackJack()) {
            return calculateProfitRateWhenPlayerIsBlackJack(dealerScore);
        }
        if (playerScore.isBust()) {
            return -1.0;
        }
        if (dealerScore.isBust()) {
            return 1.0;
        }
        return calculateProfitRateByScoreValue(dealer);
    }

    private Double calculateProfitRateWhenPlayerIsBlackJack(final Score dealerScore) {
        if (dealerScore.isBlackJack()) {
            return 0.0;
        }
        return 1.5;
    }

    //TODO: 네이밍 고민해보기
    private Double calculateProfitRateByScoreValue(final Dealer dealer) {
        final Score playerScore = getScore();
        final Score dealerScore = dealer.getScore();

        if (playerScore.isBigger(dealerScore)) {
            return 1.0;
        }
        if (playerScore.isEqual(dealerScore)) {
            return 0.0;
        }
        return -1.0;
    }
}
