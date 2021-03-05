package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class CardsTest {

    private static Stream<Arguments> getCardOverMaximum() {
        return Stream.of(Arguments.of(Arrays.asList(new Card(Symbol.ACE, Shape.CLOVER),
                new Card(Symbol.JACK, Shape.DIAMOND),
                new Card(Symbol.FIVE, Shape.HEART)), 16),
                Arguments.of(Arrays.asList(new Card(Symbol.EIGHT, Shape.CLOVER),
                        new Card(Symbol.NINE, Shape.CLOVER), new Card(Symbol.ACE, Shape.CLOVER)), 18),
                Arguments.of(Arrays.asList(new Card(Symbol.ACE, Shape.CLOVER),
                        new Card(Symbol.NINE, Shape.CLOVER), new Card(Symbol.EIGHT, Shape.CLOVER)), 18));
    }

    @DisplayName("Cards 생성시 주어지는 Card가 중복되면 안 된다")
    @Test
    void checkDuplication() {
        Card firstCard = new Card(Symbol.ACE, Shape.CLOVER);
        Card secondCard = new Card(Symbol.ACE, Shape.CLOVER);

        assertThatCode(() -> {
            new Cards(Arrays.asList(firstCard, secondCard));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 카드는 보유할 수 없습니다.");
    }

    @DisplayName("카드의 점수를 확인한다")
    @Test
    void calculateScore() {
        List<Card> cardList = Arrays.asList(new Card(Symbol.EIGHT, Shape.CLOVER),
                new Card(Symbol.QUEEN, Shape.DIAMOND));
        Cards cards = new Cards(cardList);

        int score = cards.calculateFinalScore();

        assertThat(score).isEqualTo(18);
    }

    @DisplayName("ACE를 11로 적용해서 계산했을 때 21을 넘지 않으면 11로 환산한다.")
    @Test
    void calculateScoreWhenAceIsUnder() {
        List<Card> cardList = Arrays.asList(new Card(Symbol.ACE, Shape.CLOVER),
                new Card(Symbol.JACK, Shape.DIAMOND));
        Cards cards = new Cards(cardList);

        int score = cards.calculateFinalScore();

        assertThat(score).isEqualTo(21);
    }

    @DisplayName("ACE를 11로 적용해서 계산했을 때 21을 넘면 1로 환산한다.")
    @ParameterizedTest
    @MethodSource("getCardOverMaximum")
    void calculateScoreWhenAceIsOver(List<Card> cardList, int targetScore) {
        Cards cards = new Cards(cardList);

        int score = cards.calculateFinalScore();

        assertThat(score).isEqualTo(targetScore);
    }

    @DisplayName("Ace가 카드에 여러 개 존재하는 경우")
    @Test
    void calculateScoreWhenMultipleAce() {
        List<Card> cardList = Arrays.asList(
                new Card(Symbol.ACE, Shape.CLOVER),
                new Card(Symbol.ACE, Shape.DIAMOND),
                new Card(Symbol.ACE, Shape.HEART));
        Cards cards = new Cards(cardList);

        int score = cards.calculateFinalScore();

        assertThat(score).isEqualTo(13);
    }

    @DisplayName("ace를 11이 아닌 1로 계산한 최소 점수를 반환한다")
    @Test
    void calculateMinimumScoreTotal() {
        List<Card> cardList = Arrays.asList(
                new Card(Symbol.QUEEN, Shape.CLOVER),
                new Card(Symbol.ACE, Shape.DIAMOND));
        Cards cards = new Cards(cardList);

        int minimumScore = cards.calculateScoreWhenAceIsMinimum();

        assertThat(minimumScore).isEqualTo(11);
    }

    @DisplayName("카드를 1장 추가할 때 중복이 존재하는 경우 예외 발생")
    @Test
    void addDuplicationCard() {
        Cards cards = new Cards();
        cards.add(new Card(Symbol.ACE, Shape.HEART));

        assertThatCode(() -> {
            cards.add(new Card(Symbol.ACE, Shape.HEART));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 카드는 보유할 수 없습니다.");
    }

    @DisplayName("카드를 여러 장 추가할 때 중복이 존재하는 경우 예외 발생")
    @Test
    void addDuplicationCards() {
        Cards targetCards = new Cards(CardsGenerator.generateCards());
        Cards cards = new Cards(Arrays.asList(new Card(Symbol.ACE, Shape.CLOVER)));

        assertThatCode(() -> {
            targetCards.addAll(cards);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 카드는 보유할 수 없습니다.");
    }
}
