package blackjackgame.domain;

import static blackjackgame.domain.CardFixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjackgame.domain.player.Guest;
import blackjackgame.domain.player.Name;

class GuestTest {
    @DisplayName("게스트가 가진 카드들의 합이 21미만이면, true를 반환한다.")
    @Test
    void Should_CanPickMoreCard_When_ScoreUnder21() {
        Guest guest = Guest.of(List.of(CLOVER_TEN, CLOVER_KING), new Name("name"), 500);

        assertThat(guest.canHit()).isTrue();
    }

    @DisplayName("게스트가 가진 카드들의 합이 21이상이면, false를 반환한다.")
    @Test
    void Should_CantPickMoreCard_When_ScoreOver21() {
        Guest guest = Guest.of(List.of(CLOVER_TEN, CLOVER_FIVE, CLOVER_KING), new Name("name"), 500);

        assertThat(guest.canHit()).isFalse();
    }

}
