package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.CardValue;
import blackjackgame.domain.card.Symbol;
import blackjackgame.domain.player.Guest;
import blackjackgame.domain.player.Name;

class GuestTest {
    @DisplayName("게스트가 가진 카드들의 합이 21미만이면, true를 반환한다.")
    @Test
    void Should_CanPickMoreCard_When_ScoreUnder21() {
        Card five = new Card(Symbol.SPADE, CardValue.FIVE);
        Card eight = new Card(Symbol.CLOVER, CardValue.EIGHT);
        Guest guest = new Guest(new Name("name"), Arrays.asList(five, eight));

        assertThat(guest.canHit()).isTrue();
    }

    @DisplayName("게스트가 가진 카드들의 합이 21이상이면, false를 반환한다.")
    @Test
    void Should_CantPickMoreCard_When_ScoreOver21() {
        Card jack = new Card(Symbol.SPADE, CardValue.JACK);
        Card five = new Card(Symbol.CLOVER, CardValue.FIVE);
        Card ten = new Card(Symbol.HEART, CardValue.TEN);

        Guest guest = new Guest(new Name("name"), new ArrayList<>(List.of(jack, five)));
        guest.addCard(ten);

        assertThat(guest.canHit()).isFalse();
    }
}
