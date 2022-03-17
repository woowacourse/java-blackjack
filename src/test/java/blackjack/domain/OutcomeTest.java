package blackjack.domain;

import static blackjack.domain.Outcome.DRAW;
import static blackjack.domain.Outcome.LOSE;
import static blackjack.domain.Outcome.WIN;
import static blackjack.domain.Outcome.WIN_BLACKJACK;
import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.EIGHT;
import static blackjack.domain.card.Denomination.FIVE;
import static blackjack.domain.card.Denomination.NINE;
import static blackjack.domain.card.Denomination.SIX;
import static blackjack.domain.card.Denomination.TEN;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.domain.card.Pattern.CLOVER;
import static blackjack.domain.card.Pattern.DIAMOND;
import static blackjack.domain.card.Pattern.HEART;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Denomination;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class OutcomeTest {

    @ParameterizedTest
    @MethodSource("provideForNoOneBust")
    @DisplayName("딜러와 플레이어 둘 다 버스트하지 않았을 경우 점수가 더 큰 쪽이 이긴다.")
    void bothNotBust(Dealer dealer, Outcome playerOutcome) {
        // given
        Player player = createPlayer(NINE);

        // when
        Outcome actual = Outcome.judge(player, dealer);

        // then
        assertThat(actual).isEqualTo(playerOutcome);
    }

    private static Stream<Arguments> provideForNoOneBust() {
        return Stream.of(
                Arguments.of(createDealer(TEN), LOSE),
                Arguments.of(createDealer(NINE), DRAW),
                Arguments.of(createDealer(EIGHT), WIN)
        );
    }

    @ParameterizedTest
    @MethodSource("provideForPlayerBust")
    @DisplayName("플레이어가 버스트면 무조건 딜러가 이긴다")
    void playerBust(CardDeck deck) {
        // given
        Dealer dealer = createDealer(SIX);
        dealer.hit(deck);

        Player player = createPlayer(TEN);
        player.hit(deck);

        // when
        Outcome actual = Outcome.judge(player, dealer);

        // then
        assertThat(actual).isEqualTo(LOSE);
    }

    private static Stream<Arguments> provideForPlayerBust() {
        return Stream.of(
                Arguments.of(new CardDeck(List.of(Card.of(HEART, SIX), Card.of(HEART, TWO)))),
                Arguments.of(new CardDeck(List.of(Card.of(HEART, FIVE), Card.of(HEART, TWO))))
        );
    }

    @Test
    @DisplayName("플레이어가 버스트가 아니고 딜러가 버스트면 플레이어가 이긴다")
    void dealerBust() {
        // given
        Dealer dealer = createDealer(SIX);
        dealer.hit(new CardDeck(List.of(Card.of(HEART, SIX))));

        Player player = createPlayer(TEN);

        // when
        Outcome actual = Outcome.judge(player, dealer);

        // then
        assertThat(actual).isEqualTo(WIN);
    }

    @Test
    @DisplayName("똑같이 21점이어도 블랙잭이 이긴다.")
    void blackJackDoesNotDefeat() {
        // given
        Dealer dealer = createDealer(SIX);

        Player player = createPlayer(ACE);
        dealer.hit(new CardDeck(List.of(Card.of(HEART, SIX))));

        // when
        Outcome actual = Outcome.judge(player, dealer);

        // then
        assertThat(actual).isEqualTo(WIN_BLACKJACK);
    }

    @Test
    @DisplayName("블랙잭 끼리는 비긴다")
    void blackJackDrawWithBlackJack() {
        // given
        Dealer dealer = createDealer(ACE);

        Player player = createPlayer(ACE);

        // when
        Outcome actual = Outcome.judge(player, dealer);

        // then
        assertThat(actual).isEqualTo(DRAW);
    }

    @ParameterizedTest
    @CsvSource(value = {"WIN_BLACKJACK,1.5", "WIN,1", "DRAW,0", "LOSE,-1"})
    @DisplayName("각각의 결과값은 수익률을 가지고 있다.")
    void returnOpposite(String inputName, double expected) {
        // given
        Outcome outcome = Outcome.valueOf(inputName);

        // when
        double actual = outcome.getProfitRate();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Dealer createDealer(Denomination denomination2) {
        Card card1 = Card.of(DIAMOND, TEN);
        Card card2 = Card.of(CLOVER, denomination2);
        List<Card> dealerCards = List.of(card1, card2);
        return new Dealer(dealerCards);
    }

    private static Player createPlayer(Denomination denomination2) {
        Card card1 = Card.of(DIAMOND, TEN);
        Card card2 = Card.of(CLOVER, denomination2);
        List<Card> playerCards = List.of(card1, card2);
        return new Player(new Name("player"), playerCards, new Betting(1000));
    }
}
