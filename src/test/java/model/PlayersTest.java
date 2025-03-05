package model;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    @DisplayName("여러명의_플레이어가_생성됐는지_확인")
    void newPlayers() {
        // given
        List<Player> actual = List.of(
                Player.from("pobi"),
                Player.from("hippo")
        );
        // when
        Players players = Players.from(List.of("pobi","hippo"));
        // then
        assertAll(
                () -> Assertions.assertThat(players.getPlayers().size()).isEqualTo(2),
                () -> Assertions.assertThat(players.getPlayers()).containsAll(actual)
        );
    }

    @Test
    @DisplayName("플레이어의 핸즈에 있는 카드 랭크의 합을 잘 구하는 지")
    void sum() {
        // given
        // 총 합이 9
        List<Card> divideCards = List.of(
                new Card(SuitType.HEARTS, RankType.FIVE),
                new Card(SuitType.CLUBS, RankType.FOUR)
        );
        int expected = divideCards.stream()
                .mapToInt(card -> card.getRank().getScore().getFirst())
                .sum();

        String nickname = "pobi";
        Player player = Player.from(nickname);
        player.addCards(divideCards);

        // when
        int sum = player.sum();

        // then
        Assertions.assertThat(sum).isEqualTo(expected);
    }
}
