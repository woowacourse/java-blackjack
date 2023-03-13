package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GuestsTest {
    @DisplayName("Guests를 생성할 때 입력된 이름들의 수와 동일한 게스트들이 생성되었는지, 게스트들이 2개의 카드를 가지고 있는지 확인하다.")
    @Test
    void Should_GuestsHasSameNumber_When_GuestNamesNumber() {
        Deck deck = new Deck();
        List<Name> inputNames = List.of(new Name("name1"), new Name("name2"));
        List<BettingMoney> bettingMonies = List.of(BettingMoney.of(10000), BettingMoney.of(10000));
        List<Guest> guests = new Guests(inputNames, bettingMonies, deck).getGuests();

        assertThat(guests.size()).isEqualTo(inputNames.size());
        guests.forEach(guest -> assertThat(guest.getHand().size()).isEqualTo(2));
    }
}
