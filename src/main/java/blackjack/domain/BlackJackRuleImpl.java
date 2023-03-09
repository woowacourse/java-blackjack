package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class BlackJackRuleImpl implements BlackJackRule {

    private static final int BUST_POINT = 21;

    @Override
    public ResultType calculateDealerResult(final Dealer dealer, final Player player) {
        //이 부분이 애매해서 리팩토링을 해보려고 했는데, 더 좋은 방법이 생각나지 않아서 일단 이렇게 구현했습니다.
        //enum 클래스에 BiPredicate를 추가해서 구현해보려고 했는데, enum 클래스에 메서드를 추가하는 것이 맞는지 잘 모르겠습니다.
        if (isBlackJackWin(dealer, player)) {
            return ResultType.BLACKJACK_WIN;
        }
        if (isBlackJackLose(dealer, player)) {
            return ResultType.BLACKJACK_LOSE;
        }
        final int dealerScore = dealer.currentScore();
        final int playerScore = player.currentScore();
        if (isTie(dealerScore, playerScore)) {
            return ResultType.TIE;
        }
        if (isDealerWin(dealerScore, playerScore)) {
            return ResultType.WIN;
        }
        return ResultType.LOSE;
    }

    private boolean isBlackJackWin(final Dealer dealer, final Player player) {
        return dealer.hasBlackJack() && !player.hasBlackJack();
    }

    private boolean isBlackJackLose(final Dealer dealer, final Player player) {
        return !dealer.hasBlackJack() && player.hasBlackJack();
    }

    private boolean isTie(final int dealerScore, final int playerScore) {
        if (playerScore > BUST_POINT && dealerScore > BUST_POINT) {
            return true;
        }
        return playerScore == dealerScore;
    }

    private boolean isDealerWin(final int dealerScore, final int playerScore) {
        if (playerScore > BUST_POINT) {
            return true;
        }
        if (dealerScore > BUST_POINT) {
            return false;
        }
        return dealerScore > playerScore;
    }
}
