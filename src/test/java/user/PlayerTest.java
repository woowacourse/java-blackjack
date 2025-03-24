package user;

import card.Card;
import card.CardDeck;
import card.CardRank;
import card.CardShape;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {
    @DisplayName("일반 유저는 자신이 가진 모든 카드를 공개해야 한다")
    @Test
    void test() {
        // given
        String name = "수양";
        Player user = new Player(name);
        CardDeck cardDeck = new CardDeck();
        user.drawCard(cardDeck.drawCard());
        user.drawCard(cardDeck.drawCard());

        // when
        List<Card> cards = user.openInitialCard();

        // then
        Assertions.assertThat(cards).hasSize(2);
    }

    @DisplayName("일반 유저는 'dealer', '딜러' 이름을 사용할 수 없다")
    @ParameterizedTest
    @ValueSource(strings = {"dealer", "딜러"})
    void test2(String name) {
        Assertions.assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("dealer 혹은 딜러는 이름으로 사용할 수 없습니다.");
    }

    @DisplayName("일반 유저는 카드의 총 합이 21 미만일 때만 카드를 뽑을 수 있다")
    @ParameterizedTest
    @MethodSource("DrawableValue")
    void test3(List<CardRank> ranks, boolean expected) {
        // given
        Player player = new Player("Test");
        for (CardRank rank : ranks) {
            player.drawCard(new Card(CardShape.CLOVER, rank));
        }

        // when
        boolean drawable = player.isDrawable();

        // then
        Assertions.assertThat(drawable).isEqualTo(expected);
    }

    private static Stream<Arguments> DrawableValue() {
        return Stream.of(
                Arguments.arguments(List.of(CardRank.TEN, CardRank.ACE), false),
                Arguments.arguments(List.of(CardRank.TEN), true)
        );
    }

    @DisplayName("플레이어는 플레이어다.")
    @Test
    void test4() {
        // given
        Player player = new Player("Test");

        // when
        boolean isPlayer = player.isPlayer();

        // then
        Assertions.assertThat(isPlayer).isTrue();
    }
}