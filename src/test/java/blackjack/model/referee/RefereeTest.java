package blackjack.model.referee;

import blackjack.model.card.Card;
import blackjack.model.card.Denomination;
import blackjack.model.card.Suit;
import blackjack.model.cardgenerator.CardGenerator;
import blackjack.model.cardgenerator.SequentialCardGenerator;
import blackjack.model.dealer.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import blackjack.view.dto.PlayerMatchResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class RefereeTest {
    @ParameterizedTest
    @MethodSource("provideCardsAndExpectedResult")
    @DisplayName("딜러와 플레이어의 카드 합을 비교하여 승패를 가린다")
    void determineOutcomeTest(List<Card> playerCards, List<Card> dealerCards, MatchResult expectedResult) {
        // given
        Players players = new Players(List.of("mia"), new SequentialCardGenerator(playerCards));
        Dealer dealer = new Dealer(new SequentialCardGenerator(dealerCards));

        // when
        Referee referee = new Referee(players, dealer);
        List<PlayerMatchResult> actualResult = referee.determinePlayersMatchResult();

        // then
        assertThat(actualResult.get(0).matchResult()).isEqualTo(expectedResult);
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
    @DisplayName("버스트인 쪽이 패배한다. 플레이어와 딜러가 둘 다 버스트면 플레이어가 승리한다")
    public void ensurePlayerWinWhenBothBustTest(List<Card> playerCards, List<Card> dealerCards, MatchResult expectedResult) {
        // given
        Players players = createPlayers(playerCards);
        Dealer dealer = createDealer(dealerCards);

        // when
        Referee referee = new Referee(players, dealer);
        List<PlayerMatchResult> actualResult = referee.determinePlayersMatchResult();

        // then
        assertThat(actualResult.get(0).matchResult()).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideBustCardsAndExpectedResult() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(Suit.HEART, Denomination.TEN),
                                new Card(Suit.HEART, Denomination.TEN),
                                new Card(Suit.HEART, Denomination.TEN)
                        ),
                        List.of(
                                new Card(Suit.HEART, Denomination.TWO),
                                new Card(Suit.HEART, Denomination.SIX),
                                new Card(Suit.HEART, Denomination.TEN)
                        ),
                        MatchResult.LOSE),
                Arguments.of(
                        List.of(
                                new Card(Suit.HEART, Denomination.TWO),
                                new Card(Suit.HEART, Denomination.TWO),
                                new Card(Suit.HEART, Denomination.TWO)
                        ),
                        List.of(
                                new Card(Suit.HEART, Denomination.EIGHT),
                                new Card(Suit.HEART, Denomination.EIGHT),
                                new Card(Suit.HEART, Denomination.TEN)
                        ),
                        MatchResult.WIN),
                Arguments.of(
                        List.of(
                                new Card(Suit.HEART, Denomination.TEN),
                                new Card(Suit.HEART, Denomination.TEN),
                                new Card(Suit.HEART, Denomination.TEN)
                        ),
                        List.of(
                                new Card(Suit.HEART, Denomination.EIGHT),
                                new Card(Suit.HEART, Denomination.EIGHT),
                                new Card(Suit.HEART, Denomination.TEN)
                        ),
                        MatchResult.WIN)
        );
    }

    private Players createPlayers(final List<Card> playerCards) {
        CardGenerator cardGenerator = new SequentialCardGenerator(playerCards);
        Players players = new Players(List.of("mia"), cardGenerator);
        Player player = players.getPlayers().get(0);
        player.hit(cardGenerator);
        return players;
    }

    private Dealer createDealer(final List<Card> dealerCards) {
        CardGenerator cardGenerator = new SequentialCardGenerator(dealerCards);
        Dealer dealer = new Dealer(cardGenerator);
        dealer.hitUntilEnd(cardGenerator);
        return dealer;
    }
}
