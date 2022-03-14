package blackjack.domain.card;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HoldCardsTest {

    @Test
    @DisplayName("보유한 카드에서 카드의 합을 구한다")
    void countCardNumber() {
        HoldCards holdCards = HoldCards.initTwoCards(Card.valueOf(CLUB, THREE), Card.valueOf(HEART, FOUR));
        int bestNumber = holdCards.countBestNumber();

        assertThat(bestNumber).isEqualTo(7);
    }

    @Test
    @DisplayName("에이스가 포함 될 경우 21에 가까운 숫자를 반환한다")
    void countCardNumberContainsAce() {
        HoldCards holdCards = HoldCards.initTwoCards(Card.valueOf(CLUB, ACE), Card.valueOf(HEART, TEN));
        int bestNumber = holdCards.countBestNumber();

        assertThat(bestNumber).isEqualTo(21);
    }

    @Test
    @DisplayName("보유한 카드에서 첫번째 카드를 가져온다.")
    void getFirstCard() {
        HoldCards holdCards = HoldCards.initTwoCards(Card.valueOf(CLUB, ACE), Card.valueOf(HEART, TEN));

        assertThat(holdCards.getFirstCard().get()).isEqualTo(Card.valueOf(CLUB, ACE));
    }

    @Test
    @DisplayName("중복된 카드로 생성할 경우 예외를 발생한다.")
    void throwDuplicateCardNumbers() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> HoldCards.initTwoCards(Card.valueOf(CLUB, ACE), Card.valueOf(CLUB, ACE)))
            .withMessage("카드가 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("중복된 카드를 추가할 경우 예외를 발생한다.")
    void throwDuplicateCardNumber() {
        HoldCards holdCards = HoldCards.initTwoCards(Card.valueOf(CLUB, ACE), Card.valueOf(HEART, TEN));

        assertThatIllegalArgumentException()
            .isThrownBy(() -> holdCards.addCard(Card.valueOf(CLUB, ACE)))
            .withMessage("카드가 중복될 수 없습니다.");
    }
}
