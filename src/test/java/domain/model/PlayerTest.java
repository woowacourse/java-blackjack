package domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.type.Letter;
import domain.type.Suit;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어 생성 테스트")
    public void testCreate() {
        //given
        final Cards cards = Cards.makeEmpty();
        final String name = "test";

        //when
        //then
        assertDoesNotThrow(() -> new Player(cards, name));
    }

    @Test
    @DisplayName("21 이하일 경우 카드 추가를 테스트")
    public void testAddCardWhenUnder21() {
        //given
        final Set<Card> cardSet = new HashSet<>();
        cardSet.add(new Card(Suit.SPADE, Letter.NINE));
        final Cards cards = new Cards(cardSet);
        final Player player = new Player(cards, "player");

        //when
        player.addCard(new Card(Suit.DIAMOND, Letter.NINE));
        player.addCard(new Card(Suit.SPADE, Letter.THREE));

        //then
        assertThat(player.getScore().getValue()).isEqualTo(21);
    }

    @Test
    @DisplayName("21 초과일 경우 카드 추가를 테스트")
    public void testAddCardWhenOver21() {
        //given
        final Set<Card> cardSet = new HashSet<>();
        cardSet.add(new Card(Suit.SPADE, Letter.TEN));
        final Cards cards = new Cards(cardSet);
        final Player player = new Player(cards, "player");

        //when
        player.addCard(new Card(Suit.SPADE, Letter.TEN));
        player.addCard(new Card(Suit.SPADE, Letter.ACE));

        //then
        assertThat(player.getScore().getValue()).isEqualTo(21);
    }

    @Test
    @DisplayName("플레이어가 버스트인지 테스트")
    public void testIsBustTrue() {
        //given
        final Set<Card> cardSet = Set.of(
            new Card(Suit.DIAMOND, Letter.TEN),
            new Card(Suit.SPADE, Letter.TEN),
            new Card(Suit.CLUB, Letter.TWO));
        final Cards bustedCards = new Cards(cardSet);
        final Player player = new Player(bustedCards, "test");

        //when
        final boolean result = player.isBust();

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("플레이어가 버스트가 아닌지 테스트")
    public void testIsBustFalse() {
        //given
        final Set<Card> cardSet = Set.of(
            new Card(Suit.DIAMOND, Letter.TEN),
            new Card(Suit.SPADE, Letter.TEN),
            new Card(Suit.CLUB, Letter.ACE));
        final Cards notBustedCards = new Cards(cardSet);
        final Player player = new Player(notBustedCards, "test");

        //when
        final boolean result = player.isBust();

        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("플레이어의 카드가 비지 않았음을 테스트")
    public void testCardsIsNotEmptyTrue() {
        //given
        final Set<Card> cardSet = Set.of(
            new Card(Suit.CLUB, Letter.ACE));
        final Cards cards = new Cards(cardSet);
        final Player player = new Player(cards, "test");

        //when
        final boolean result = player.cardsIsNotEmpty();

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("플레이어의 카드가 비었음을 테스트")
    public void testCardsIsNotEmptyFalse() {
        //given
        final Player player = new Player(Cards.makeEmpty(), "test");

        //when
        final boolean result = player.cardsIsNotEmpty();

        //then
        assertThat(result).isFalse();
    }
}
