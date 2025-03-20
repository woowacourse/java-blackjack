package blackjack.object.gambler;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.object.card.Card;
import blackjack.object.card.CardShape;
import blackjack.object.card.CardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

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

    @DisplayName("처음 카드를 받은 후 플레이어는 두 장의 카드만 오픈한다.")
    @Test
    void getInitialCardsTest() {
        // given
        Player player = new Player(new Name("라젤"));
        Card card1 = new Card(CardShape.CLOVER, CardType.TEN);
        Card card2 = new Card(CardShape.HEART, CardType.EIGHT);
        player.addCard(card1);
        player.addCard(card2);

        // when
        List<Card> result = player.getInitialCards();

        // then
        assertThat(result).hasSize(2);
        assertThat(result.getFirst()).isEqualTo(card1);
        assertThat(result.getLast()).isEqualTo(card2);
    }
}
