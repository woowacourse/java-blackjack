package model.participant;

import static org.junit.jupiter.api.Assertions.assertAll;
import static util.TestCardDistributor.divideCardToPlayer;

import java.util.List;
import java.util.stream.Stream;

import card.Card;
import card.Suit;
import card.NormalRank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import participant.Player;
import participant.Players;

public class PlayersTest {

    @Test
    @DisplayName("여러명의_플레이어가_생성됐는지_확인")
    void newPlayers() {
        // given
        List<Player> actual = List.of(
                new Player("pobi"),
                new Player("hippo")
        );
        // when
        Players players = Players.from(List.of("pobi", "hippo"));
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
        List<Card> cards = List.of(
                new Card(Suit.HEARTS, NormalRank.FIVE),
                new Card(Suit.CLUBS, NormalRank.FOUR)
        );
        int expected = cards.stream()
                .mapToInt(card -> card.getRank().getScore())
                .sum();

        Player player = new Player("pobi");
        divideCardToPlayer(cards, player);

        // when
        int sum = player.getScore().getValue();

        // then
        Assertions.assertThat(sum).isEqualTo(expected);
    }

    @Test
    @DisplayName("중복된 플레이어가 존재하는 지")
    void validateDuplication() {
        //given
        List<String> actual = List.of(
                "pobi",
                "pobi"
        );
        //when
        //then
        Assertions.assertThatThrownBy(() -> Players.from(actual))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("createPlayers")
    @DisplayName("참여 가능한 플레이어 수가 아닐 때 예외 처리")
    void validateNumber(List<String> values) {
        //given
        //when
        //then
        Assertions.assertThatThrownBy(() -> Players.from(values))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> createPlayers() {
        return Stream.of(
                Arguments.arguments(
                        List.of(
                        )
                ),
                Arguments.arguments(
                        List.of("Adam", "Alan", "Alex", "Andy", "Brad", "Carl", "Cody", "Dale",
                                "Drew", "Eric", "Evan", "Gary", "Glen", "Hank", "Jack", "Jake",
                                "Jeff", "Joel", "John", "Josh", "Kirk", "Leon", "Mark", "Matt",
                                "Mike", "Nick", "Paul", "Rick", "Sean", "Wade", "hippo")
                )
        );
    }
}
