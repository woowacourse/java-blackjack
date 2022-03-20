package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Denomination;
import blackjack.domain.Fixtures;
import blackjack.domain.result.Match;
import blackjack.domain.state.Ready;

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
        Ready ready = new Ready();
        Dealer dealer = new Dealer("딜러", ready);
        PlayingCard playingCard = Fixtures.SPADE_ACE;
        dealer.getState().draw(playingCard);

        Dealer compareDealer = new Dealer("딜러", ready);
        compareDealer.getState().draw(playingCard);

        assertThat(dealer).isEqualTo(compareDealer);
    }

    @Test
    @DisplayName("딜러가 카드를 더 받을 수 있는지 확인: 16을 넘는 경우")
    public void checkDealerCantHit() {
        Dealer dealer = new Dealer();
        dealer.getState().draw(Fixtures.SPADE_JACK);
        dealer.getState().draw(Fixtures.SPADE_NINE);

        assertThat(dealer.isHit()).isFalse();
    }

    @Test
    @DisplayName("딜러가 카드를 더 받을 수 있는지 확인: 16을 넘지 않는 경우")
    public void checkDealerCanHit() {
        Dealer dealer = new Dealer();
        dealer.getState().draw(Fixtures.SPADE_EIGHT);
        dealer.getState().draw(Fixtures.SPADE_TWO);

        assertThat(dealer.isHit()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"SPADE:TWO:JACK:WIN", "SPADE:JACK:TWO:LOSE"}, delimiter = ':')
    @DisplayName("딜러 승패 확인")
    void checkDealerResult(Suit suit, Denomination denomination, Denomination secondDenomination, Match expected) {
        Guest guest = new Guest("guest", new Ready(), 100);
        guest.getState().draw(new PlayingCard(suit, denomination));
        guest.changeState(guest.getState().stay());

        Dealer dealer = new Dealer();
        dealer.getState().draw(new PlayingCard(suit, secondDenomination));
        dealer.changeState(dealer.getState().stay());

        assertThat(dealer.getState().matchResult(guest)).isEqualTo(expected);
    }
}
