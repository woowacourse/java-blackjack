package blackjack.domain.card;

import static blackjack.fixtures.BlackjackFixtures.SPADE_ACE;
import static blackjack.fixtures.BlackjackFixtures.SPADE_FOUR;
import static blackjack.fixtures.BlackjackFixtures.SPADE_TEN;
import static blackjack.fixtures.BlackjackFixtures.SPADE_THREE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HoldCardsTest {

    @Test
    @DisplayName("보유한 카드에서 카드의 합을 구한다")
    void countCardNumber() {
        HoldCards holdCards = HoldCards.initTwoCards(SPADE_THREE, SPADE_FOUR);
        int bestNumber = holdCards.countBestNumber();

        assertThat(bestNumber).isEqualTo(7);
    }

    @Test
    @DisplayName("에이스가 포함 될 경우 21에 가까운 숫자를 반환한다")
    void countCardNumberContainsAce() {
        HoldCards holdCards = HoldCards.initTwoCards(SPADE_ACE, SPADE_TEN);
        int bestNumber = holdCards.countBestNumber();

        assertThat(bestNumber).isEqualTo(21);
    }

    @Test
    @DisplayName("보유한 카드에서 첫번째 카드를 가져온다.")
    void getFirstCard() {
        HoldCards holdCards = HoldCards.initTwoCards(SPADE_ACE, SPADE_TEN);

        assertThat(holdCards.getFirstCard().get()).isEqualTo(SPADE_ACE);
    }

    @Test
    @DisplayName("중복된 카드로 생성할 경우 예외를 발생한다.")
    void throwExceptionDuplicateCardNumbers() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> HoldCards.initTwoCards(SPADE_ACE, SPADE_ACE))
            .withMessage("카드가 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("중복된 카드를 추가할 경우 예외를 발생한다.")
    void throwExceptionDuplicateCardNumber() {
        HoldCards holdCards = HoldCards.initTwoCards(SPADE_ACE, SPADE_TEN);

        assertThatIllegalArgumentException()
            .isThrownBy(() -> holdCards.addCard(SPADE_ACE))
            .withMessage("카드가 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("초기 카드가 2장이 아닌경우 예외를 발생한다.")
    void throwExceptionInvalidSize() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new HoldCards(List.of(SPADE_ACE)))
            .withMessage("초기 카드는 2장이어야 합니다.");
    }

    @Test
    @DisplayName("두 장의 카드 합이 블랙잭(21)인 경우 true를 반환한다.")
    void isBlackjack() {
        HoldCards holdCards = HoldCards.initTwoCards(SPADE_ACE, SPADE_TEN);

        assertThat(holdCards.isBlackjack()).isTrue();
    }
}
