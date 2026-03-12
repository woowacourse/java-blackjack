package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @Test
    @DisplayName("카드 한 장 생성 확인")
    void createCardTest() {
        // given
        Figure spade = Figure.SPADE;
        Number five = Number.FIVE;
        // when
        Card card = new Card(spade, five);
        // then
        assertThat(card.getCardName()).isEqualTo("5스페이드");
    }

    @Test
    @DisplayName("Figure와 Number가 동시에 같으면 Card이다.")
    void cardIsSameTest() {
        // given
        Card card = new Card(Figure.SPADE, Number.ACE);
        Card card2 = new Card(Figure.SPADE, Number.ACE);

        // when & then
        assertThat(card.equals(card2)).isTrue();
    }

    @Test
    @DisplayName("Figure와 Number 중 다른 게 포함하면 Card는 다르다.")
    void cardIsNotSameTest() {
        // given
        Card card = new Card(Figure.SPADE, Number.ACE);
        Card card2 = new Card(Figure.SPADE, Number.FIVE);
        Card card3 = new Card(Figure.HEART, Number.FIVE);

        // when & then
        assertThat(card.equals(card2)).isFalse();
        assertThat(card2.equals(card3)).isFalse();
        assertThat(card3.equals(card)).isFalse();
    }

    @Test
    @DisplayName("Card가 Ace라면 참을 반환한다.")
    void getTrueIfCardIsAce() {
        // given
        Card card = new Card(Figure.SPADE, Number.ACE);

        // when & then
        assertThat(card.isAce()).isTrue();
    }

    @Test
    @DisplayName("Card가 Ace가 아니라면 거짓을 반환한다.")
    void getFalseIfCardIsAce() {
        // given
        Card card = new Card(Figure.SPADE, Number.KING);

        // when & then
        assertThat(card.isAce()).isFalse();
    }


}
