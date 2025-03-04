package domain;

import java.util.List;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @Test
    @DisplayName("특정 이름을 가진 플레이어에게 카드를 제공한다.")
    void giveCardToGamerTest() {
        // given
        List<String> usernames = List.of("a", "b", "c");
        Players players = new Players(usernames);
        String username = "a";
        Card card = Card.HEART_JACK;
        // when
        players.giveCard(username, card);
        // then
        Set<Card> cards = players.getPlayerCard(username);
        Assertions.assertThat(cards).contains(card);
    }
}