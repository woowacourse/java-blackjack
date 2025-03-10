package blackjack.domain.gambler;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayerTest {
    @DisplayName("이름을 통해 플레이어를 생성한다.")
    @Test
    void createTest() {
        Player player = new Player(new Name("라젤"));

        assertThat(player.getName()).isEqualTo(new Name("라젤"));
    }

    @DisplayName("패에 카드를 추가한다")
    @Test
    void addCardTest() {
        Player player = new Player(new Name("라젤"));
        Card card1 = new Card(CardShape.CLOVER, CardType.TEN);
        Card card2 = new Card(CardShape.HEART, CardType.EIGHT);

        player.addCard(card1);
        player.addCard(card2);

        assertThat(player.calculateScore()).isEqualTo(18);
    }

    @DisplayName("카드의 합이 특정 값 이하인지 확인한지 테스트")
    @CsvSource(value = {"21:True", "16:False"}, delimiterString = ":")
    @ParameterizedTest
    void isBelowTest(int criteria, boolean expected) {
        // given
        Player player = new Player(new Name("라젤"));
        Card card1 = new Card(CardShape.CLOVER, CardType.TEN);
        Card card2 = new Card(CardShape.HEART, CardType.EIGHT);

        player.addCard(card1);
        player.addCard(card2);

        // when
        boolean result = player.isScoreBelow(criteria);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
