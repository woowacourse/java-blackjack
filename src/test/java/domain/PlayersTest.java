package domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayersTest {
    List<PlayerName> usernames;
    List<BettingMoney> bettingMonies;

    @BeforeEach
    void makeTestPlayerNames() {
        usernames = Stream.of("김", "이", "박").map(PlayerName::new).toList();
        bettingMonies = Stream.of(10000, 10000, 10000).map(BettingMoney::new).toList();
    }

    @Test
    @DisplayName("특정 이름을 가진 플레이어에게 카드를 제공한다.")
    void giveCardToGamerTest() {
        // given
        Players players = new Players(usernames, bettingMonies);
        PlayerName username = new PlayerName("이");
        Card card = Card.HEART_JACK;
        Cards newCards = new Cards(List.of(card));
        // when
        players.giveCardsToPlayer(username, newCards);
        // then
        Cards cards = players.getPlayerCard(username);
        assertTrue(cards.hasCommonCard(newCards));
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("특정 플레이어가 카드를 더 받을 수 있는지 확인합니다.")
    void canGetMoreCardTest(List<Card> cards, boolean expected) {
        //given
        Players players = new Players(usernames, bettingMonies);
        players.giveCardsToPlayer(new PlayerName("김"), new Cards(List.of(Card.HEART_TEN)));
        players.giveCardsToPlayer(new PlayerName("김"), new Cards(cards));

        //when & then
        Assertions.assertThat(players.isDrawable(new PlayerName("김"))).isEqualTo(expected);
    }

    public static Stream<Arguments> canGetMoreCardTest() {
        return Stream.of(
                Arguments.of(List.of(Card.HEART_ACE), false),
                Arguments.of(List.of(Card.DIA_JACK), true),
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_QUEEN), false),
                Arguments.of(List.of(Card.HEART_QUEEN, Card.CLOVER_THREE), false),
                Arguments.of(List.of(Card.DIA_JACK, Card.CLOVER_TWO), false)
        );
    }

    @Test
    @DisplayName("플레이어가 6명 이상이면 예외를 반환합니다.")
    void validatePlayerQuantityTest() {
        usernames = Stream.of("김", "이", "박", "정", "최", "유").map(PlayerName::new).toList();
        bettingMonies = Stream.of(10000, 10000, 10000, 10000, 10000, 10000).map(BettingMoney::new).toList();

        Assertions.assertThatThrownBy(() -> new Players(usernames, bettingMonies))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 최대 5명까지 참가 가능합니다.");
    }
}
