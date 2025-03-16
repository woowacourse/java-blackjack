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
import fixture.HandFixture;
import fixture.NicknameFixture;
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
                Arguments.of(new Player(HandFixture.createHand(Card.of(CardSymbol.SPADE, CardRank.EIGHT),
                                Card.of(CardSymbol.SPADE, CardRank.EIGHT)),
                                NicknameFixture.createNickname("ad"), BettingFixture.createBetting(1000)),
                        -1000
                ),
                Arguments.of(new Player(HandFixture.createHand(Card.of(CardSymbol.SPADE, CardRank.EIGHT),
                                Card.of(CardSymbol.SPADE, CardRank.JACK)),
                                NicknameFixture.createNickname("ad"), BettingFixture.createBetting(1000)),
                        1000
                ),
                Arguments.of(new Player(HandFixture.createHand(Card.of(CardSymbol.SPADE, CardRank.EIGHT),
                                Card.of(CardSymbol.SPADE, CardRank.NINE)),
                                NicknameFixture.createNickname("ad"), BettingFixture.createBetting(1000)),
                        0
                )
        );
    }

    @DisplayName("딜러는 초기 핸드 점수에 따라 다른 상태를 가진다.")
    @ParameterizedTest
    @MethodSource("provideCardDeckAndExpected")
    void checkState(CardDeck cardDeck, Class<?> expected) {
        // given
        Hand hand = new Hand(cardDeck);

        Dealer dealer = new Dealer(hand);

        // when
        final var actual = dealer.state;

        // then
        assertThat(actual).isInstanceOf(expected);
    }

    @DisplayName("딜러는 플레이어의 수익 결과를 도출한다.")
    @ParameterizedTest
    @MethodSource("providePlayersAndExpected")
    void determineMatchResult(Player player, int expected) {
        // given
        CardDeck cardDeck = CardDeckFixture.createCardDeck(
                Card.of(CardSymbol.SPADE, CardRank.SEVEN),
                Card.of(CardSymbol.SPADE, CardRank.KING));

        Hand hand = new Hand(cardDeck);

        Dealer dealer = new Dealer(hand);
        player.stay();

        // when
        ProfitResult profitResult = dealer.calculateProfits(List.of(player));
        int actual = profitResult.getPlayerProfits().get(player);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
