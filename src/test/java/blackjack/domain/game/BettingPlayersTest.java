package blackjack.domain.game;

import blackjack.domain.card.*;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.fixture.MockDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class BettingPlayersTest {
    @Test
    @DisplayName("생성한다.")
    void create() {
        final Deck deck = new CardDeck();
        final List<String> nameValues = List.of("헤나", "시카");
        final List<Integer> moneyValues = List.of(1000, 1234);

        assertThatNoException().isThrownBy(() -> new BettingPlayers(deck, nameValues, moneyValues));
    }

    @Test
    @DisplayName("플레이어 이름 목록이 비어있을 경우 예외가 발생한다.")
    void throwExceptionWhenNamesIsEmpty() {
        final Deck deck = new CardDeck();

        assertThatThrownBy(() -> new BettingPlayers(deck, Collections.emptyList(), List.of(1000)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("돈 목록이 비어있을 경우 예외가 발생한다.")
    void throwExceptionWhenMoneysIsEmpty() {
        final Deck deck = new CardDeck();

        assertThatThrownBy(() -> new BettingPlayers(deck, List.of("헤나"), Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("decideBetResultByDummy")
    @DisplayName("배팅 결과를 반환한다.")
    void decideBetResultBy(
            final Card playerCard1,
            final Card playerCard2,
            final List<Card> playerAdditionalCards,
            final Card dealerCard1,
            final Card dealerCard2,
            final List<Card> dealerAdditionalCards,
            final int playerBeforeBetMoney,
            final int playerAfterBetMoney
    ) {
        // given
        final Deck playerDeck = MockDeck.create(List.of(playerCard1, playerCard2));
        final Deck dealerDeck = MockDeck.create(List.of(dealerCard1, dealerCard2));
        final Dealer dealer = new Dealer(dealerDeck);
        dealerAdditionalCards.forEach(dealer::hit);

        // when
        final BettingPlayers bettingPlayers = new BettingPlayers(playerDeck, List.of("헤나"), List.of(playerBeforeBetMoney));
        final Player player = bettingPlayers.getPlayers().get(0);
        playerAdditionalCards.forEach(player::hit);
        final Map<Participant, Money> bettingResults = bettingPlayers.findBettingResultsBy(dealer);

        // then
        final int playerMoneyProfit = bettingResults.values()
                .stream()
                .map(Money::getValue)
                .reduce(0, Integer::sum);

        assertThat(playerMoneyProfit).isEqualTo(playerAfterBetMoney);
    }

    @Test
    @DisplayName("플레이어 리스트를 가져온다.")
    void getPlayers() {
        final Deck deck = new CardDeck();
        final BettingPlayers bettingPlayers = new BettingPlayers(deck, List.of("헤나"), List.of(0));
        final List<Player> players = bettingPlayers.getPlayers();

        assertThat(players).hasSize(1);
    }

    static Stream<Arguments> decideBetResultByDummy() {
        return Stream.of(
                Arguments.arguments(
                        // 플레이어 패배, 플레이어 딜러 모두 버스트하는 경우
                        // 플레이어
                        new Card(CardShape.DIAMOND, CardNumber.NINE),
                        new Card(CardShape.DIAMOND, CardNumber.QUEEN),
                        List.of(new Card(CardShape.SPADE, CardNumber.JACK),
                                new Card(CardShape.HEART, CardNumber.KING)),
                        // 딜러
                        new Card(CardShape.HEART, CardNumber.TWO),
                        new Card(CardShape.HEART, CardNumber.QUEEN),
                        List.of(new Card(CardShape.CLOVER, CardNumber.JACK),
                                new Card(CardShape.CLOVER, CardNumber.KING)),
                        // 플레이어 배팅 이전 금액
                        10000,
                        // 플레이어 배팅 이후 수익
                        -10000
                ),
                Arguments.arguments(
                        // 플레이어 패배, 플레이어만 버스트하는 경우
                        // 플레이어
                        new Card(CardShape.DIAMOND, CardNumber.NINE),
                        new Card(CardShape.DIAMOND, CardNumber.QUEEN),
                        List.of(new Card(CardShape.SPADE, CardNumber.JACK),
                                new Card(CardShape.HEART, CardNumber.KING)),
                        // 딜러
                        new Card(CardShape.HEART, CardNumber.TWO),
                        new Card(CardShape.HEART, CardNumber.FIVE),
                        List.of(new Card(CardShape.CLOVER, CardNumber.THREE),
                                new Card(CardShape.CLOVER, CardNumber.KING)),
                        // 플레이어 배팅 이전 금액
                        10000,
                        // 플레이어 배팅 이후 수익
                        -10000
                ),
                Arguments.arguments(
                        // 플레이어 승리, 플레이어의 점수가 딜러보다 높을 경우
                        // 플레이어
                        new Card(CardShape.DIAMOND, CardNumber.TWO),
                        new Card(CardShape.DIAMOND, CardNumber.FIVE),
                        List.of(new Card(CardShape.SPADE, CardNumber.THREE),
                                new Card(CardShape.HEART, CardNumber.QUEEN)),
                        // 딜러
                        new Card(CardShape.HEART, CardNumber.TWO),
                        new Card(CardShape.HEART, CardNumber.FIVE),
                        List.of(new Card(CardShape.CLOVER, CardNumber.TWO),
                                new Card(CardShape.CLOVER, CardNumber.KING)),
                        // 플레이어 배팅 이전 금액
                        10000,
                        // 플레이어 배팅 이후 수익
                        10000
                ),
                Arguments.arguments(
                        // 플레이어 패배, 플레이어의 점수보다 딜러가 높을 경우
                        // 플레이어
                        new Card(CardShape.DIAMOND, CardNumber.TWO),
                        new Card(CardShape.DIAMOND, CardNumber.FIVE),
                        List.of(new Card(CardShape.SPADE, CardNumber.TWO),
                                new Card(CardShape.HEART, CardNumber.QUEEN)),
                        // 딜러
                        new Card(CardShape.HEART, CardNumber.TWO),
                        new Card(CardShape.HEART, CardNumber.FIVE),
                        List.of(new Card(CardShape.CLOVER, CardNumber.THREE),
                                new Card(CardShape.CLOVER, CardNumber.KING)),
                        // 플레이어 배팅 이전 금액
                        10000,
                        // 플레이어 배팅 이후 수익
                        -10000
                ),
                Arguments.arguments(
                        // 플레이어 패배, 플레이어와 딜러의 점수가 동일하고 블랙잭이 아닐 경우
                        // 플레이어
                        new Card(CardShape.DIAMOND, CardNumber.TWO),
                        new Card(CardShape.DIAMOND, CardNumber.FIVE),
                        List.of(new Card(CardShape.SPADE, CardNumber.TWO),
                                new Card(CardShape.HEART, CardNumber.QUEEN)),
                        // 딜러
                        new Card(CardShape.HEART, CardNumber.TWO),
                        new Card(CardShape.HEART, CardNumber.FIVE),
                        List.of(new Card(CardShape.CLOVER, CardNumber.TWO),
                                new Card(CardShape.CLOVER, CardNumber.KING)),
                        // 플레이어 배팅 이전 금액
                        10000,
                        // 플레이어 배팅 이후 수익
                        -10000
                ),
                Arguments.arguments(
                        // 플레이어 무승부, 플레이어와 딜러의 점수가 블랙잭일 경우
                        // 플레이어
                        new Card(CardShape.DIAMOND, CardNumber.TWO),
                        new Card(CardShape.DIAMOND, CardNumber.FIVE),
                        List.of(new Card(CardShape.SPADE, CardNumber.FOUR),
                                new Card(CardShape.HEART, CardNumber.QUEEN)),
                        // 딜러
                        new Card(CardShape.HEART, CardNumber.TWO),
                        new Card(CardShape.HEART, CardNumber.FIVE),
                        List.of(new Card(CardShape.CLOVER, CardNumber.FOUR),
                                new Card(CardShape.CLOVER, CardNumber.KING)),
                        // 플레이어 배팅 이전 금액
                        10000,
                        // 플레이어 배팅 이후 수익
                        0
                ),
                Arguments.arguments(
                        // 플레이어 승리, 플레이어가 블랙잭이고 딜러는 아닐 경우
                        // 플레이어
                        new Card(CardShape.DIAMOND, CardNumber.TWO),
                        new Card(CardShape.DIAMOND, CardNumber.FIVE),
                        List.of(new Card(CardShape.SPADE, CardNumber.FOUR),
                                new Card(CardShape.HEART, CardNumber.QUEEN)),
                        // 딜러
                        new Card(CardShape.HEART, CardNumber.TWO),
                        new Card(CardShape.HEART, CardNumber.FIVE),
                        List.of(new Card(CardShape.CLOVER, CardNumber.THREE),
                                new Card(CardShape.CLOVER, CardNumber.KING)),
                        // 플레이어 배팅 이전 금액
                        10000,
                        // 플레이어 배팅 이후 수익
                        15000
                )
        );
    }
}
