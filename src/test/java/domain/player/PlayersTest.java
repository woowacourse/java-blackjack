package domain.player;

import domain.card.Card;
import domain.card.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static domain.Textures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {

    @Test
    @DisplayName("딜러에게 특정한 카드를 줄 수 있다.")
    void whenGivingCardToDealer() {
        Dealer dealer = new Dealer(Hand.withEmptyHolder());
        Players players = Players.from(dealer, Collections.emptyList());

        Card card = SPADE_FOUR;
        players.giveCardByName(dealer.getName(), card);

        assertThat(dealer.getCards()).contains(card);
    }

    @Test
    @DisplayName("딜러가 가진 점수가 17점 이상이면 카드를 더 가질 수 없다.")
    void givenDealerOrMore17_thenDealerShouldNotGetMoreCard() {
        Dealer dealer = new Dealer(
                new Hand(List.of(
                        HEART_TEN, DIAMOND_SEVEN
                )));
        Players players = Players.from(dealer, Collections.emptyList());

        assertThat(players.shouldDealerGetCard()).isFalse();
    }

    @Test
    @DisplayName("딜러가 가진 점수가 16점 미만이면 카드를 더 가져야 한다.")
    void givenDealerOrLess16_thenDealerShouldGetMoreCard() {
        Dealer dealer = new Dealer(
                new Hand(List.of(
                        HEART_TEN, DIAMOND_FIVE
                )));
        Players players = Players.from(dealer, Collections.emptyList());

        assertThat(players.shouldDealerGetCard()).isTrue();
    }

    @Test
    @DisplayName("주어진 이름에 해당하는 플레이어를 찾을 수 있다.")
    void givenName_thenReturnsPlayer() {
        Gambler gambler = new Gambler(Hand.withEmptyHolder(), Name.of("테스트"), Bet.from(3000));
        Players players = Players.from(new Dealer(Hand.withEmptyHolder()), List.of(gambler));

        Player findPlayer = players.findByName("테스트");
        assertThat(findPlayer).isEqualTo(gambler);
    }

    @Test
    @DisplayName("주어진 이름에 해당하는 플레이어를 찾을 수 없으면 예외가 발생한다.")
    void givenInvalidName_thenThrowsException() {
        Gambler gambler = new Gambler(Hand.withEmptyHolder(), Name.of("테스트"), Bet.from(3000));
        Players players = Players.from(new Dealer(Hand.withEmptyHolder()), List.of(gambler));

        assertThatThrownBy(() -> players.findByName("없는회원"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}