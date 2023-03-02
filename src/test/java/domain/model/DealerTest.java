package domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import domain.type.Letter;
import domain.type.Suit;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("21 이하일 경우 카드 추가를 테스트")
    public void testAddCardWhenUnder21() {
        //given
        Set<Card> cardSet = new HashSet<>();
        cardSet.add(new Card(Suit.SPADE, Letter.NINE));
        Cards cards = new Cards(cardSet);
        Dealer dealer = new Dealer(cards);

        //when
        dealer.addCard(new Card(Suit.DIAMOND, Letter.NINE));
        dealer.addCard(new Card(Suit.SPADE, Letter.THREE));

        //then
        assertThat(dealer.getScore().getValue()).isEqualTo(21);
    }

    @Test
    @DisplayName("21 초과일 경우 카드 추가를 테스트")
    public void testAddCardWhenOver21() {
        //given
        Set<Card> cardSet = new HashSet<>();
        cardSet.add(new Card(Suit.SPADE, Letter.TEN));
        Cards cards = new Cards(cardSet);
        Dealer dealer = new Dealer(cards);

        //when
        dealer.addCard(new Card(Suit.SPADE, Letter.TEN));
        dealer.addCard(new Card(Suit.SPADE, Letter.ACE));

        //then
        assertThat(dealer.getScore().getValue()).isEqualTo(21);
    }

    @Test
    @DisplayName("카드를 더 받을 수 없는 테스트")
    public void testCanReceiveCard() {
        //given
        Cards cards = new Cards(Set.of(new Card(Suit.SPADE, Letter.SIX), new Card(Suit.SPADE, Letter.ACE)));
        Dealer dealer = new Dealer(cards);

        //when
        boolean result = dealer.canReceiveCard();

        //then
        assertFalse(result);
    }
}
