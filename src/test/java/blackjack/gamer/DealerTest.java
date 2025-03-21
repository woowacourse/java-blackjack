package blackjack.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.CardDeck;
import blackjack.card.CardRank;
import blackjack.card.CardSymbol;
import blackjack.card.Hand;
import blackjack.result.ProfitResult;
import blackjack.state.started.finished.Stay;
import blackjack.state.started.running.Hit;
import fixture.BettingFixture;
import fixture.CardDeckFixture;
import fixture.NicknameFixture;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    public static Stream<Arguments> provideCardDeckAndExpected() {
        return Stream.of(
                Arguments.of(
                        CardDeckFixture.createCardDeck(
                                Card.of(CardSymbol.SPADE, CardRank.SEVEN),
                                Card.of(CardSymbol.SPADE, CardRank.KING)),
                        Stay.class),
                Arguments.of(
                        CardDeckFixture.createCardDeck(
                                Card.of(CardSymbol.SPADE, CardRank.SIX),
                                Card.of(CardSymbol.SPADE, CardRank.KING)),
                        Hit.class)
        );
    }

    public static Stream<Arguments> providePlayersAndExpected() {
        return Stream.of(
                Arguments.of(new Player(new Hand(),
                                NicknameFixture.createNickname("ad"), BettingFixture.createBetting(1000)),
                        CardDeckFixture.createCardDeck(Card.of(CardSymbol.SPADE, CardRank.EIGHT),
                                Card.of(CardSymbol.SPADE, CardRank.EIGHT)),
                        BigDecimal.valueOf(-1000)
                ),
                Arguments.of(new Player(new Hand(),
                                NicknameFixture.createNickname("ad"), BettingFixture.createBetting(1000)),
                        CardDeckFixture.createCardDeck(Card.of(CardSymbol.SPADE, CardRank.EIGHT),
                                Card.of(CardSymbol.SPADE, CardRank.JACK)),
                        BigDecimal.valueOf(1000)
                ),
                Arguments.of(new Player(new Hand(),
                                NicknameFixture.createNickname("ad"), BettingFixture.createBetting(1000)),
                        CardDeckFixture.createCardDeck(Card.of(CardSymbol.SPADE, CardRank.EIGHT),
                                Card.of(CardSymbol.SPADE, CardRank.NINE)),
                        BigDecimal.valueOf(0)
                )
        );
    }

    @DisplayName("딜러는 초기 핸드 점수에 따라 다른 상태를 가진다.")
    @ParameterizedTest
    @MethodSource("provideCardDeckAndExpected")
    void checkState(CardDeck cardDeck, Class<?> expected) {
        // given
        Hand hand = new Hand();

        Dealer dealer = new Dealer(hand);

        dealer.initialDeal(cardDeck);

        // when
        final var actual = dealer.state;

        // then
        assertThat(actual).isInstanceOf(expected);
    }

    @DisplayName("딜러는 플레이어의 수익 결과를 도출한다.")
    @ParameterizedTest
    @MethodSource("providePlayersAndExpected")
    void determineMatchResult(Player player, CardDeck cardDeckForPlayer, BigDecimal expected) {
        // given
        CardDeck cardDeck = CardDeckFixture.createCardDeck(
                Card.of(CardSymbol.SPADE, CardRank.SEVEN),
                Card.of(CardSymbol.SPADE, CardRank.KING));

        Hand hand = new Hand();

        Dealer dealer = new Dealer(hand);
        dealer.initialDeal(cardDeck);
        player.initialDeal(cardDeckForPlayer);
        player.stay();

        // when
        ProfitResult profitResult = dealer.calculateProfits(List.of(player));
        BigDecimal actual = profitResult.getPlayerProfits().get(player);

        // then
        assertThat(actual.compareTo(expected)).isZero();
    }
}
