package domain.participant.player;

import static org.assertj.core.api.Assertions.assertThat;

import static domain.card.CardFixture.cardOf;
import static domain.card.Rank.ACE;
import static domain.card.Rank.EIGHT;
import static domain.card.Rank.JACK;
import static domain.card.Rank.KING;
import static domain.card.Rank.NINE;
import static domain.card.Rank.QUEEN;
import static domain.card.Rank.TWO;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.card.Card;
import domain.card.Deck;
import domain.participant.attributes.Bet;
import domain.participant.attributes.Name;
import domain.participant.dealer.Dealer;
import domain.result.ProfitFixture;

class PlayerTest {

    @DisplayName("참가자는 카드의 합을 구할 수 있다.")
    @Test
    void cardSum() {
        Player player = PlayerFixture.from("zeus");
        player.receive(cardOf(JACK));
        assertThat(player.score()).isEqualTo(10);
    }

    @DisplayName("카드의 합계로 `Bust`를 판단한다.")
    @Test
    void isBust() {
        Card cardTwo = cardOf(TWO);
        Card cardKing = cardOf(KING);
        Card cardQueen = cardOf(QUEEN);
        Player player = PlayerFixture.from("hotea");
        player.receive(cardTwo);
        player.receive(cardKing);
        player.receive(cardQueen);
        assertThat(player.isBust()).isTrue();
    }

    @DisplayName("카드의 합계로 `Blackjack`를 판단한다.")
    @Test
    void isBlackjack() {
        Card cardAce = cardOf(ACE);
        Card cardKing = cardOf(KING);
        Player player = PlayerFixture.from("hotea");
        player.receive(cardAce);
        player.receive(cardKing);
        assertThat(player.isBlackjack()).isTrue();
    }

    static Stream<Arguments> profitWhenBlackjack() {
        return Stream.of(
                Arguments.of(
                        List.of(cardOf(ACE), cardOf(NINE), cardOf(ACE), cardOf(KING)),
                        1000,
                        1500
                )
        );
    }

    static Stream<Arguments> profitWhenWin() {
        return Stream.of(
                Arguments.of(
                        List.of(cardOf(ACE), cardOf(EIGHT), cardOf(ACE), cardOf(NINE)),
                        1000,
                        1000
                )
        );
    }

    static Stream<Arguments> profitWhenLose() {
        return Stream.of(
                Arguments.of(
                        List.of(cardOf(ACE), cardOf(NINE), cardOf(ACE), cardOf(EIGHT)),
                        1000,
                        -1000
                )
        );
    }

    static Stream<Arguments> profitWhenDraw() {
        return Stream.of(
                Arguments.of(
                        List.of(cardOf(ACE), cardOf(KING), cardOf(ACE), cardOf(KING)),
                        1000,
                        0
                ),
                Arguments.of(
                        List.of(cardOf(ACE), cardOf(NINE), cardOf(ACE), cardOf(NINE)),
                        1000,
                        0
                )
        );
    }

    static Stream<Arguments> profitAgainst() {
        return Stream.of(
                profitWhenBlackjack(),
                profitWhenWin(),
                profitWhenLose(),
                profitWhenDraw()
        ).flatMap(stream -> stream);
    }

    @DisplayName("딜러를 상대로 수익을 구할 수 있다.")
    @MethodSource
    @ParameterizedTest
    void profitAgainst(List<Card> cards, int playerBet, int expectedProfit) {
        Player player = new Player(new Name("Pobi"), new Bet(playerBet));
        Dealer dealer = new Dealer(new Deck(cards));
        dealer.dealInitialCards(Players.from(List.of(player)));
        assertThat(player.profitAgainst(dealer)).isEqualTo(ProfitFixture.amountOf(expectedProfit));
    }
}
