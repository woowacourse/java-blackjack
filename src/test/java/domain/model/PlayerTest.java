package domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import domain.type.Letter;
import domain.type.Suit;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("21 이하일 경우 카드 추가를 테스트")
    public void testAddCardWhenUnder21() {
        //given
        Set<Card> cardSet = new HashSet<>();
        cardSet.add(new Card(Suit.SPADE, Letter.NINE));
        Cards cards = new Cards(cardSet);
        Player player = new Player(cards);

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
        Set<Card> cardSet = new HashSet<>();
        cardSet.add(new Card(Suit.SPADE, Letter.TEN));
        Cards cards = new Cards(cardSet);
        Player player = new Player(cards);

        //when
        player.addCard(new Card(Suit.SPADE, Letter.TEN));
        player.addCard(new Card(Suit.SPADE, Letter.ACE));

        //then
        assertThat(player.getScore().getValue()).isEqualTo(21);
    }
}