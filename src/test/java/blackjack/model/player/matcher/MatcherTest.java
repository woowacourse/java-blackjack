package blackjack.model.player.matcher;

import static blackjack.model.card.Suit.CLOVER;
import static blackjack.model.card.Suit.DIAMOND;
import static blackjack.model.card.Suit.HEART;
import static blackjack.model.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.player.Dealer;
import blackjack.model.player.Gamer;
import blackjack.model.player.Money;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MatcherTest {


    private static final Card ACE = new Card(Rank.ACE, SPADE);
    private static final Card TWO = new Card(Rank.TWO, SPADE);
    private static final Card THREE = new Card(Rank.THREE, CLOVER);
    private static final Card FOUR = new Card(Rank.FOUR, HEART);
    private static final Card FIVE = new Card(Rank.FIVE, CLOVER);
    private static final Card SIX = new Card(Rank.SIX, SPADE);
    private static final Card SEVEN = new Card(Rank.SEVEN, CLOVER);
    private static final Card EIGHT = new Card(Rank.EIGHT, DIAMOND);
    private static final Card JACK = new Card(Rank.JACK, HEART);
    private static final Card QUEEN = new Card(Rank.QUEEN, SPADE);
    private static final Card KING = new Card(Rank.KING, CLOVER);
    private static final String GAMER_NAME = "pobi";
    private static final Money MONEY = new Money(new BigDecimal("1000"));

    @ParameterizedTest
    @MethodSource("providePlayerLosingCaseCards")
    @DisplayName("블랙잭과 버스트 경우를 제외하고 플레이어가 지는 경우")
    void dealerIsWinner(Dealer dealer, Gamer gamer) {
        Matcher matcher = Matcher.of(dealer);

        Record record = matcher.match(gamer);

        assertThat(record.name()).isEqualTo(GAMER_NAME);
        assertThat(record.profit()).isEqualTo(MONEY.negate());
    }

    private static Stream<Arguments> providePlayerLosingCaseCards() {
        return Stream.of(
            Arguments.of(new Dealer(EIGHT, JACK), new Gamer(GAMER_NAME, MONEY, SEVEN, EIGHT)),
            Arguments.of(new Dealer(ACE, FIVE), new Gamer(GAMER_NAME, MONEY, SEVEN, SIX)),
            Arguments.of(new Dealer(ACE, FIVE, SIX), new Gamer(GAMER_NAME, MONEY, THREE, FOUR))
        );
    }

    @ParameterizedTest
    @MethodSource("providePlayerWinningCaseCards")
    @DisplayName("블랙잭과 버스트인 경우를 제외하고 플레이어가 이기는 경우")
    void dealerIsLoser(Dealer dealer, Gamer gamer) {
        Matcher matcher = Matcher.of(dealer);

        Record record = matcher.match(gamer);

        assertThat(record.name()).isEqualTo(GAMER_NAME);
        assertThat(record.profit()).isEqualTo(MONEY);
    }

    private static Stream<Arguments> providePlayerWinningCaseCards() {
        return Stream.of(
            Arguments.of(new Dealer(SEVEN, EIGHT), new Gamer(GAMER_NAME, MONEY, EIGHT, JACK)),
            Arguments.of(new Dealer(SEVEN, SIX), new Gamer(GAMER_NAME, MONEY, ACE, FIVE)),
            Arguments.of(new Dealer(THREE, FOUR), new Gamer(GAMER_NAME, MONEY, ACE, FIVE, SIX))
        );
    }

    @Test
    @DisplayName("블랙잭과 버스트 경우를 제외하고 비기는 경우")
    void dealerIsDraw() {
        Dealer dealer = new Dealer(JACK, FOUR);
        Gamer gamer = new Gamer(GAMER_NAME, MONEY, EIGHT, SIX);

        Matcher matcher = Matcher.of(dealer);

        Record record = matcher.match(gamer);

        assertThat(record.name()).isEqualTo(GAMER_NAME);
        assertThat(record.profit()).isEqualTo(new Money(BigDecimal.ZERO));
    }

    @Test
    @DisplayName("딜러가 버스트인 경우")
    void dealerIsBust() {
        Dealer dealer = new Dealer(JACK, QUEEN, TWO);
        Gamer gamer = new Gamer(GAMER_NAME, MONEY, EIGHT, SIX);

        Matcher matcher = Matcher.of(dealer);

        Record record = matcher.match(gamer);

        assertThat(record.name()).isEqualTo(GAMER_NAME);
        assertThat(record.profit()).isEqualTo(MONEY);
    }

    @Test
    @DisplayName("플레이어가 버스트인 경우")
    void playerIsBust() {
        Dealer dealer = new Dealer(JACK, QUEEN);
        Gamer gamer = new Gamer(GAMER_NAME, MONEY, JACK, QUEEN, TWO);

        Matcher matcher = Matcher.of(dealer);

        Record record = matcher.match(gamer);

        assertThat(record.name()).isEqualTo(GAMER_NAME);
        assertThat(record.profit()).isEqualTo(MONEY.negate());
    }

    @Test
    @DisplayName("둘 다 버스트인 경우 테스트")
    void bothBust() {
        Dealer dealer = new Dealer(JACK, KING, SEVEN);
        Gamer gamer = new Gamer(GAMER_NAME, MONEY, EIGHT, SIX, KING);

        Matcher matcher = Matcher.of(dealer);

        Record record = matcher.match(gamer);

        assertThat(record.name()).isEqualTo(GAMER_NAME);
        assertThat(record.profit()).isEqualTo(MONEY.negate());
    }

    @Test
    @DisplayName("플레이어만 블랙잭인 경우")
    void playerBlackjackByInitialCards() {
        Dealer dealer = new Dealer(JACK, KING);
        Gamer gamer = new Gamer(GAMER_NAME, MONEY, ACE, JACK);

        Matcher matcher = Matcher.of(dealer);

        Record record = matcher.match(gamer);

        assertThat(record.name()).isEqualTo(GAMER_NAME);
        assertThat(record.profit()).isEqualTo(MONEY.multiply(new BigDecimal("1.5")));
    }

    @Test
    @DisplayName("딜러만 블랙잭인 경우")
    void dealerBlackjackByInitialCards() {
        Dealer dealer = new Dealer(JACK, ACE);
        Gamer gamer = new Gamer(GAMER_NAME, MONEY, QUEEN, JACK);

        Matcher matcher = Matcher.of(dealer);

        Record record = matcher.match(gamer);

        assertThat(record.name()).isEqualTo(GAMER_NAME);
        assertThat(record.profit()).isEqualTo(MONEY.negate());
    }

    @Test
    @DisplayName("플레이어와 딜러 블랙잭인 경우")
    void playerAndDealerBlackjackByInitialCards() {
        Dealer dealer = new Dealer(JACK, ACE);
        Gamer gamer = new Gamer(GAMER_NAME, MONEY, ACE, JACK);

        Matcher matcher = Matcher.of(dealer);

        Record record = matcher.match(gamer);

        assertThat(record.name()).isEqualTo(GAMER_NAME);
        assertThat(record.profit()).isEqualTo(new Money(BigDecimal.ZERO));
    }

    @Test
    @DisplayName("딜러만 블랙잭이고 플레이어는 여러장을 받아 21점인 경우")
    void dealerBlackjackByInitialCardsPlayerJustBlackjack() {
        Dealer dealer = new Dealer(ACE, JACK);
        Gamer gamer = new Gamer(GAMER_NAME, MONEY, QUEEN, JACK, ACE);

        Matcher matcher = Matcher.of(dealer);

        Record record = matcher.match(gamer);

        assertThat(record.name()).isEqualTo(GAMER_NAME);
        assertThat(record.profit()).isEqualTo(MONEY.negate());
    }

    @Test
    @DisplayName("플레이어만 블랙잭이고 딜러는 여러장을 받아 21점인 경우")
    void playerBlackjackByInitialCardsDealerJustBlackjack() {
        Dealer dealer = new Dealer(ACE, JACK, QUEEN);
        Gamer gamer = new Gamer(GAMER_NAME, MONEY, QUEEN, ACE);
        Matcher matcher = Matcher.of(dealer);

        Record record = matcher.match(gamer);

        assertThat(record.name()).isEqualTo(GAMER_NAME);
        assertThat(record.profit()).isEqualTo(MONEY.multiply(new BigDecimal("1.5")));
    }

    @Test
    @DisplayName("플레이어는 블랙잭이고 딜러는 버스트인 경우")
    void playerBlackjackAndDealerBust() {
        Dealer dealer = new Dealer(THREE, JACK, QUEEN);
        Gamer gamer = new Gamer(GAMER_NAME, MONEY, QUEEN, ACE);
        Matcher matcher = Matcher.of(dealer);

        Record record = matcher.match(gamer);

        assertThat(record.name()).isEqualTo(GAMER_NAME);
        assertThat(record.profit()).isEqualTo(MONEY.multiply(new BigDecimal("1.5")));
    }

}
