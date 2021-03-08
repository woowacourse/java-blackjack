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
    private static final List<Card> CARDS_SCORE_21 = Arrays.asList(
            new Card(Symbol.ACE, Shape.HEART),
            new Card(Symbol.KING, Shape.HEART),
            new Card(Symbol.TEN, Shape.HEART)
    );
    private static final List<Card> CARDS_BUST = Arrays.asList(
            new Card(Symbol.JACK, Shape.HEART),
            new Card(Symbol.TEN, Shape.HEART),
            new Card(Symbol.TWO, Shape.HEART)
    );
    private static final List<Card> CARDS_BLACKJACK = Arrays.asList(
            new Card(Symbol.JACK, Shape.HEART),
            new Card(Symbol.ACE, Shape.DIAMOND)
    );
    private static final List<Card> CARDS_WITH_THREE_ACE = Arrays.asList(
            new Card(Symbol.ACE, Shape.CLOVER),
            new Card(Symbol.ACE, Shape.DIAMOND),
            new Card(Symbol.ACE, Shape.HEART)
    );

    private static Stream<Arguments> getCardsWithAceBonusScore() {
        return Stream.of(Arguments.of(Arrays.asList(
                new Card(Symbol.ACE, Shape.CLOVER),
                new Card(Symbol.JACK, Shape.DIAMOND),
                new Card(Symbol.FIVE, Shape.HEART)), 16),
                Arguments.of(Arrays.asList(
                        new Card(Symbol.EIGHT, Shape.CLOVER),
                        new Card(Symbol.NINE, Shape.CLOVER),
                        new Card(Symbol.ACE, Shape.CLOVER)), 18),
                Arguments.of(Arrays.asList(
                        new Card(Symbol.ACE, Shape.CLOVER),
                        new Card(Symbol.NINE, Shape.CLOVER),
                        new Card(Symbol.EIGHT, Shape.CLOVER)), 18));
    }

    @DisplayName("Ace를 11로 적용해서 계산했을 때 21을 넘면 Ace를 1로 환산한다.")
    @ParameterizedTest
    @MethodSource("getCardsWithAceBonusScore")
    void calculateScoreWhenAceIsMaximum(List<Card> cardList, int expectedScore) {
        Cards cards = new Cards(cardList);

        int score = cards.calculateFinalScore();

        assertThat(score).isEqualTo(expectedScore);
    }

    @DisplayName("ACE를 11로 적용해서 계산했을 때 21을 넘지 않으면 11로 환산한다.")
    @Test
    void calculateScoreWhenAceIsMinimum() {
        List<Card> cardList = Arrays.asList(new Card(Symbol.ACE, Shape.CLOVER), new Card(Symbol.JACK, Shape.DIAMOND));
        Cards cards = new Cards(cardList);

        int score = cards.calculateFinalScore();

        assertThat(score).isEqualTo(21);
    }

    @DisplayName("Cards 생성시 주어지는 Card가 중복되면 안 된다")
    @Test
    void checkDuplication() {
        Card firstCard = new Card(Symbol.ACE, Shape.CLOVER);
        Card secondCard = new Card(Symbol.ACE, Shape.CLOVER);

        assertThatCode(() -> new Cards(Arrays.asList(firstCard, secondCard)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 카드는 보유할 수 없습니다.");
    }

    @DisplayName("카드의 점수를 확인한다")
    @Test
    void calculateScore() {
        Cards cards = new Cards(CARDS_SCORE_21);

        int score = cards.calculateFinalScore();

        assertThat(score).isEqualTo(21);
    }

    @DisplayName("Ace가 카드에 여러 개 존재하는 경우에도 에이스 보너스 점수를 고려해 계산한다.")
    @Test
    void calculateScoreWhenMultipleAce() {
        Cards cards = new Cards(CARDS_WITH_THREE_ACE);

        int score = cards.calculateFinalScore();

        assertThat(score).isEqualTo(13);
    }

    @DisplayName("ace를 11이 아닌 1로 계산한 최소 점수를 반환한다")
    @Test
    void calculateMinimumScoreTotal() {
        List<Card> cardList = Arrays.asList(new Card(Symbol.QUEEN, Shape.CLOVER), new Card(Symbol.ACE, Shape.DIAMOND));
        Cards cards = new Cards(cardList);

        int minimumScore = cards.calculateScoreWhenAceIsMinimum();

        assertThat(minimumScore).isEqualTo(11);
    }

    @DisplayName("카드를 1장 뽑아 추가할 때 중복이 존재하는 경우 예외 발생")
    @Test
    void addDuplicationCard() {
        Cards cards = new Cards();
        Card duplicatedCard = new Card(Symbol.ACE, Shape.HEART);
        cards.add(new Card(Symbol.ACE, Shape.HEART));

        assertThatCode(() -> cards.add(duplicatedCard))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 카드는 보유할 수 없습니다.");
    }

    @DisplayName("카드를 여러 장 뽑아 추가할 때 중복이 존재하는 경우 예외 발생")
    @Test
    void addDuplicationCards() {
        Cards targetCards = new Cards(CardsGenerator.generateShuffledCards());
        Cards cards = new Cards(CARDS_SCORE_21);

        assertThatCode(() -> targetCards.addAll(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 카드는 보유할 수 없습니다.");
    }

    @DisplayName("카드가 2장이고 두 장의 합이 21이면 블랙잭이다.")
    @Test
    void isBlackJack_True() {
        Cards cards = new Cards(CARDS_BLACKJACK);

        assertThat(cards.isBlackJack()).isTrue();
    }

    @DisplayName("카드의 합이 21이더라도 2장이 아니면 블랙잭이 아니다.")
    @Test
    void isBlackJack_False() {
        Cards cards = new Cards(CARDS_SCORE_21);

        assertThat(cards.isBlackJack()).isFalse();
    }

    @DisplayName("카드의 합이 21을 초과하면 버스트이다")
    @Test
    void isBust_True() {
        Cards cards = new Cards(CARDS_BUST);

        assertThat(cards.isBust()).isTrue();
    }

    @DisplayName("카드의 합이 21을 초과하지 않으면 버스트가 아니다")
    @Test
    void isBust_False() {
        Cards cards = new Cards(CARDS_SCORE_21);

        assertThat(cards.isBust()).isFalse();
    }
}
