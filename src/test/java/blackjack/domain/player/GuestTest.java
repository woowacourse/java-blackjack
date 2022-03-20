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
import blackjack.domain.state.*;

import static org.assertj.core.api.Assertions.assertThat;

class GuestTest {

    @Test
    @DisplayName("guest 객체 생성 확인")
    void createGuest() {
        Guest guest = Fixtures.guest;

        assertThat(guest).isInstanceOf(Guest.class);
    }

    @Test
    @DisplayName("게스트가 Bust인 경우")
    void checkGuestIsBust() {
        Guest guest = Fixtures.guest;
        guest.getState().draw(Fixtures.SPADE_JACK);
        guest.changeState(guest.getState().draw(Fixtures.SPADE_NINE));
        State state = guest.getState().draw(Fixtures.SPADE_EIGHT);

        assertThat(state.getClass()).isEqualTo(Bust.class);
    }

    @Test
    @DisplayName("게스트가 Bust가 아닌 경우: 21인 경우")
    void checkGuestIsNotBustAndMaxPoint() {
        Guest guest = new Guest("guest", new Ready(), 100);
        guest.getState().draw(Fixtures.SPADE_JACK);
        guest.changeState(guest.getState().draw(Fixtures.SPADE_NINE));
        State state = guest.getState().draw(Fixtures.SPADE_TWO);

        assertThat(state.getClass()).isEqualTo(Stay.class);
    }

    @Test
    @DisplayName("게스트가 Bust가 아닌 경우: 21보다 작은 경우")
    void checkGuestIsNotBust() {
        Guest guest = new Guest("guest", new Ready(), 100);
        guest.getState().draw(Fixtures.SPADE_JACK);
        guest.changeState(guest.getState().draw(Fixtures.SPADE_EIGHT));
        State state = guest.getState().draw(Fixtures.SPADE_TWO);

        assertThat(state.getClass()).isEqualTo(Hit.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"SPADE:TWO:JACK:LOSE", "SPADE:JACK:TWO:WIN"}, delimiter = ':')
    @DisplayName("플레이어 승패 확인")
    void checkPlayerResult(Suit suit, Denomination denomination, Denomination secondDenomination, Match expected) {
        Guest guest = new Guest("guest", new Ready(), 100);
        guest.getState().draw(new PlayingCard(suit, denomination));
        guest.changeState(guest.getState().stay());

        Dealer dealer = new Dealer();
        dealer.getState().draw(new PlayingCard(suit, secondDenomination));
        dealer.changeState(dealer.getState().stay());

        assertThat(guest.getState().matchResult(dealer)).isEqualTo(expected);
    }

    @Test
    @DisplayName("게스트 Hit 확인: 넘치지 않은 경우")
    void checkGuestCanHit() {
        Guest guest = Fixtures.guest;
        guest.getState().draw(Fixtures.SPADE_NINE);
        guest.getState().draw(Fixtures.SPADE_ACE);

        assertThat(guest.isHit()).isTrue();
    }

    @Test
    @DisplayName("게스트 Hit 확인: 넘친 경우")
    void checkGuestCantHit() {
        Guest guest = Fixtures.guest;
        guest.getState().draw(Fixtures.SPADE_NINE);
        guest.getState().draw(Fixtures.SPADE_EIGHT);
        guest.getState().draw(Fixtures.SPADE_JACK);

        assertThat(guest.isHit()).isFalse();
    }
}
