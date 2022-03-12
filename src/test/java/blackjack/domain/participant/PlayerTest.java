package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Pattern;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

        Player player = new Player(name, cards);

        // when
        player.hit(new Card(Pattern.HEART, Denomination.THREE));

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
        assertThat(actual).isTrue();
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
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("카드의 점수 총합을 계산한다. (에이스가 없는 경우)")
    void calculateCardsSum() {
        // given
        Name name = new Name("lala");
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);
        List<Card> cards = List.of(card1, card2);

        Player player = new Player(name, cards);

        // when
        int actual = player.calculateScore();

        // then
        assertThat(actual).isEqualTo(6);
    }

    @Test
    @DisplayName("카드의 점수 총합을 계산한다. (에이스가 있는 경우)")
    void calculateCardsSumWithACE() {
        // given
        Name name = new Name("lala");
        Card card1 = new Card(Pattern.DIAMOND, Denomination.ACE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.ACE);
        Card card3 = new Card(Pattern.HEART, Denomination.ACE);
        Card card4 = new Card(Pattern.SPADE, Denomination.ACE);
        List<Card> cards = List.of(card1, card2);

        Player player = new Player(name, cards);
        player.hit(card3);
        player.hit(card4);

        // when
        int actual = player.calculateScore();

        // then
        assertThat(actual).isEqualTo(14);
    }

    @Test
    @DisplayName("카드의 점수 총합을 계산한다. (총 합이 21이 넘는 경우)")
    void calculateCardsSumOver21() {
        // given
        Name name = new Name("lala");
        Card card1 = new Card(Pattern.DIAMOND, Denomination.TEN);
        Card card2 = new Card(Pattern.CLOVER, Denomination.TEN);
        Card card3 = new Card(Pattern.HEART, Denomination.TWO);
        List<Card> cards = List.of(card1, card2);

        Player player = new Player(name, cards);
        player.hit(card3);

        // when
        int actual = player.calculateScore();

        // then
        assertThat(actual).isEqualTo(22);
    }

    @ParameterizedTest
    @MethodSource("provideBustTest")
    @DisplayName("카드의 총합이 21이 넘는 경우 버스트이다.")
    void bust(Card card, boolean expected) {
        // given
        Name name = new Name("lala");
        Card DIAMOND_TEN = new Card(Pattern.DIAMOND, Denomination.TEN);
        Card CLOVER_TEN = new Card(Pattern.CLOVER, Denomination.TEN);
        List<Card> cards = List.of(DIAMOND_TEN, CLOVER_TEN);

        Player player = new Player(name, cards);
        player.hit(card);

        // when
        boolean actual = player.isBust();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideBustTest() {
        Card HEART_TWO = new Card(Pattern.HEART, Denomination.TWO);
        Card SPADE_ACE = new Card(Pattern.SPADE, Denomination.ACE);
        return Stream.of(
            Arguments.of(HEART_TWO, true),
            Arguments.of(SPADE_ACE, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideBlackJackTest")
    @DisplayName("점수의 총합이 21이면서 2장이면 블랙잭이다.")
    void blackJack(List<Card> initCards, List<Card> hitCards, boolean expected) {
        // given
        Name name = new Name("lala");
        Player player = new Player(name, initCards);
        for (Card hitCard : hitCards) {
            player.hit(hitCard);
        }

        // when
        boolean actual = player.isBlackJack();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideBlackJackTest() {
        Card DIAMOND_TEN = new Card(Pattern.DIAMOND, Denomination.TEN);
        Card CLOVER_TEN = new Card(Pattern.CLOVER, Denomination.TEN);
        Card SPADE_ACE = new Card(Pattern.SPADE, Denomination.ACE);
        return Stream.of(
            Arguments.of(List.of(DIAMOND_TEN, SPADE_ACE), List.of(), true),
            Arguments.of(List.of(DIAMOND_TEN, CLOVER_TEN), List.of(SPADE_ACE), false)
        );
    }
}
