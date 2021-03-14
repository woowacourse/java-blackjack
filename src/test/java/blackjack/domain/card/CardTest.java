package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class CardTest {
    @Test
    @DisplayName("카드가 잘 생성되었는지 확인")
    void create() {
        assertThatCode(() -> new Card(CardLetter.TWO, CardSuit.CLOVER))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("해당 카드가 ACE 넘버인지 확인")
    void isAce() {
        final Card aceCard = new Card(CardLetter.ACE, CardSuit.CLOVER);
        assertThat(aceCard.isAce()).isTrue();

        final Card notAceCard = new Card(CardLetter.TWO, CardSuit.CLOVER);
        assertThat(notAceCard.isAce()).isFalse();
    }

    @Test
    @DisplayName("hashCode, equals 오버라이드 테스트")
    void hashCodeEqualsTest() {
        final Card card = new Card(CardLetter.ACE, CardSuit.CLOVER);
        final Card sameCard = new Card(CardLetter.ACE, CardSuit.CLOVER);
        assertThat(card).isEqualTo(sameCard);
        assertThat(card.hashCode()).isEqualTo(sameCard.hashCode());
    }

    @Test
    @DisplayName("getter에 대한 테스트")
    void getterTest() {
        final Card card = new Card(CardLetter.ACE, CardSuit.CLOVER);
        assertThat(card.getCardLetter()).isEqualTo(CardLetter.ACE);
        assertThat(card.getCardSuit()).isEqualTo(CardSuit.CLOVER);
    }
}
