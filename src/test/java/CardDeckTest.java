import domain.CardDeck;
import domain.CardNumber;
import domain.TrumpCardManager;
import domain.CardShape;
import domain.TrumpCard;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CardDeckTest {
    @BeforeEach
    public void setUp() {
        TrumpCardManager.bin();
        TrumpCardManager.initCache();
    }

    @DisplayName("카드 배부 시 52장의 카드 덱에서 카드를 뽑아서 배부한다.")
    @Test
    void test2() {
        // given
        int originCardDeckSize = TrumpCardManager.getCardDeck().size();

        // when
        TrumpCardManager.drawCard();
        int afterDrawDeckSize = TrumpCardManager.getCardDeck().size();

        // then
        Assertions.assertThat(originCardDeckSize - 1).isEqualTo(afterDrawDeckSize);
    }

    @DisplayName("카드가 다 떨어지면 예외를 발생한다")
    @Test
    void test3() {
        // given
        int count = 0;
        for (int i = 0; i < 52; i++) {
            TrumpCardManager.drawCard();
            count++;
        }

        // when & then
        Assertions.assertThatThrownBy(TrumpCardManager::drawCard)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드가 다 떨어졌습니다");
    }

    @DisplayName("카드의 합이 21 초과 시 패배한다")
    @Test
    void test4() {
        //given
        TrumpCard trumpCard1 = new TrumpCard(CardShape.CLOVER, CardNumber.K);
        TrumpCard trumpCard2 = new TrumpCard(CardShape.CLOVER, CardNumber.J);
        TrumpCard trumpCard3 = new TrumpCard(CardShape.CLOVER, CardNumber.FIVE);
        CardDeck cardDeck = new CardDeck();

        cardDeck.addTrumpCard(trumpCard1);
        cardDeck.addTrumpCard(trumpCard2);
        cardDeck.addTrumpCard(trumpCard3);

        // when
        boolean isOver = cardDeck.checkOverScore();

        // then
        Assertions.assertThat(isOver).isTrue();
    }

    @DisplayName("카드에 ACE가 있다면 가능한 max값을 계산하여 판단한다")
    @ParameterizedTest
    @MethodSource("hasAce")
    void test5(List<TrumpCard> cards, boolean isExpectedOver) {
        //given
        CardDeck cardDeck = new CardDeck();
        for (TrumpCard card : cards) {
            cardDeck.addTrumpCard(card);
        }

        // when & then
        boolean isRealOver = cardDeck.checkOverScore();
        Assertions.assertThat(isRealOver).isEqualTo(isExpectedOver);
    }

    private static Stream<Arguments> hasAce() {
        return Stream.of(
                Arguments.arguments(List.of(
                        new TrumpCard(CardShape.DIA, CardNumber.ACE),
                        new TrumpCard(CardShape.CLOVER, CardNumber.ACE),
                        new TrumpCard(CardShape.HEART, CardNumber.ACE)), false),
                Arguments.arguments(List.of(
                        new TrumpCard(CardShape.DIA, CardNumber.K),
                        new TrumpCard(CardShape.CLOVER, CardNumber.J),
                        new TrumpCard(CardShape.HEART, CardNumber.ACE)), false),
                Arguments.arguments(List.of(
                        new TrumpCard(CardShape.DIA, CardNumber.ACE),
                        new TrumpCard(CardShape.CLOVER, CardNumber.Q)), false)
        );
    }

    @DisplayName("카드의 총합을 계산한다.")
    @ParameterizedTest
    @MethodSource("cardDeck")
    void test6(List<TrumpCard> cards, int expectedSum) {
        // given
        CardDeck cardDeck = new CardDeck();
        for (TrumpCard card : cards) {
            cardDeck.addTrumpCard(card);
        }

        // when
        int realSum = cardDeck.calculateScore();

        // then
        Assertions.assertThat(realSum).isEqualTo(expectedSum);
    }

    private static Stream<Arguments> cardDeck() {
        return Stream.of(
                Arguments.arguments(List.of(
                        new TrumpCard(CardShape.DIA, CardNumber.ACE),
                        new TrumpCard(CardShape.CLOVER, CardNumber.ACE),
                        new TrumpCard(CardShape.HEART, CardNumber.ACE)), 13),
                Arguments.arguments(List.of(
                        new TrumpCard(CardShape.DIA, CardNumber.K),
                        new TrumpCard(CardShape.CLOVER, CardNumber.J),
                        new TrumpCard(CardShape.HEART, CardNumber.ACE)), 21),
                Arguments.arguments(List.of(
                        new TrumpCard(CardShape.HEART, CardNumber.ACE),
                        new TrumpCard(CardShape.CLOVER, CardNumber.Q)), 21),
                Arguments.arguments(List.of(
                        new TrumpCard(CardShape.DIA, CardNumber.J),
                        new TrumpCard(CardShape.CLOVER, CardNumber.Q)), 20),
                Arguments.arguments(List.of(
                        new TrumpCard(CardShape.SPADE, CardNumber.K),
                        new TrumpCard(CardShape.CLOVER, CardNumber.J),
                        new TrumpCard(CardShape.CLOVER, CardNumber.Q)), 30)
        );
    }

    @DisplayName("블랙잭인지 아닌지 판단한다")
    @Test
    void test7() {
        // given
        TrumpCard trumpCard1 = new TrumpCard(CardShape.CLOVER, CardNumber.ACE);
        TrumpCard trumpCard2 = new TrumpCard(CardShape.CLOVER, CardNumber.J);
        CardDeck cardDeck = new CardDeck();

        cardDeck.addTrumpCard(trumpCard1);
        cardDeck.addTrumpCard(trumpCard2);

        // when
        boolean isBlackjack = cardDeck.isBlackjack();

        // then
        Assertions.assertThat(isBlackjack).isEqualTo(true);
    }
}
