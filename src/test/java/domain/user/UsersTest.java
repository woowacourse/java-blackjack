package domain.user;

import static domain.card.Denomination.ACE;
import static domain.card.Denomination.JACK;
import static domain.card.Denomination.QUEEN;
import static domain.card.Denomination.SEVEN;
import static domain.card.Denomination.TEN;
import static domain.card.Denomination.THREE;
import static domain.card.Denomination.TWO;
import static domain.card.Suits.DIAMOND;
import static domain.card.Suits.HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.money.BettingAmount;
import domain.user.state.Stay;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class UsersTest {

    Users users;

    @BeforeEach
    void setUpUsers() {
        users = Users.from(List.of("hongo", "kiara", "ash"));
    }

    @DisplayName("참여 인원은 1명 이상 4명 이하이다")
    @ParameterizedTest
    @MethodSource("parameterProvider")
    void playerCount1_4(List<String> playerNames) {
        assertThatNoException()
            .isThrownBy(() -> Users.from(playerNames));
    }

    static Stream<List<String>> parameterProvider() {
        return Stream.of(
            List.of("hongo", "kiara", "ash", "woowa"),
            List.of("ash")
        );
    }

    @DisplayName("참여 인원은 1명미만이 될 수 없다")
    @Test
    void playerCount_shouldNotBeUnder1() {
        assertThatThrownBy(() -> Users.from(Collections.emptyList()))
            .hasMessageContaining("플레이어 수는 ")
            .hasMessageContaining("명 이상, ");
    }

    @DisplayName("참여 인원은 4명초과가 될 수 없다")
    @Test
    void playerCount_shouldNotBeOver4() {
        assertThatThrownBy(() -> Users.from(List.of("a", "b", "c", "d", "e")))
            .hasMessageContaining("플레이어 수는 ")
            .hasMessageContaining("명 이하여야 합니다.");
    }

    @DisplayName("플레이어의 이름은 중복될 수 없다")
    @Test
    void nameNotDuplicated() {
        assertThatThrownBy(() -> Users.from(List.of("hongo", "hongo", "ash")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("플레이어 이름은 중복될 수 없습니다.");
    }

    @DisplayName("해당 이름의 플레이어에게 카드를 추가한다")
    @Test
    void hitCardByName() {
        users.hitCardByName("hongo", Card.of(JACK, HEART));
        Player player = users.getPlayers().get(0);
        assertThat(player.getScore()).isEqualTo(10);
    }

    @DisplayName("이름으로 카드추가시 해당 이름의 플레이어가 없을시 예외처리한다")
    @Test
    void hitCardByName_noSuchName_exception() {
        assertThatThrownBy(() -> users.hitCardByName("error", Card.of(JACK, HEART)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("해당 이름의 플레이어가 존재하지 않습니다.");
    }

    @DisplayName("Running상태인 딜러를 Stay상태로 바꾼다.")
    @Test
    void stayDealerIfRunning() {
        Dealer dealer = users.getDealer();
        dealer.hit(Card.of(TWO, HEART));
        dealer.hit(Card.of(THREE, HEART));
        users.stayDealerIfRunning();

        assertThat(dealer.getState()).isInstanceOf(Stay.class);
    }

    @DisplayName("Terminated 상태인 딜러는 Stay상태로 바꾸지 않는다.")
    @Test
    void notStayDealerIfTerminated() {
        Dealer dealer = users.getDealer();
        dealer.hit(Card.of(TEN, HEART));
        dealer.hit(Card.of(JACK, HEART));
        dealer.hit(Card.of(TWO, HEART));
        users.stayDealerIfRunning();

        assertThat(dealer.getState()).isNotInstanceOf(Stay.class);
    }

    @DisplayName("해당 이름의 플레이어가 베팅한다")
    @Test
    void bettingByName() {
        users.bettingByName("hongo", 10000);
        Player player = users.getPlayers().get(0);
        assertThat(player.getBettingAmount()).isEqualTo(BettingAmount.valueOf(10000));
    }

    @DisplayName("이름으로 베팅시 해당 이름의 플레이어가 없을시 예외처리한다")
    @Test
    void bettingByName_noSuchName_exception() {
        assertThatThrownBy(() -> users.bettingByName("error", 10000))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("해당 이름의 플레이어가 존재하지 않습니다.");
    }

    @DisplayName("카드를 더 받을 수 있는 플레이어 리스트를 반환한다")
    @Test
    void getHittablePlayers() {
        // 카드 현황
        // player1 : X          => 0
        // player2 : 7          => 7
        // player3 : 10, 10, 1  => 21
        users.hitCardByName("ash", Card.of(SEVEN, DIAMOND));
        users.hitCardByName("kiara", Card.of(JACK, DIAMOND));
        users.hitCardByName("kiara", Card.of(QUEEN, DIAMOND));
        users.hitCardByName("kiara", Card.of(ACE, DIAMOND));

        List<Player> hittablePlayers = users.getHittablePlayers();
        assertThat(hittablePlayers)
            .satisfiesExactly(
                player -> assertNameEquals(player, "hongo"),
                player -> assertNameEquals(player, "ash"));
    }

    private void assertNameEquals(Player player, String name) {
        assertThat(player.getName()).isEqualTo(name);
    }
}
