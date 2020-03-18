package domain;

import domain.card.CardDeck;
import domain.player.Users;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public class UserFactoryTest {
    @DisplayName("팩토리에서 생성한 User 의 유효성 검증")
    @Test
    void createPlayersTest() {
        CardDeck cards = new CardDeck();
        Map<String, Integer> playerInformation = new LinkedHashMap<>();
        playerInformation.put("pobi", 10000);
        playerInformation.put("json", 20000);

        Assertions.assertThat(UserFactory.create(cards, playerInformation)).isInstanceOf(Users.class);
    }
}
