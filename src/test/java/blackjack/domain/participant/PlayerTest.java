package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {

    @DisplayName("플레이어 이름이 공백이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void create_ThrowsException_WhenNicknameIsBlank(String invalidNickname) {
        assertThatThrownBy(() -> new Player(invalidNickname))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("초기 생성된 플레이어는 카드를 뽑을 수 있는 상태이다.")
    void isDrawable_True_Initially() {
        Player player = new Player("pobi");

        assertThat(player.isDrawable()).isTrue();
    }

    @Test
    @DisplayName("플레이어의 점수가 정확히 21점 이상이면 카드를 뽑을 수 없다.")
    void isDrawable_False_WhenScoreIs21OrMore() {
        Player player = new Player("pobi");
        player.receiveCard(new Card(Rank.TEN, Suit.SPADES));
        player.receiveCard(new Card(Rank.TEN, Suit.HEARTS));
        player.receiveCard(new Card(Rank.TWO, Suit.CLUBS));

        assertThat(player.isDrawable()).isFalse();
    }
}
