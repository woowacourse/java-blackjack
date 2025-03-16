package domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardRank;
import domain.card.CardSymbol;
import domain.card.Hand;
import domain.rule.BlackjackMatchResult;
import domain.state.started.finished.Stay;
import domain.state.started.running.Hit;
import fixture.BettingFixture;
import fixture.CardDeckFixture;
import fixture.HandFixture;
import fixture.NicknameFixture;
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
                        BlackjackMatchResult.LOSE
                ),
                Arguments.of(new Player(HandFixture.createHand(Card.of(CardSymbol.SPADE, CardRank.EIGHT),
                                Card.of(CardSymbol.SPADE, CardRank.JACK)),
                                NicknameFixture.createNickname("ad"), BettingFixture.createBetting(1000)),
                        BlackjackMatchResult.WIN
                ),
                Arguments.of(new Player(HandFixture.createHand(Card.of(CardSymbol.SPADE, CardRank.EIGHT),
                                Card.of(CardSymbol.SPADE, CardRank.NINE)),
                                NicknameFixture.createNickname("ad"), BettingFixture.createBetting(1000)),
                        BlackjackMatchResult.DRAW
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

    @DisplayName("딜러는 플레이어의 승패 결과를 도출한다.")
    @ParameterizedTest
    @MethodSource("providePlayersAndExpected")
    void determineMatchResult(Player player, BlackjackMatchResult expected) {
        // given
        CardDeck cardDeck = CardDeckFixture.createCardDeck(
                Card.of(CardSymbol.SPADE, CardRank.SEVEN),
                Card.of(CardSymbol.SPADE, CardRank.KING));

        Hand hand = new Hand(cardDeck);

        Dealer dealer = new Dealer(hand);

        // when
        BlackjackMatchResult actual = dealer.determineMatchResultFor(player);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
