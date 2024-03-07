package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.Card;
import blackjack.domain.Card.Shape;
import blackjack.domain.Card.Value;
import blackjack.domain.Deck;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {

    private static final Name DEFAULT_NAME = new Name("name");

    @DisplayName("점수를 계산할 수 있다.")
    @ParameterizedTest
    @MethodSource("provideCardsAndScore")
    void calculateScoreTest(List<Card> cards, int expected) {
        Player player = new Player(cards, DEFAULT_NAME);

        assertThat(player.calculateScore()).isEqualTo(expected);
    }

    // TODO Fixture로 추출하기
    static Stream<Arguments> provideCardsAndScore() {
        return Stream.of(Arguments.of(List.of(new Card(Value.ACE, Shape.HEART), new Card(Value.KING, Shape.HEART)), 21),
                Arguments.of(List.of(new Card(Value.ACE, Shape.HEART), new Card(Value.ACE, Shape.SPADE)), 12),
                Arguments.of(List.of(new Card(Value.ACE, Shape.HEART), new Card(Value.KING, Shape.HEART),
                        new Card(Value.TWO, Shape.HEART)), 13),
                Arguments.of(List.of(new Card(Value.KING, Shape.HEART), new Card(Value.TWO, Shape.HEART)), 12));
    }

    @DisplayName("카드의 총 점수가 21을 넘지 않으면, 카드를 더 뽑을 수 있다")
    @Test
    void isDrawableTest_whenScoreIsUnder21_returnTrue() {
        List<Card> CardsScore21 = List.of(
                new Card(Value.JACK, Shape.HEART),
                new Card(Value.EIGHT, Shape.HEART),
                new Card(Value.THREE, Shape.HEART)
        );
        Player player = new Player(CardsScore21, DEFAULT_NAME);

        assertThat(player.isDrawable()).isTrue();
    }

    @DisplayName("카드의 총 점수가 21을 넘으면, 카드를 더 뽑을 수 없다")
    @Test
    void isDrawableTest_whenScoreIsOver21_returnFalse() {
        List<Card> CardsScore22 = List.of(
                new Card(Value.JACK, Shape.HEART),
                new Card(Value.SEVEN, Shape.HEART),
                new Card(Value.FIVE, Shape.HEART)
        );
        Player player = new Player(CardsScore22, DEFAULT_NAME);

        assertThat(player.isDrawable()).isFalse();
    }

    @DisplayName("게임을 시작할 때는 카드를 두 장 뽑는다.")
    @Test
    void drawStartCardsTest() {
        Player player = Player.from("name");
        Deck deck = Deck.createShuffledDeck();

        player.drawStartCards(deck);

        assertThat(player.getCards()).hasSize(2);
    }

    @DisplayName("이미 카드를 가지고 있는 경우, 시작 카드를 뽑을 수 없다.")
    @Test
    void drawStartCardsTest_whenAlreadyStarted_throwException() {
        Player player = Player.from("name");
        Deck deck = Deck.createShuffledDeck();
        player.drawStartCards(deck);

        assertThatThrownBy(() -> player.drawStartCards(deck))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 시작 카드를 뽑았습니다.");
    }

    @DisplayName("카드의 총 점수가 21을 넘지 않으면, 카드를 한 장 뽑는다")
    @Test
    void addTest_whenScoreIsUnder21() {
        Player player = new Player(List.of(
                new Card(Value.KING, Shape.HEART),
                new Card(Value.NINE, Shape.HEART),
                new Card(Value.TWO, Shape.HEART)
        ), DEFAULT_NAME);

        player.add(new Card(Value.ACE, Shape.HEART));

        assertThat(player.getCards()).containsExactly(
                new Card(Value.KING, Shape.HEART),
                new Card(Value.NINE, Shape.HEART),
                new Card(Value.TWO, Shape.HEART),
                new Card(Value.ACE, Shape.HEART)
        );
    }

    @DisplayName("카드의 총 점수가 21을 넘으면, 카드를 뽑을 때 예외가 발생한다.")
    @Test
    void addTest_whenScoreIsOver21_throwException() {
        Player player = new Player(List.of(
                new Card(Value.KING, Shape.HEART),
                new Card(Value.NINE, Shape.HEART),
                new Card(Value.THREE, Shape.HEART)
        ), DEFAULT_NAME);
        Card card = new Card(Value.ACE, Shape.HEART);

        assertThatThrownBy(() -> player.add(card))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("더 이상 카드를 추가할 수 없습니다.");
    }
}
