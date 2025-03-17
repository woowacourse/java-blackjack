package model.participant;

import model.card.Card;
import model.card.Rank;
import model.card.SingleScoreCard;
import model.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import setupSettings.PlayerGenerator;

import java.util.List;
import java.util.stream.Stream;

public class PlayersTest {

    Players players;

    @BeforeEach
    void setup() {
        List<Player> playerData = PlayerGenerator.generatePlayers(3);
        players = new Players(playerData);
    }

    @Test
    @DisplayName("여러명의_플레이어가_생성됐는지_확인")
    void newPlayers() {
        // given
        // when
        // then
        Assertions.assertThat(players.getPlayers().size()).isEqualTo(3);
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

        Player player = PlayerGenerator.generatePlayers(1).getFirst();
        player.addCards(distributeCards);

        // when
        int sum = player.getScore();

        // then
        Assertions.assertThat(sum).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("createPlayers")
    @DisplayName("참여 가능한 플레이어 수가 아닐 때 예외 처리")
    void validateNumber(List<Player> playerData) {
        //given
        //when
        //then
        Assertions.assertThatThrownBy(() -> new Players(playerData))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> createPlayers() {
        return Stream.of(
                Arguments.arguments(PlayerGenerator.generatePlayers(0)),
                Arguments.arguments(PlayerGenerator.generatePlayers(31))
        );
    }
}
