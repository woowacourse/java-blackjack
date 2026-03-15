package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.user.Dealer;
import blackjack.model.user.Player;
import blackjack.model.user.Users;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardProviderTest {

    @Test
    @DisplayName("딜러 및 플레이어에게 초기 카드 2장 정상 배분")
    void test_provide_init_cards() {
        CardProvider cardProvider = new CardProvider();

        List<Player> players = List.of(new Player("pobi"), new Player("james"));
        Dealer dealer = new Dealer();
        Users users = new Users(players, dealer);

        cardProvider.provideInitCards(users);

        for (Player player : players) {
            assertThat(player.cards().size()).isEqualTo(2);
        }
        assertThat(dealer.cards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드 추가 지급 성공")
    void test_provide_one_card_success() {
        Player player = new Player("pobi");
        CardProvider cardProvider = new CardProvider();

        cardProvider.provideOneCard(player);

        assertThat(player.cards().size()).isEqualTo(1);
    }

}