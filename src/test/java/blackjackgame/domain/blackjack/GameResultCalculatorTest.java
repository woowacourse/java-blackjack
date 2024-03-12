package blackjackgame.domain.blackjack;

import static blackjackgame.domain.blackjack.GameResult.BIG_WIN;
import static blackjackgame.domain.blackjack.GameResult.LOSE;
import static blackjackgame.domain.blackjack.GameResult.WIN;
import static blackjackgame.domain.card.CardName.ACE;
import static blackjackgame.domain.card.CardName.JACK;
import static blackjackgame.domain.card.CardName.NINE;
import static blackjackgame.domain.card.CardName.QUEEN;
import static blackjackgame.domain.card.CardName.TWO;
import static blackjackgame.domain.card.CardType.SPADE;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.gamers.CardHolder;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultCalculatorTest {
    private static final List<Card> blackjackCards = List.of(new Card(JACK, SPADE), new Card(ACE, SPADE));
    private static final List<Card> normalCards = List.of(new Card(JACK, SPADE), new Card(QUEEN, SPADE));
    private static final List<Card> normalSmallCards = List.of(new Card(JACK, SPADE), new Card(NINE, SPADE));
    private static final List<Card> bustCards = List.of(new Card(JACK, SPADE), new Card(QUEEN, SPADE), new Card(TWO, SPADE));

    private static Stream<List<Card>> allCards() {
        return Stream.of(blackjackCards, normalCards, bustCards);
    }

    private static Stream<List<Card>> normalAndBustCards() {
        return Stream.of(normalCards, bustCards);
    }

    @Test
    @DisplayName("플레이어가 BLACKJACK이고 딜러가 BLACKJACK이면 결과가 WIN이다.")
    void gameResultWinWhenPlayerBlackjackDealerBlackjackTest() {
        CardHolder baseCardHolder = new CardHolder("게이머1", new HoldingCards(blackjackCards));
        CardHolder otherCardHolder = new CardHolder("게이머2", new HoldingCards(blackjackCards));

        GameResult gameResult = GameResultCalculator.calculate(baseCardHolder, otherCardHolder);

        Assertions.assertThat(gameResult).isEqualTo(WIN);
    }

    @ParameterizedTest
    @MethodSource("normalAndBustCards")
    @DisplayName("플레이어가 BLACKJACK이고 딜러가 BLACKJACK이 아니라면 결과가 BIG_WIN이다.")
    void gameResultBigWinWhenPlayerBlackjackDealerNotBlackjackTest(List<Card> otherCards) {
        CardHolder baseCardHolder = new CardHolder("게이머1", new HoldingCards(blackjackCards));
        CardHolder otherCardHolder = new CardHolder("게이머2", new HoldingCards(otherCards));

        GameResult gameResult = GameResultCalculator.calculate(baseCardHolder, otherCardHolder);

        Assertions.assertThat(gameResult).isEqualTo(BIG_WIN);
    }

    @ParameterizedTest
    @MethodSource("allCards")
    @DisplayName("플레이어가 BUST라면 결과가 LOSE다.")
    void gameResultLoseWhenPlayerBust(List<Card> otherCards) {
        CardHolder baseCardHolder = new CardHolder("게이머1", new HoldingCards(bustCards));
        CardHolder otherCardHolder = new CardHolder("게이머2", new HoldingCards(otherCards));

        GameResult gameResult = GameResultCalculator.calculate(baseCardHolder, otherCardHolder);

        Assertions.assertThat(gameResult).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("플레이어가 NORMAL이고 딜러가 BUST라면 결과가 WIN이다.")
    void gameResultWinWhenPlayerNormalDealerBust() {
        CardHolder baseCardHolder = new CardHolder("게이머1", new HoldingCards(normalCards));
        CardHolder otherCardHolder = new CardHolder("게이머2", new HoldingCards(bustCards));

        GameResult gameResult = GameResultCalculator.calculate(baseCardHolder, otherCardHolder);

        Assertions.assertThat(gameResult).isEqualTo(WIN);
    }

    @Test
    @DisplayName("플레이어가 NORMAL이고 플레이어의 합이 딜러의 합과 같으면 결과가 WIN이다.")
    void gameResultWinWhenPlayerSumEqualDealerSum() {
        CardHolder baseCardHolder = new CardHolder("게이머1", new HoldingCards(normalCards));
        CardHolder otherCardHolder = new CardHolder("게이머2", new HoldingCards(normalCards));

        GameResult gameResult = GameResultCalculator.calculate(baseCardHolder, otherCardHolder);

        Assertions.assertThat(gameResult).isEqualTo(WIN);
    }

    @Test
    @DisplayName("플레이어가 NORMAL이고 플레이어의 합이 딜러의 합보다 크면 결과가 WIN이다.")
    void gameResultWinWhenPlayerSumBiggerThanDealerSum() {
        CardHolder baseCardHolder = new CardHolder("게이머1", new HoldingCards(normalCards));
        CardHolder otherCardHolder = new CardHolder("게이머2", new HoldingCards(normalSmallCards));

        GameResult gameResult = GameResultCalculator.calculate(baseCardHolder, otherCardHolder);

        Assertions.assertThat(gameResult).isEqualTo(WIN);
    }

    @Test
    @DisplayName("플레이어가 NORMAL이고 플레이어의 합이 딜러의 합보다 작으면 결과가 LOSE다.")
    void gameResultWinWhenPlayerSumSmallerThanDealerSum() {
        CardHolder baseCardHolder = new CardHolder("게이머1", new HoldingCards(normalSmallCards));
        CardHolder otherCardHolder = new CardHolder("게이머2", new HoldingCards(normalCards));

        GameResult gameResult = GameResultCalculator.calculate(baseCardHolder, otherCardHolder);

        Assertions.assertThat(gameResult).isEqualTo(LOSE);
    }
}
