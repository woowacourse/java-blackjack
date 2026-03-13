package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;

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
    void flip() {
        // given
        Card card = Card.createOpenedCard(Rank.ACE, Suit.CLOVER);

        // when
        card.flip();

        // then
        assertThat(card.isOpened()).isFalse();
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
        card.flip();

        // when & then
        assertThat(card.isOpened()).isFalse();
    }
}