package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드 생성 테스트")
    void cardTest() {
        // given
        Rank ace = Rank.ACE;
        Suit clover = Suit.CLOVER;

        // when & then
        assertThatCode(() -> Card.openedCard(ace, clover)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("뒤집기 테스트")
    void flipTest() {
        // given
        Card card = Card.openedCard(Rank.ACE, Suit.CLOVER);

        // when
        card.flip();

        // then
        assertThat(card.isOpened()).isFalse();
    }

    @Test
    @DisplayName("카드 기본 점수 가져오기")
    void getCardDefaultScoreTest() {
        // given
        Card card = Card.openedCard(Rank.TEN, Suit.CLOVER);

        // when
        int score = card.getDefaultScore();

        // then
        assertThat(score).isEqualTo(10);
    }

    @Test
    @DisplayName("에이스 판별 테스트")
    void isAceTest() {
        // given
        Card card1 = Card.openedCard(Rank.ACE, Suit.CLOVER);
        Card card2 = Card.openedCard(Rank.TEN, Suit.CLOVER);

        // when & then
        assertThat(card1.isAce()).isTrue();
        assertThat(card2.isAce()).isFalse();
    }

    @Test
    @DisplayName("오픈 판별 테스트")
    void isOpenedCardTest() {
        // given
        Card card1 = Card.openedCard(Rank.ACE, Suit.CLOVER);
        Card card2 = Card.openedCard(Rank.ACE, Suit.CLOVER);
        card2.flip();

        // when & then
        assertThat(card1.isOpened()).isTrue();
        assertThat(card2.isOpened()).isFalse();
    }
}