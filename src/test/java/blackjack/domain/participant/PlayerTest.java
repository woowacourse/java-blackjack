package blackjack.domain.participant;

import static blackjack.domain.card.CardNumber.FIVE;
import static blackjack.domain.card.CardNumber.JACK;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardSymbol.CLUB;
import static blackjack.domain.card.CardSymbol.DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Deck;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {

    @ParameterizedTest
    @CsvSource(value = {"TWO:false", "ACE:true"}, delimiter = ':')
    @DisplayName("카드의 합이 21을 초과하면 BUST를 반환한다.")
    void returnBust(CardNumber cardNumber, boolean expected) {
        // give
        final List<Card> cards = List.of(
                Card.of(DIAMOND, cardNumber),
                Card.of(DIAMOND, QUEEN),
                Card.of(DIAMOND, JACK));
        final Deck deck = Deck.from(() -> cards);

        final Player player = new Player("pobi");
        IntStream.range(0, 3)
                .mapToObj(i -> deck)
                .forEach(player::hit);

        // when
        final boolean actual = player.isDrawable();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("게임준비를 위해 가진 카드를 초기화한다.")
    void init() {
        // give
        final List<Card> cards = List.of(
                Card.of(DIAMOND, QUEEN),
                Card.of(DIAMOND, KING),
                Card.of(DIAMOND, JACK));
        final Deck deck = Deck.from(() -> cards);

        final Player player = new Player("pobi");

        // when
        player.initCards(deck);
        final int actual = player.getCards().getValue().size();

        // then
        assertThat(actual).isEqualTo(2);
    }

    @ParameterizedTest
    @DisplayName("플레이어가 버스트한 경우 상금을 계산한다.")
    @ValueSource(ints = {20, 22})
    void calculatePrize_playerBust(int dealerScore) {
        // give
        final Player player = new Player("rick");
        player.initMoney(1000);

        final Deck deck = Deck.from(() -> List.of(Card.of(CLUB, TEN), Card.of(CLUB, KING), Card.of(CLUB, FIVE)));
        IntStream.range(0, 3)
                .forEach(i -> player.hit(deck));

        // when
        player.calculatePrize(false, dealerScore);
        final int actual = player.getPrize();

        // then
        assertThat(actual).isEqualTo(-1000);
    }

    @ParameterizedTest
    @DisplayName("상금을 계산한다.")
    @CsvSource(value = {"true:21:ACE:0", "false:20:ACE:1500", "false:20:TEN:0", "false:20:NINE:-1000",
            "false:22:NINE:1000", "false:19:TEN:1000"}, delimiter = ':')
    void calculatePrize_playerNotBust(boolean isDealerBlackjack, int dealerScore, CardNumber cardNumber, int expected) {
        // give
        final Player player = new Player("rick");
        player.initMoney(1000);

        final Deck deck = Deck.from(() -> List.of(Card.of(CLUB, cardNumber), Card.of(CLUB, KING)));
        IntStream.range(0, 2)
                .forEach(i -> player.hit(deck));

        // when
        player.calculatePrize(isDealerBlackjack, dealerScore);
        final int actual = player.getPrize();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
