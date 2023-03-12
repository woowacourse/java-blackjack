package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GuestsTest {
    @DisplayName("참여자가 1명 미만이면 예외를 던지는지 확인한다.")
    @Test
    void Should_ThrowException_When_GuestsNumbersUnderRange() {
        List<String> inputNames = Collections.emptyList();
        assertThatThrownBy(() -> Guests.validateGuestNames(inputNames))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("참여자는", "이상", "이하여야 합니다.");
    }

    @DisplayName("참여자가 10명 초과면 예외를 던지는지 확인한다.")
    @Test
    void Should_ThrowException_When_GuestsNumberOverRange() {
        List<String> inputNames = List.of("name1", "name2", "name3", "name4", "name5", "name6", "name7",
                "name8", "name9", "name10", "name11");
        assertThatThrownBy(() -> Guests.validateGuestNames(inputNames))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("참여자는", "이상", "이하여야 합니다.");
    }

    @DisplayName("중복된 이름이 있으면, 예외를 던지는지 확인한다.")
    @Test
    void Should_ThrowException_When_DuplicateGuestNames() {
        List<String> inputNames = List.of("name1", "name1");

        assertThrows(IllegalArgumentException.class, () -> Guests.validateGuestNames(inputNames));
    }

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
