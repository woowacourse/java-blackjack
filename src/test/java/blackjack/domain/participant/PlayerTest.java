package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Betting;
import blackjack.domain.Outcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Pattern;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PlayerTest {

    @Test
    @DisplayName("플레이어는 처음에 모든 카드를 보여준다.")
    void showEveryCard() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);
        List<Card> cards = List.of(card1, card2);

        Player player = new Player(new Name("Player"), cards, new Betting(1000));

        // when
        List<Card> actual = player.showInitialCards();

        // then
        assertThat(actual).containsOnly(card1, card2);
    }

    @Test
    @DisplayName("플레이어가 카드 한 장을 더 받는 경우")
    void addCard() {
        // given
        Name name = new Name("pobi");
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);
        List<Card> cards = List.of(card1, card2);

        CardDeck deck = new CardDeck(List.of(new Card(Pattern.HEART, Denomination.THREE)));
        Player player = new Player(name, cards, new Betting(1000));

        // when
        player.hit(deck);

        // then
        assertThat(player.getCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("플레이어의 카드의 총합이 21보다 작으면 hit이 가능하다.")
    void hittable() {
        // given
        Name name = new Name("pobi");
        Card card1 = new Card(Pattern.DIAMOND, Denomination.TEN);
        Card card2 = new Card(Pattern.CLOVER, Denomination.TEN);
        List<Card> cards = List.of(card1, card2);

        Player player = new Player(name, cards, new Betting(1000));

        // when
        boolean actual = player.isHittable();

        // then
        assertThat(actual).isEqualTo(true);
    }

    @Test
    @DisplayName("플레이어의 카드의 총합이 21 이상이면 hit이 불가능하다.")
    void notHittable() {
        // given
        Name name = new Name("pobi");
        Card card1 = new Card(Pattern.DIAMOND, Denomination.TEN);
        Card card2 = new Card(Pattern.CLOVER, Denomination.ACE);
        List<Card> cards = List.of(card1, card2);

        Player player = new Player(name, cards, new Betting(1000));

        // when
        boolean actual = player.isHittable();

        // then
        assertThat(actual).isEqualTo(false);
    }

    @ParameterizedTest
    @CsvSource(value = {"WIN_BLACKJACK,1500", "WIN,1000", "DRAW,0", "LOSE,-1000"})
    @DisplayName("승패 결과에 따라 수익을 계산한다.")
    void calculateProfit(String outcomeName, int expected) {
        // given
        Name name = new Name("pobi");
        Card card1 = new Card(Pattern.DIAMOND, Denomination.TEN);
        Card card2 = new Card(Pattern.CLOVER, Denomination.ACE);
        List<Card> cards = List.of(card1, card2);

        Player player = new Player(name, cards, new Betting(1000));

        // when
        int profit = player.calculateProfit(Outcome.valueOf(outcomeName));

        // then
        assertThat(profit).isEqualTo(expected);
    }
}
