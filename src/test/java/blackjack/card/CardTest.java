package blackjack.card;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
    @DisplayName("카드 생성 테스트")
    @Test
    public void createCardTest() {
        Card card = new Card(Denomination.JACK, Suit.CLOVER);
        assertThat(card).isEqualTo(new Card(Denomination.JACK, Suit.CLOVER));
    }

    @DisplayName("카드의 에이스 여부 검사 테스트")
    @Test
    public void isAceTest() {
        Card card = new Card(Denomination.ACE, Suit.HEART);
        Card card2 = new Card(Denomination.JACK, Suit.HEART);
        assertThat(card.isAce()).isTrue();
        assertThat(card2.isAce()).isFalse();
    }

    @DisplayName("카드 정보 getter 테스트")
    @Test
    public void getCardInformationTest() {
        Card card = new Card(Denomination.THREE, Suit.DIAMOND);
        assertThat(card.getCardInformation()).isEqualTo("3다이아몬드");
    }

    @DisplayName("카드 점수 getter 테스트")
    @Test
    public void getScoreTest() {
        Card card = new Card(Denomination.THREE, Suit.SPADE);
        assertThat(card.getScore()).isEqualTo(3);
    }
}
