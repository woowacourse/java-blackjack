package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.card.Symbol.CLOVER;
import static blackjack.domain.card.Symbol.DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @Test
    @DisplayName("이름은 공백일 수 없다")
    void throwExceptionWhenNameLengthIsBlank() {
        List<Card> initCards = List.of(new Card(CLOVER, JACK), new Card(DIAMOND, FIVE));
        assertThatThrownBy(() -> new Player(" ", Cards.of(initCards)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이름은 6자를 초과할 수 없다")
    void throwExceptionWhenNameLengthOverMaxLength() {
        List<Card> initCards = List.of(new Card(CLOVER, JACK), new Card(DIAMOND, FIVE));
        assertThatThrownBy(() -> new Player("1234567", Cards.of(initCards)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("hit이 가능한 상태라면 참을 반환한다.")
    void testIsAbleToHit1() {
        // given
        List<Card> initCards = List.of(new Card(CLOVER, JACK), new Card(DIAMOND, FIVE));
        Player player = new Player("pobi", Cards.of(initCards));

        // when
        boolean actual = player.isAbleToHit();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("블랙잭이면 더이상 hit을 할 수 없다.")
    void testIsAbleToHit2() {
        // given
        List<Card> initCards = List.of(new Card(CLOVER, JACK), new Card(DIAMOND, FIVE));
        Player player = new Player("pobi", Cards.of(initCards));
        player.addCard(new Card(DIAMOND, SIX));

        // when
        boolean actual = player.isAbleToHit();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("버스트면 더이상 hit을 할 수 없다.")
    void testIsAbleToHit3() {
        // given
        List<Card> initCards = List.of(new Card(CLOVER, JACK), new Card(DIAMOND, FIVE));
        Player player = new Player("pobi", Cards.of(initCards));
        player.addCard(new Card(DIAMOND, SEVEN));

        // when
        boolean actual = player.isAbleToHit();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("stay하면 더이상 hit을 할 수 없다.")
    void testIsAbleToHit4() {
        // given
        List<Card> initCards = List.of(new Card(CLOVER, JACK), new Card(DIAMOND, FIVE));
        Player player = new Player("pobi", Cards.of(initCards));

        // when
        player.stay();
        boolean actual = player.isAbleToHit();

        // then
        assertThat(actual).isFalse();
    }
}
