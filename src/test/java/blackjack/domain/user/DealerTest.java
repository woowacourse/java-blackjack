package blackjack.domain.user;

import blackjack.domain.GameResult;
import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class DealerTest {

    private final Card firstCard = new Card(CardShape.SPADE, CardNumber.ACE);
    private final Card secondCard = new Card(CardShape.CLOVER, CardNumber.EIGHT);
    private CardGroup initialGroup;

    @BeforeEach
    void setUp() {
        initialGroup = new CardGroup(firstCard, secondCard);
    }

    @Test
    @DisplayName("딜러 초기화 테스트")
    void initTest() {
        User dealer = new Dealer(initialGroup);

        assertSoftly(softly -> {
            softly.assertThat(dealer.getName()).isEqualTo(Dealer.DEALER_NAME);
            softly.assertThat(dealer.getHandholdingCards()).containsExactly(firstCard, secondCard);
        });
    }

    @Test
    @DisplayName("첫 패 확인 테스트")
    void getInitialStatus() {
        User dealer = new Dealer(initialGroup);

        assertThat(dealer.getInitialHoldingCards()).containsExactly(firstCard);
    }

    @Test
    @DisplayName("딜러가 카드를 추가로 뽑아야 하는지 확인 테스트")
    void isOverDrawTest() {
        final Dealer dealer = new Dealer(initialGroup);

        boolean isOverDraw = dealer.isOverDraw();

        assertThat(isOverDraw).isTrue();
    }

    // TODO: 나머지 경우(딜러가 다른경우)도 nested test 찾아보고 하위로 같이 만들기
    @ParameterizedTest(name = "결과 : {1}")
    @DisplayName("플레이어 점수로 블랙젝 결과 테스트")
    @MethodSource("providePlayerScoresAndExpectedResults")
    void judgePlayerResultTest(Score playerScore, GameResult expectedResult) {
        final Dealer dealer = new Dealer(initialGroup);

        GameResult actualResult = dealer.judgePlayerGameResult(playerScore);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> providePlayerScoresAndExpectedResults() {
        return Stream.of(
                Arguments.of(new Score(17, 2), GameResult.LOSE),
                Arguments.of(new Score(19, 2), GameResult.TIE),
                Arguments.of(new Score(20, 2), GameResult.WIN),
                Arguments.of(new Score(23, 3), GameResult.LOSE)
        );
    }

}
