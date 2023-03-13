package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CardsTest {

    @DisplayName("카드 일급 컬렉션 생성 테스트")
    @Test
    void Should_Success_When_Create() {
        assertDoesNotThrow(() -> new Cards());
    }

    @DisplayName("Cards에 카드 한장을 추가할 수 있다.")
    @Test
    void Should_Success_When_AddCard() {
        // given
        Cards cards = new Cards();
        Card card = new Card(CardNumber.ACE, CardSymbol.HEARTS);
        cards.add(card);

        // when, then
        assertThat(cards.getCards()).containsExactly(card);
    }

    @DisplayName("Cards에 Ace 카드가 있다면 True를 반환할 수 있다.")
    @Test
    void Should_True_When_HasAce() {
        // given
        Cards cards = new Cards();
        Card card = new Card(CardNumber.ACE, CardSymbol.HEARTS);
        cards.add(card);

        // when, then
        assertThat(cards.hasAce()).isTrue();
    }

    @DisplayName("Cards에 Ace 카드가 없다면 False를 반환할 수 있다.")
    @Test
    void Should_False_When_HasNoAce() {
        // given
        Cards cards = new Cards();
        Card card = new Card(CardNumber.TEN, CardSymbol.HEARTS);
        cards.add(card);

        // when, then
        assertThat(cards.hasAce()).isFalse();
    }

    @DisplayName("Cards의 점수를 계산할 수 있다.")
    @Test
    void Should_11_When_tenPlusAce() {
        // given
        Cards cards = new Cards();
        Card card = new Card(CardNumber.TEN, CardSymbol.HEARTS);
        Card card2 = new Card(CardNumber.ACE, CardSymbol.HEARTS);

        cards.add(card);
        cards.add(card2);

        // when, then
        assertThat(cards.calculateScore()).isEqualTo(11);
    }

    @DisplayName("카드가 없다면 카드의 첫번째 요소를 반환할 때 예외가 발생한다.")
    @Test
    void Should_ThrowException_When_NoCard() {
        // given
        Cards cards = new Cards();

        // when, then
        assertThatThrownBy(() -> cards.getOneCard())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드가 없습니다.");
    }

}
