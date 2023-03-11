package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSuit;
import blackjack.domain.user.exception.ReservedPlayerNameException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayerTest {

    @Test
    @DisplayName("이름을 받아 플레이어를 생성한다")
    void createPlayerTest() {
        Player player = new Player("boxster");

        assertThat(player.getName()).isEqualTo("boxster");
    }

    @Test
    @DisplayName("카드를 받아 플레이어 카드에 추가한다")
    void addPlayerCardsTest() {
        Player player = new Player("jamie");
        Card card = new Card(CardSuit.HEART, CardNumber.ACE);

        player.addCard(card);

        assertThat(player.getCards()).contains(card);
    }

    @ParameterizedTest
    @MethodSource("playerIsDrawableTestSource")
    @DisplayName("플레이어는 점수 상태에 따라 카드를 뽑을 수 있는지 결정된다")
    void isDrawableTest(List<Card> cards, boolean expect) {
        Player player = new Player("박스터");
        player.addCards(cards);

        boolean drawable = player.isDrawable();

        assertThat(drawable).isEqualTo(expect);
    }

    static Stream<Arguments> playerIsDrawableTestSource() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardSuit.HEART, CardNumber.TEN),
                                new Card(CardSuit.HEART, CardNumber.SIX)
                        ), true),
                Arguments.of(
                        List.of(
                                new Card(CardSuit.HEART, CardNumber.TEN),
                                new Card(CardSuit.HEART, CardNumber.SEVEN)
                        ), true),
                Arguments.of(
                        List.of(
                                new Card(CardSuit.HEART, CardNumber.TEN),
                                new Card(CardSuit.SPADE, CardNumber.TEN),
                                new Card(CardSuit.SPADE, CardNumber.TWO)
                        ), false)
        );
    }

    @Test
    @DisplayName("이름이 딜러인 경우 예외를 발생시킨다")
    void reservedWordCreateException() {
        assertThatThrownBy(() -> new Player("딜러"))
                .isInstanceOf(ReservedPlayerNameException.class);
    }
}
