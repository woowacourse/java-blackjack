package blackjack.domain.participant;

import static blackjack.domain.card.CardNumber.JACK;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.QUEEN;
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

class PlayerTest {

    @ParameterizedTest
    @CsvSource(value = {"TWO:false", "ACE:true"}, delimiter = ':')
    @DisplayName("카드의 합이 21을 초과하면 BUST를 반환한다.")
    void returnBust(CardNumber cardNumber, boolean expected) {
        // give
        final Player player = new Player("pobi");
        final List<Card> cards = List.of(Card.of(DIAMOND, cardNumber), Card.of(DIAMOND, QUEEN),
                Card.of(DIAMOND, JACK));
        final Deck deck = Deck.createBy(cards);
        IntStream.range(0, 3)
                .mapToObj(i -> deck)
                .forEach(player::hit);

        // when
        final boolean actual = player.isHit();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("게임준비를 위해 가진 카드를 초기화한다.")
    void init() {
        // give
        final Player player = new Player("pobi");
        final List<Card> cards = List.of(Card.of(DIAMOND, QUEEN), Card.of(DIAMOND, KING));

        // when
        player.initCards(Deck.createBy(cards));
        final int actual = player.getCards().getValue().size();

        // then
        assertThat(actual).isEqualTo(2);
    }
}
