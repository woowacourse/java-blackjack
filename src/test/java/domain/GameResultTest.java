package domain;

import static domain.card.Rank.EIGHT;
import static domain.card.Rank.FIVE;
import static domain.card.Rank.JACK;
import static domain.card.Rank.KING;
import static domain.card.Rank.NINE;
import static domain.card.Rank.SEVEN;
import static domain.card.Rank.SIX;
import static domain.card.Rank.TEN;
import static domain.card.Suit.CLOVER;
import static domain.card.Suit.DIAMOND;
import static domain.card.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardHand;
import domain.card.Deck;
import domain.game.GameResult;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.shuffler.RandomShuffler;
import fixture.CardFixture;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultTest {
    private final Deck deck = new Deck(new RandomShuffler());
    private final CardHand bustCardHand = new CardHand(
            Set.of(CardFixture.of(TEN, CLOVER), CardFixture.of(TEN, HEART), CardFixture.of(TEN, DIAMOND)));
    private final CardHand bustCardHand2 = new CardHand(
            Set.of(CardFixture.of(KING, CLOVER), CardFixture.of(KING, HEART), CardFixture.of(KING, DIAMOND)));
    private final CardHand noBustCardHand = new CardHand(
            Set.of(CardFixture.of(FIVE, CLOVER), CardFixture.of(FIVE, HEART)));

    @ParameterizedTest
    @MethodSource("cardArguments")
    @DisplayName("딜러와 플레이어가 모두 21점 이하일 때 딜러의 승패 여부를 판단할 수 있다.")
    void testCalculateDealerGameResult(Set<Card> dealerCards, Set<Card> playerCards, GameResult expectedResult) {
        // given
        CardHand dealerCardHand = new CardHand(dealerCards);
        CardHand playerCardHand = new CardHand(playerCards);
        Dealer dealer = new Dealer(deck, dealerCardHand);
        Player player = new Player("pobi", playerCardHand);
        // when
        GameResult gameResult = GameResult.calculateDealerGameResult(dealer, player);
        // then
        assertThat(gameResult).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("플레이어만 버스트가 발생할 경우 딜러는 승리한다.")
    void testWhenPlayerBust() {
        // given
        Dealer dealer = new Dealer(deck, noBustCardHand);
        Player player = new Player("pobi", bustCardHand);
        // when
        GameResult gameResult = GameResult.calculateDealerGameResult(dealer, player);
        // then
        assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("딜러만 버스트가 발생할 경우 딜러는 패배한다.")
    void testWhenDealerBust() {
        // given
        Dealer dealer = new Dealer(deck, bustCardHand);
        Player player = new Player("pobi", noBustCardHand);
        // when
        GameResult gameResult = GameResult.calculateDealerGameResult(dealer, player);
        // then
        assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 버스트가 발생할 경우 딜러가 승리한다.")
    void testWhenBothBust() {
        // given
        Dealer dealer = new Dealer(deck, bustCardHand);
        Player player = new Player("pobi", bustCardHand2);
        // when
        GameResult gameResult = GameResult.calculateDealerGameResult(dealer, player);
        // then
        assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("딜러의 승리 여부를 입력하면 플레이어의 승리 여부를 반환한다.")
    void test() {
        // given & when
        GameResult gameResult = GameResult.getOppositeResult(GameResult.WIN);
        // then
        assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    private static Stream<Arguments> cardArguments() {
        return Stream.of(
                Arguments.arguments(Set.of(CardFixture.of(TEN, CLOVER), CardFixture.of(NINE, CLOVER)),
                        Set.of(CardFixture.of(EIGHT, CLOVER), CardFixture.of(SEVEN, CLOVER)),
                        GameResult.WIN),
                Arguments.arguments(Set.of(CardFixture.of(SIX, CLOVER), CardFixture.of(KING, CLOVER)),
                        Set.of(CardFixture.of(NINE, CLOVER), CardFixture.of(JACK, CLOVER)),
                        GameResult.LOSE),
                Arguments.arguments(Set.of(CardFixture.of(TEN, CLOVER), CardFixture.of(KING, CLOVER)),
                        Set.of(CardFixture.of(JACK, CLOVER), CardFixture.of(KING, DIAMOND)),
                        GameResult.PUSH));
    }
}
