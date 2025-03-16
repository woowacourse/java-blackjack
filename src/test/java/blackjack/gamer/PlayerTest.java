package blackjack.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.CardDeck;
import blackjack.card.CardRank;
import blackjack.card.CardSymbol;
import blackjack.card.Hand;
import blackjack.result.BlackjackMatchResult;
import fixture.BettingFixture;
import fixture.CardDeckFixture;
import fixture.NicknameFixture;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {


    public static Stream<Arguments> provideResultAndExpectedForBlackjack() {
        return Stream.of(
                Arguments.of(BlackjackMatchResult.WIN, 1500),
                Arguments.of(BlackjackMatchResult.DRAW, 0)
        );
    }

    public static Stream<Arguments> provideResultAndExpectedForStay() {
        return Stream.of(
                Arguments.of(BlackjackMatchResult.WIN, 1000),
                Arguments.of(BlackjackMatchResult.DRAW, 0),
                Arguments.of(BlackjackMatchResult.LOSE, -1000)
        );
    }

    public static Stream<Arguments> provideResultAndExpectedForBust() {
        return Stream.of(
                Arguments.of(BlackjackMatchResult.DRAW, 0),
                Arguments.of(BlackjackMatchResult.LOSE, -1000)
        );
    }

    @DisplayName("플레이어는 카드를 뽑을 수 있다.")
    @Test
    void hit() {
        //given
        CardDeck cardDeck = CardDeckFixture.createCardDeck(
                Card.of(CardSymbol.SPADE, CardRank.EIGHT),
                Card.of(CardSymbol.SPADE, CardRank.SEVEN),
                Card.of(CardSymbol.SPADE, CardRank.TWO)
        );
        Hand hand = new Hand(cardDeck);

        Player player = new Player(hand, NicknameFixture.createNickname("ad"), BettingFixture.createBetting(1000));

        //when
        int initialSize = player.getHand().getCards().size();
        player.hit(cardDeck);
        int actual = player.getHand().getCards().size() - initialSize;

        //then
        assertThat(actual).isEqualTo(1);
    }

    @DisplayName("플레이어는 블랙잭으로 승리하면 베팅 금액의 1.5배의 수익, 무승부면 0배의 수익을 얻는다")
    @ParameterizedTest
    @MethodSource("provideResultAndExpectedForBlackjack")
    void getProfitOfBlackjack(BlackjackMatchResult result, double expected) {
        // given
        CardDeck cardDeck = CardDeckFixture.createCardDeck(
                Card.of(CardSymbol.SPADE, CardRank.ACE),
                Card.of(CardSymbol.SPADE, CardRank.JACK)
        );

        Hand hand = new Hand(cardDeck);

        Player player = new Player(hand, NicknameFixture.createNickname("ad"), BettingFixture.createBetting(1000));

        //when
        double actual = player.getProfit(result);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("플레이어는 Stay로 승리하면 베팅 금액의 1배의 수익, 패배하면 1배의 손해, 무승부면 0배의 수익을 얻는다.")
    @ParameterizedTest
    @MethodSource("provideResultAndExpectedForStay")
    void getProfitOfStay(BlackjackMatchResult result, double expected) {
        // given
        CardDeck cardDeck = CardDeckFixture.createCardDeck(
                Card.of(CardSymbol.SPADE, CardRank.KING),
                Card.of(CardSymbol.SPADE, CardRank.JACK)
        );

        Hand hand = new Hand(cardDeck);

        Player player = new Player(hand, NicknameFixture.createNickname("ad"), BettingFixture.createBetting(1000));
        player.stay();

        //when
        double actual = player.getProfit(result);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("플레이어는 Bust로 패배하면 1배의 손해, 무승부면 0배의 수익을 얻는다.")
    @ParameterizedTest
    @MethodSource("provideResultAndExpectedForBust")
    void getProfitOfBust(BlackjackMatchResult result, double expected) {
        // given
        CardDeck cardDeck = CardDeckFixture.createCardDeck(
                Card.of(CardSymbol.SPADE, CardRank.EIGHT),
                Card.of(CardSymbol.SPADE, CardRank.KING),
                Card.of(CardSymbol.SPADE, CardRank.JACK)
        );

        Hand hand = new Hand(cardDeck);

        Player player = new Player(hand, NicknameFixture.createNickname("ad"), BettingFixture.createBetting(1000));
        player.hit(cardDeck);

        //when
        double actual = player.getProfit(result);

        // then
        assertThat(actual).isEqualTo(expected);
    }


}
