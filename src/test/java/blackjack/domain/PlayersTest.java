package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @DisplayName("모든 플레이어 카드 추가 기능 테스트")
    @Test
    void addCardToPlayersTest() {
        Players players = new Players(List.of("pobi", "jason"));
        Map<String, Card> cardForPlayers = new HashMap<>();

        Card cardForPobi = new Card("3다이아몬드", 3);
        cardForPlayers.put("pobi", cardForPobi);

        Card cardForJason = new Card("2다이아몬드", 2);
        cardForPlayers.put("jason", cardForJason);

        players.addCardToPlayers(cardForPlayers);

        boolean pobiCardAddSuccess = players.getPlayers()
                .get(0)
                .getMyCards()
                .contains(cardForPobi);
        boolean jasonCardAddSuccess = players.getPlayers()
                .get(1)
                .getMyCards()
                .contains(cardForJason);

        assertThat(pobiCardAddSuccess && jasonCardAddSuccess).isTrue();
    }

    @DisplayName("카드를 더 받을 수 있는 플레이어 이름 가져오는 기능 테스트")
    @Test
    void namesAbleToGetAdditionalCardTest() {
        Players players = new Players(List.of("pobi", "jason"));
        players.addCardToPlayers(Map.of("pobi", new Card("9다이아몬드", 9)
                , "jason", new Card("1다이아몬드", 1)));
        players.addCardToPlayers(Map.of("pobi", new Card("9하트", 9)
                , "jason", new Card("1하트", 1)));
        players.addCardToPlayers(Map.of("pobi", new Card("9클로버", 9)
                , "jason", new Card("1클로버", 1)));

        assertThat(players.namesAbleToGetAdditionalCard().size() == 1
                && players.namesAbleToGetAdditionalCard().get(0) == "jason").isTrue();
    }
}
