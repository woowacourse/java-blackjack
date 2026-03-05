package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

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
        
        cardProvider.provideInitCards(players, dealer);

        for (Player player : players) {
            assertThat(player.getCardStatus().getCards().size()).isEqualTo(2);
        }
        assertThat(dealer.getCardStatus().getCards().size()).isEqualTo(2);
    }
}