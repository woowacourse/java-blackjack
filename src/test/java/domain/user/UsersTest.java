package domain.user;

import static domain.card.Denomination.ACE;
import static domain.card.Denomination.TEN;
import static domain.card.Denomination.THREE;
import static domain.card.Denomination.TWO;
import static domain.card.Suits.HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.money.BettingAmount;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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

    @DisplayName("플레이어와 카드 맵을 반환한다")
    @Test
    void getPlayerToCard() {
        List<Player> players = users.getPlayers();
        players.get(1).hit(Card.of(TEN, HEART));
        players.get(2).hit(Card.of(TEN, HEART));
        players.get(2).hit(Card.of(TEN, HEART));

        Map<String, List<Card>> playerToCard = users.getPlayerToCard();

        assertThat(playerToCard.keySet())
            .containsExactly("hongo", "kiara", "ash");
        assertThat(playerToCard)
            .hasEntrySatisfying("hongo", cards -> assertSize(cards, 0))
            .hasEntrySatisfying("kiara", cards -> assertSize(cards, 1))
            .hasEntrySatisfying("ash", cards -> assertSize(cards, 2));
    }

    private void assertSize(List<Card> cards, int size) {
        assertThat(cards.size()).isEqualTo(size);
    }

    @DisplayName("플레이어와 점수 맵을 반환한다")
    @Test
    void getPlayerToScore() {
        List<Player> players = users.getPlayers();
        players.get(0).hit(Card.of(ACE, HEART));
        players.get(1).hit(Card.of(TWO, HEART));
        players.get(1).hit(Card.of(THREE, HEART));
        players.get(2).hit(Card.of(TEN, HEART));

        Map<String, Integer> playerToScore = users.getPlayerToScore();

        assertThat(playerToScore.keySet())
            .containsExactly("hongo", "kiara", "ash");
        assertThat(playerToScore)
            .hasEntrySatisfying("hongo", score -> assertThat(score).isEqualTo(11))
            .hasEntrySatisfying("kiara", score -> assertThat(score).isEqualTo(5))
            .hasEntrySatisfying("ash", score -> assertThat(score).isEqualTo(10));
    }
}
