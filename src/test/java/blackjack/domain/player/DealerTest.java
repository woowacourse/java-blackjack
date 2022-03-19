package blackjack.domain.player;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.PlayingCards;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("dealer 객체 생성 확인")
    public void createDealer() {
       Dealer dealer = new Dealer();

       assertThat(dealer).isInstanceOf(Dealer.class);
    }

    @Test
    @DisplayName("카드를 할당받았는지 확인")
    public void checkAddCardToPlayingCard() {
        Dealer dealer = new Dealer();
        PlayingCard playingCard = new PlayingCard(Suit.SPADE, Denomination.FOUR);
        dealer.addCard(playingCard);

        Dealer compareDealer = new Dealer();
        compareDealer.addCard(playingCard);

        assertThat(dealer).isEqualTo(compareDealer);
    }

    @Test
    @DisplayName("딜러가 카드를 더 받을 수 있는지 확인: 16을 넘는 경우")
    public void checkDealerCantHit() {
        Set<PlayingCard> dealerCards = new HashSet<>();
        dealerCards.add(new PlayingCard(Suit.SPADE, Denomination.JACK));
        dealerCards.add(new PlayingCard(Suit.SPADE, Denomination.SEVEN));
        Dealer dealer = new Dealer("딜러", new PlayingCards(dealerCards));

        assertThat(dealer.isCanHit()).isFalse();
    }

    @Test
    @DisplayName("딜러가 카드를 더 받을 수 있는지 확인: 16을 넘지 않는 경우")
    public void checkDealerCanHit() {
        Set<PlayingCard> dealerCards = new HashSet<>();
        dealerCards.add(new PlayingCard(Suit.SPADE, Denomination.JACK));
        dealerCards.add(new PlayingCard(Suit.SPADE, Denomination.FIVE));
        Dealer dealer = new Dealer("딜러", new PlayingCards(dealerCards));

        assertThat(dealer.isCanHit()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"SPADE:TWO:JACK:true", "SPADE:JACK:TWO:false"}, delimiter = ':')
    @DisplayName("딜러 승패 확인")
    void checkDealerResult(Suit suit, Denomination denomination, Denomination secondDenomination, boolean expected) {
        Set<PlayingCard> guestCards = new HashSet<>();
        guestCards.add(new PlayingCard(suit, denomination));
        Guest guest = new Guest("guest", new PlayingCards(guestCards), 100);

        Set<PlayingCard> dealerCards = new HashSet<>();
        dealerCards.add(new PlayingCard(suit, secondDenomination));
        Dealer dealer = new Dealer("딜러", new PlayingCards(dealerCards));

        assertThat(dealer.isWin(guest)).isEqualTo(expected);
    }
}
