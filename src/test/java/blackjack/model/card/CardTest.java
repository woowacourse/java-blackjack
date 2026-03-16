package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("해당되는 랭크와 슈트를 가진 공개된 카드를 생성한다.")
    void createOpenedCard() {
        assertThat(Card.createOpenedCard(Rank.ACE, Suit.CLOVER).toString())
                .isEqualTo("A클로버");
    }

    @Test
    @DisplayName("공개된 카드를 뒤집으면, isOpened가 false가 된다.")
    void close_opened() {
        // given
        Card card = Card.createOpenedCard(Rank.ACE, Suit.CLOVER);

        // when
        Card closed = card.close();

        // then
        assertThat(closed.isOpened()).isFalse();
    }

    @Test
    @DisplayName("이미 뎦혀진 카드를 뒤집으면, 예외가 발생한다.")
    void close_closed() {
        // given
        Card card = Card.createOpenedCard(Rank.ACE, Suit.CLOVER);
        Card closed = card.close();

        // when & then
        assertThatThrownBy(() -> closed.close())
                .isInstanceOf(IllegalStateException.class)
                        .hasMessage("이미 덮혀져 있습니다.");
    }

    @Test
    @DisplayName("카드가 에이스이면 true를 반환한다.")
    void isAceWithAce() {
        assertThat(
                Card.createOpenedCard(Rank.ACE, Suit.CLOVER)
                        .isAce()
        ).isTrue();
    }

    @Test
    @DisplayName("카드가 에이스가 아니면 false를 반환한다.")
    void isAceWithNotAce() {
        assertThat(
                Card.createOpenedCard(Rank.TEN, Suit.CLOVER)
                        .isAce()
        ).isFalse();
    }


    @Test
    @DisplayName("카드의 기본 점수를 반환한다.")
    void getDefaultScore() {
        // given
        Card card = Card.createOpenedCard(Rank.TEN, Suit.CLOVER);

        // when
        int score = card.getDefaultScore();

        // then
        assertThat(score).isEqualTo(10);
    }


    @Test
    @DisplayName("카드가 공개돼 있으면 true를 반환한다.")
    void isOpenedTrue() {
        // given
        Card card = Card.createOpenedCard(Rank.ACE, Suit.CLOVER);

        // when & then
        assertThat(card.isOpened()).isTrue();
    }

    @Test
    @DisplayName("카드가 공개돼 있지 않으면 false를 반환한다.")
    void isOpenedFalse() {
        // given
        Card card = Card.createOpenedCard(Rank.ACE, Suit.CLOVER);
        Card closed = card.close();

        // when & then
        assertThat(closed.isOpened()).isFalse();
    }
}