package blackjackgame.domain;

class PlayersTest {
    /*@DisplayName("참여자가 1명 미만이면 예외를 던지는지 확인한다.")
    @Test
    void Should_ThrowException_When_GuestsNumbersUnderRange() {
        List<Guest> emptyGuests = Collections.emptyList();

        assertThatThrownBy(() -> new Players(emptyGuests))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("참여자는", "이상", "이하여야 합니다.");
    }

    @DisplayName("참여자가 10명 초과면 예외를 던지는지 확인한다.")
    @Test
    void Should_ThrowException_When_GuestsNumberOverRange() {
        List<Guest> overTenCountGuests = Stream.of("name1", "name2", "name3", "name4", "name5", "name6", "name7",
                "name8",
                "name9", "name10", "name11")
            .map(name -> new Guest(new Name(name), new Hand(new Deck().firstPickCards())))
            .collect(Collectors.toList());

        assertThatThrownBy(() -> new Players(overTenCountGuests))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("참여자는", "이상", "이하여야 합니다.");
    }

    @DisplayName("중복된 이름이 있으면, 예외를 던지는지 확인한다.")
    @Test
    void Should_ThrowException_When_DuplicateGuestNames() {
        List<Guest> sameNameGuests = Stream.of("name", "name")
            .map(name -> new Guest(new Name(name), new Hand(new Deck().firstPickCards())))
            .collect(Collectors.toList());

        assertThrows(IllegalArgumentException.class, () -> new Players(sameNameGuests));
    }*/

}
