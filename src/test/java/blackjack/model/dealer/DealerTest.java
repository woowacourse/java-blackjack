package blackjack.model.dealer;

import blackjack.model.card.Card;
import blackjack.model.card.Denomination;
import blackjack.model.card.Suit;
import blackjack.model.cardgenerator.CardGenerator;
import blackjack.model.cardgenerator.SequentialCardGenerator;
import blackjack.model.player.Player;
import blackjack.model.referee.MatchResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @Test
    @DisplayName("딜러는 카드의 합이 17점 이상이 될 때까지 카드를 받는다")
    void canHitTest() {
        // given
        Card cardCreatedByDeal = new Card(Suit.HEART, Denomination.TWO);
        Dealer dealer = new Dealer(new SequentialCardGenerator(List.of(cardCreatedByDeal, cardCreatedByDeal)));

        // when
        Card firstCardCreatedByAction = new Card(Suit.HEART, Denomination.TEN);
        Card secondCardCreatedByAction = new Card(Suit.HEART, Denomination.FOUR);
        dealer.hitUntilEnd(new SequentialCardGenerator(List.of(firstCardCreatedByAction, secondCardCreatedByAction)));

        // then
        int cardsTotal = dealer.calculateCardsTotalScore().getValue();
        assertThat(cardsTotal).isGreaterThan(17);
    }

    @ParameterizedTest
    @MethodSource("provideCardsAndExpectedResult")
    @DisplayName("딜러와 플레이어의 카드 합을 비교하여 승패를 가린다")
    void determineOutcomeTest(List<Card> playerCards, List<Card> dealerCards, MatchResult expectedResult) {
        // given
        Player player = new Player("mia", new SequentialCardGenerator(playerCards));
        Dealer dealer = new Dealer(new SequentialCardGenerator(dealerCards));

        // when
        MatchResult actualResult = dealer.determinePlayerMatchResult(player);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideCardsAndExpectedResult() {
        return Stream.of(
                Arguments.of(
                        List.of(new Card(Suit.HEART, Denomination.TEN), new Card(Suit.HEART, Denomination.NINE)),
                        List.of(new Card(Suit.HEART, Denomination.TEN), new Card(Suit.HEART, Denomination.TEN)),
                        MatchResult.LOSE
                ),
                Arguments.of(
                        List.of(new Card(Suit.HEART, Denomination.TEN), new Card(Suit.HEART, Denomination.TEN)),
                        List.of(new Card(Suit.HEART, Denomination.TEN), new Card(Suit.HEART, Denomination.NINE)),
                        MatchResult.WIN
                ),
                Arguments.of(
                        List.of(new Card(Suit.HEART, Denomination.TEN), new Card(Suit.HEART, Denomination.TEN)),
                        List.of(new Card(Suit.HEART, Denomination.TEN), new Card(Suit.HEART, Denomination.TEN)),
                        MatchResult.PUSH
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideBustCardsAndExpectedResult")
    @DisplayName("버스트인 쪽이 패배한다. 플레이어와 딜러가 둘 다 버스트면 플레이어가 패배한다")
    public void ensurePlayerWinWhenBothBustTest(List<Card> playerCards, List<Card> dealerCards, MatchResult expectedResult) {
        // given
        Player player = createPlayer(playerCards);
        Dealer dealer = createDealer(dealerCards);

        // when
        MatchResult actualResult = dealer.determinePlayerMatchResult(player);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideBustCardsAndExpectedResult() {
        return Stream.of(
                Arguments.of(
                        List.of(new Card(Suit.HEART, Denomination.TEN), new Card(Suit.HEART, Denomination.TEN), new Card(Suit.HEART, Denomination.TEN)),
                        List.of(new Card(Suit.HEART, Denomination.TWO), new Card(Suit.HEART, Denomination.SIX), new Card(Suit.HEART, Denomination.TEN)),
                        MatchResult.LOSE
                ),
                Arguments.of(
                        List.of(new Card(Suit.HEART, Denomination.TWO), new Card(Suit.HEART, Denomination.TWO), new Card(Suit.HEART, Denomination.TWO)),
                        List.of(new Card(Suit.HEART, Denomination.EIGHT), new Card(Suit.HEART, Denomination.EIGHT), new Card(Suit.HEART, Denomination.TEN)),
                        MatchResult.WIN
                ),
                Arguments.of(
                        List.of(new Card(Suit.HEART, Denomination.TEN), new Card(Suit.HEART, Denomination.TEN), new Card(Suit.HEART, Denomination.TEN)),
                        List.of(new Card(Suit.HEART, Denomination.EIGHT), new Card(Suit.HEART, Denomination.EIGHT), new Card(Suit.HEART, Denomination.TEN)),
                        MatchResult.LOSE
                )
        );
    }

    private Player createPlayer(final List<Card> playerCards) {
        CardGenerator cardGenerator = new SequentialCardGenerator(playerCards);
        Player player = new Player("mia", cardGenerator);
        player.hit(cardGenerator);
        return player;
    }

    private Dealer createDealer(final List<Card> dealerCards) {
        CardGenerator cardGenerator = new SequentialCardGenerator(dealerCards);
        Dealer dealer = new Dealer(cardGenerator);
        dealer.hitUntilEnd(cardGenerator);
        return dealer;
    }
}
