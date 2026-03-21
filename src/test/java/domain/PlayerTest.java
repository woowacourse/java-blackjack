package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    @DisplayName("플레이어는 초기 카드 내역 공개 시, 가지고 있는 카드 2장을 모두 보여준다.")
    void shouldReturnAllCardsForInitialVisibleCards() {
        // given
        Card card1 = new Card(CardShape.HEART, CardContents.FIVE);
        Card card2 = new Card(CardShape.HEART, CardContents.SIX);
        Player testPlayer = createPlayerWithCards(card1, card2);

        // when & then
        assertThat(testPlayer.getInitialVisibleCards())
                .containsExactly(card1, card2);
    }

    @Test
    @DisplayName("플레이어는 버스트 상태가 아니면 카드를 더 뽑을 수 있다.")
    void shouldReturnTrueWhenNotBust() {
        // given
        Player testPlayer = createPlayerWithCards(
                new Card(CardShape.HEART, CardContents.FIVE),
                new Card(CardShape.HEART, CardContents.SIX)
        );

        // when & then
        assertThat(testPlayer.isDrawable()).isTrue();
    }

    @Test
    @DisplayName("플레이어는 버스트 상태가 되면 카드를 더 이상 뽑을 수 없다.")
    void shouldReturnFalseWhenBust() {
        // given
        Player testPlayer = createPlayerWithCards(
                new Card(CardShape.HEART, CardContents.TEN),
                new Card(CardShape.CLOVER, CardContents.TEN),
                new Card(CardShape.SPADE, CardContents.TEN)
        );

        // when & then
        assertThat(testPlayer.isDrawable()).isFalse();
    }

    private Player createPlayerWithCards(Card... cards) {
        Player player = new Player(new Name("pobi"), new BetMoney(1000));
        for (Card card : cards) {
            player.addCard(card);
        }
        return player;
    }
}
