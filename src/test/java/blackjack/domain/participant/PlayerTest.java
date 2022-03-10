package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.Name;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Pattern;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("플레이어는 생성될 때 두 장의 카드를 받는다.")
    void startWithDraw() {
        // given
        Name name = new Name("pobi");
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);
        List<Card> cards = List.of(card1, card2);

        // when
        Player player = new Player(name, cards);

        // then
        assertThat(player.getCards()).containsOnly(card1, card2);
    }

    @Test
    @DisplayName("플레이어를 생성할 때 카드는 null일 수 없다.")
    void cardsNotNull() {
        // given
        Name name = new Name("pobi");

        // then
        assertThatThrownBy(() -> new Player(name, null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("플레이어를 생성할 때 카드가 두 장이 아니면 예외가 발생한다.")
    void cardsSizeNotTwo() {
        // given
        Name name = new Name("pobi");
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);
        Card card3 = new Card(Pattern.HEART, Denomination.THREE);
        List<Card> cards = List.of(card1, card2, card3);

        // then
        assertThatThrownBy(() -> new Player(name, cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 카드를 두 장 받고 시작해야 합니다.");
    }

    @Test
    @DisplayName("플레이어를 생성할 때 카드가 중복되면 예외가 발생한다.")
    void duplicatedCards() {
        // given
        Name name = new Name("pobi");
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        List<Card> cards = List.of(card1, card1);

        // then
        assertThatThrownBy(() -> new Player(name, cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 카드는 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어가 카드 한 장을 더 받는 경우")
    void addCard() {
        // given
        Name name = new Name("pobi");
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);
        List<Card> cards = List.of(card1, card2);

        CardDeck deck = new CardDeck(() -> new ArrayList<>(List.of(new Card(Pattern.HEART, Denomination.THREE))));
        Player player = new Player(name, cards);

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

        Player player = new Player(name, cards);

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

        Player player = new Player(name, cards);

        // when
        boolean actual = player.isHittable();

        // then
        assertThat(actual).isEqualTo(false);
    }
}
