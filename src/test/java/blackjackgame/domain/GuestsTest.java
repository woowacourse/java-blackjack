package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjackgame.domain.card.Deck;
import blackjackgame.domain.player.Guest;
import blackjackgame.domain.player.Guests;
import blackjackgame.domain.player.Name;

class GuestsTest {
    @DisplayName("참여자가 1명 미만이면 예외를 던지는지 확인한다.")
    @Test
    void Should_ThrowException_When_GuestsNumbersUnderRange() {
        List<Guest> emptyGuests = Collections.emptyList();

        assertThatThrownBy(() -> new Guests(emptyGuests))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("참여자는", "이상", "이하여야 합니다.");
    }

    @DisplayName("참여자가 10명 초과면 예외를 던지는지 확인한다.")
    @Test
    void Should_ThrowException_When_GuestsNumberOverRange() {
        List<Guest> overTenCountGuests = Stream.of("name1", "name2", "name3", "name4", "name5", "name6", "name7",
                "name8",
                "name9", "name10", "name11")
            .map(name -> new Guest(new Name(name), new Deck().firstPickCards()))
            .collect(Collectors.toList());

        assertThatThrownBy(() -> new Guests(overTenCountGuests))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("참여자는", "이상", "이하여야 합니다.");
    }

    @DisplayName("중복된 이름이 있으면, 예외를 던지는지 확인한다.")
    @Test
    void Should_ThrowException_When_DuplicateGuestNames() {
        List<Guest> sameNameGuests = Stream.of("name", "name")
            .map(name -> new Guest(new Name(name), new Deck().firstPickCards()))
            .collect(Collectors.toList());

        assertThrows(IllegalArgumentException.class, () -> new Guests(sameNameGuests));
    }

}
