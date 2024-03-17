package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("카드는 무늬와 숫자를 가진다")
    @Test
    public void create() {
        Card card = CardFixture.fromSuitCloverWith(Denomination.ACE);

        assertThat(card.getSuit()).isEqualTo(Suit.CLOVER);
        assertThat(card.getDenomination()).isEqualTo(Denomination.ACE);
    }

    @DisplayName("카드의 점수를 반환한다")
    @Test
    public void getScore() {
        Card card = CardFixture.fromSuitCloverWith(Denomination.TWO);

        assertThat(card.getScore()).isEqualTo(Score.from(2));
    }

    @DisplayName("카드가 에이스면 true를 반환한다")
    @Test
    public void isAceTrue() {
        Card card = CardFixture.fromSuitCloverWith(Denomination.ACE);

        assertThat(card.isAce()).isTrue();
    }

    @DisplayName("카드가 에어스가 아니면 false를 반환한다")
    @Test
    public void isAceFalse() {
        Card card = CardFixture.fromSuitCloverWith(Denomination.TWO);

        assertThat(card.isAce()).isFalse();
    }
}
