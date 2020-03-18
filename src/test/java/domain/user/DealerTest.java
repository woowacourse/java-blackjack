package domain.user;

import domain.card.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DealerTest {

    Deck deck = mock(Deck.class);

    @Test
    @DisplayName("첫턴에 딜러는 2장의 카드를 받는다.")
    void construct() {
        PlayingCards playingCards = new PlayingCards(Arrays.asList(new Card(Symbol.ACE, Type.DIAMOND),
                new Card(Symbol.TEN, Type.HEART)));
        assertThat(new Dealer(playingCards, deck)).isNotNull();
    }

    @Test
    @DisplayName("딜러의 카드를 추가한다.")
    void addCard() {
        PlayingCards playingCards = new PlayingCards(new ArrayList<>());
        Dealer dealer = new Dealer(playingCards, deck);
        Card card = new Card(Symbol.ACE, Type.CLOVER);
        dealer.addCard(card);

        assertThat(dealer.countCards()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러가 카드를 더 받을 수 있다.")
    void canGetExtraCard() {
        PlayingCards playingCards = new PlayingCards(Arrays.asList(new Card(Symbol.TWO, Type.DIAMOND),
                new Card(Symbol.TEN, Type.HEART)));
        Dealer dealer = new Dealer(playingCards, deck);

        assertThat(dealer.canGetExtraCard()).isTrue();
    }

    @Test
    @DisplayName("딜러가 더 카드를 받을 수 없다.")
    void canNotGetExtraCard() {
        PlayingCards playingCards = new PlayingCards(Arrays.asList(new Card(Symbol.ACE, Type.DIAMOND),
                new Card(Symbol.TEN, Type.HEART)));
        Dealer dealer = new Dealer(playingCards, deck);

        assertThat(dealer.canGetExtraCard()).isFalse();
    }

    @Test
    @DisplayName("#hit() : should add card without return")
    void hit() {
        //given
        PlayingCards playingCards = new PlayingCards(new ArrayList<>());
        Dealer dealer = new Dealer(playingCards, deck);
        int defaultSizeOfCards = dealer.countCards();
        when(deck.pop()).thenReturn(new Card(Symbol.QUEEN, Type.SPADE));

        //when
        dealer.hit();

        //then
        assertThat(dealer.countCards()).isEqualTo(defaultSizeOfCards + 1);
    }
}
