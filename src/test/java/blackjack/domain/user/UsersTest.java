package blackjack.domain.user;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UsersTest {
    private Users users;

    @BeforeEach
    public void setUp() {
        users = new Users(new Dealer(),
                Arrays.asList("amazzi", "dani", "pobi"),
                Arrays.asList(10000, 10000, 20000));
    }

    @DisplayName("이름별로 참여자들을 생성한다.")
    @Test
    void createPlayers() {
        assertThat(users).isInstanceOf(Users.class);
    }

    @DisplayName("각 플레이어에게 초기에 카드 두장을 배분한다.")
    @Test
    void DistributeToEachPlayer() {
        Deck deck = new Deck();
        users.distributeToPlayer(deck);

        assertThat(users.getPlayers()
                .stream()
                .allMatch(user -> user.cards.getCards().size() == 2)).isTrue();
    }

    @DisplayName("각 플레이어의 모든 카드를 보여준다.")
    @Test
    void showCardsByPlayers() {
        Deck deck = new Deck();
        users.distributeToPlayer(deck);
        List<Cards> cardsGroup = users.showCardsByPlayers();

        assertThat(cardsGroup.stream()
                .allMatch(cards -> cards.getCards().size() == 2)).isTrue();
    }

    @DisplayName("플레이어 이름들을 확인한다.")
    @Test
    void getNames() {
        List<String> namesGroup = users.getPlayerNames();

        assertThat(namesGroup).isEqualTo(Arrays.asList("amazzi", "dani", "pobi"));
    }

    @DisplayName("Players 일급 컬렉션을 반환한다.")
    @Test
    void getPlayers() {
        assertThat(users.getPlayers()).hasSize(3);
    }
}
