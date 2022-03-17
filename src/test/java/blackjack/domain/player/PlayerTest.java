package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.fixture.FixedSequenceDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.card.Symbol.CLOVER;
import static blackjack.domain.card.Symbol.DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @Test
    @DisplayName("이름은 공백일 수 없다")
    void throwExceptionWhenNameLengthIsBlank() {
        Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, FIVE));

        assertThatThrownBy(() -> new Player(" ", deck.initialDraw()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이름은 6자를 초과할 수 없다")
    void throwExceptionWhenNameLengthOverMaxLength() {
        Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, FIVE));
        assertThatThrownBy(() -> new Player("1234567", deck.initialDraw()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("hit이 가능한 상태라면 참을 반환한다.")
    void testIsAbleToHit1() {
        // given
        Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, FIVE));
        Player player = new Player("pobi", deck.initialDraw());

        // when
        boolean actual = player.isAbleToHit();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("블랙잭이면 더이상 hit을 할 수 없다.")
    void testIsAbleToHit2() {
        // given
        Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, ACE), new Card(DIAMOND, JACK));
        Player player = new Player("pobi", deck.initialDraw());
        // when
        boolean actual = player.isAbleToHit();
        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("버스트면 더이상 hit을 할 수 없다.")
    void testIsAbleToHit3() {
        // given
        Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, FIVE), new Card(DIAMOND, SEVEN));
        Player player = new Player("pobi", deck.initialDraw());
        player.addCard(deck.draw());

        // when
        boolean actual = player.isAbleToHit();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("stay하면 더이상 hit을 할 수 없다.")
    void testIsAbleToHit4() {
        // given
        Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, FIVE));
        Player player = new Player("pobi", deck.initialDraw());

        // when
        player.stay();
        boolean actual = player.isAbleToHit();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("첫 공개 카드는 두 장을 반환한다")
    void testOpenCards() {
        Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, FIVE));
        Player player = new Player("pobi", deck.initialDraw());

        assertThat(player.openCards().size()).isEqualTo(2);
    }

}
