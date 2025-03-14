package model.participant;

import model.card.Card;
import model.card.Rank;
import model.card.SingleScoreCard;
import model.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;

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
        List<Card> distributeCards = List.of(
                new SingleScoreCard(Suit.HEARTS, Rank.FIVE),
                new SingleScoreCard(Suit.CLUBS, Rank.FOUR)
        );
        int expected = distributeCards.stream()
                .mapToInt(Card::getScore)
                .sum();

        String nickname = "pobia";
        Player player = Player.from(nickname);
        player.addCards(distributeCards);

        // when
        int sum = player.getScore();

        // then
        Assertions.assertThat(sum).isEqualTo(expected);
    }

    @Test
    @DisplayName("중복된 플레이어가 존재하는 지")
    void validateDuplication() {
        //given
        List<String> actual = List.of(
                "pobib",
                "pobib"
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
