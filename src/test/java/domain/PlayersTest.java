package domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.card.Card;
import domain.participants.Gamer;
import domain.participants.PlayerName;
import domain.participants.Players;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayersTest {
    @Test
    @DisplayName("특정 이름을 가진 플레이어에게 카드를 제공한다.")
    void giveCardToGamerTest() {
        // given
        List<PlayerName> usernames = List.of(new PlayerName("김"), new PlayerName("이"), new PlayerName("박"));
        Players players = new Players(usernames);
        PlayerName username = new PlayerName("이");
        Card card = Card.HEART_JACK;
        // when
        players.giveCard(username, List.of(card));
        // then
        List<Card> cards = players.getPlayerCard(username);
        Assertions.assertThat(cards).contains(card);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("특정 플레이어가 카드를 더 받을 수 있는지 확인합니다.")
    void canGetMoreCardTest(List<Card> cards, boolean expected) {
        //given
        List<PlayerName> usernames = List.of(new PlayerName("김"), new PlayerName("이"), new PlayerName("박"));
        Players players = new Players(usernames);
        players.giveCard(new PlayerName("김"), cards);

        //when & then
        Assertions.assertThat(players.isDrawable(new PlayerName("김"))).isEqualTo(expected);
    }

    public static Stream<Arguments> canGetMoreCardTest() {
        return Stream.of(
                Arguments.of(List.of(Card.HEART_TEN, Card.HEART_ACE), true),
                Arguments.of(List.of(Card.HEART_TEN, Card.HEART_ACE, Card.CLOVER_QUEEN), true),
                Arguments.of(List.of(Card.HEART_TEN, Card.HEART_QUEEN, Card.CLOVER_THREE), false),
                Arguments.of(List.of(Card.HEART_TEN, Card.DIA_JACK, Card.CLOVER_TWO), false)
        );
    }

    @Test
    @DisplayName("플레이어가 모두 존재하는지 확인합니다.")
    void getPlayerInfoTest() {
        // given
        List<PlayerName> usernames = List.of(new PlayerName("김"), new PlayerName("이"), new PlayerName("박"));
        Players players = new Players(usernames);
        // when
        Map<PlayerName, Gamer> playerInfo = players.getPlayersInfo();

        // then
        assertTrue(
                playerInfo.keySet().containsAll(Set.of(new PlayerName("김"), new PlayerName("이"), new PlayerName("박"))));
    }
}