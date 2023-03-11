package domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.type.Letter;
import domain.type.Suit;
import domain.vo.Bet;
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
        final Bet bet = Bet.of(1000D);

        //when
        //then
        assertDoesNotThrow(() -> new Player(cards, name, bet));
    }

    @Test
    @DisplayName("21 이하일 경우 카드 추가를 테스트")
    public void testAddCardWhenUnder21() {
        //given
        final Set<Card> cardSet = new HashSet<>();
        cardSet.add(new Card(Suit.SPADE, Letter.NINE));
        final Cards cards = new Cards(cardSet);
        final Player player = new Player(cards, "player", Bet.of(1000D));

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
        final Player player = new Player(cards, "player", Bet.of(1000D));

        //when
        player.addCard(new Card(Suit.SPADE, Letter.TEN));
        player.addCard(new Card(Suit.SPADE, Letter.ACE));

        //then
        assertThat(player.getScore().getValue()).isEqualTo(21);
    }

    @Test
    @DisplayName("플레이어가 카드를 받을 수 있는 상태인지 테스트")
    public void testCanReceiveCardTrue() {
        //given
        final Set<Card> cardSet = Set.of(
            new Card(Suit.DIAMOND, Letter.TEN),
            new Card(Suit.SPADE, Letter.TEN),
            new Card(Suit.CLUB, Letter.ACE));
        final Cards bustedCards = new Cards(cardSet);
        final Player player = new Player(bustedCards, "test", Bet.of(1000D));

        //when
        final boolean result = player.canReceiveCard();

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("플레이어가 카드를 받을 수 없는 상태인지 테스트")
    public void testCanReceiveCardFalse() {
        //given
        final Set<Card> cardSet = Set.of(
            new Card(Suit.DIAMOND, Letter.TEN),
            new Card(Suit.SPADE, Letter.TEN),
            new Card(Suit.CLUB, Letter.ACE));
        final Cards notBustedCards = new Cards(cardSet);
        final Player player = new Player(notBustedCards, "test", Bet.of(1000D));

        //when
        final boolean result = player.canNotReceiveCard();

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
        final Player player = new Player(cards, "test", Bet.of(1000D));

        //when
        final boolean result = player.cardsIsNotEmpty();

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("플레이어의 카드가 비었음을 테스트")
    public void testCardsIsNotEmptyFalse() {
        //given
        final Player player = new Player(Cards.makeEmpty(), "test", Bet.of(1000D));

        //when
        final boolean result = player.cardsIsNotEmpty();

        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("블랙잭인지 테스트")
    public void testIsBlackJack() {
        //given
        final Cards blackJackCards = new Cards(Set.of(
            new Card(Suit.CLUB, Letter.TEN),
            new Card(Suit.SPADE, Letter.ACE)
        ));
        final Player player = new Player(blackJackCards, "test", Bet.of(1000D));

        //when
        boolean result = player.isBlackJack();

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("블랙잭이 아닌지 테스트")
    public void testIsNotBlackJack() {
        //given
        final Cards blackJackCards = new Cards(Set.of(
            new Card(Suit.CLUB, Letter.TEN),
            new Card(Suit.SPADE, Letter.TEN)
        ));
        final Player player = new Player(blackJackCards, "test", Bet.of(1000D));

        //when
        boolean result = player.isNotBlackJack();

        //then
        assertThat(result).isTrue();
    }
}
