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

    @Test
    @DisplayName("카드 추가 지급 성공")
    void test_provide_one_card_success() {
        Player player = new Player("pobi");
        CardProvider cardProvider = new CardProvider();

        cardProvider.provideOneCard(player);

        assertThat(player.getCardStatus().getCards().size()).isEqualTo(1);
    }


    @Test
    @DisplayName("플레이어의 카드 합계까 21 이상인 경우 true 반환")
    void test_isGreaterThanTwentyOne_return_true() {
        Player player = new Player("pobi");
        CardCalculator cardCalculator = new CardCalculator();
        CardProvider cardProvider = new CardProvider();

        player.addCard(Card.A_DIA);
        player.addCard(Card.J_DIA);
        player.addCard(Card.Q_CLOVER);

        boolean result = cardProvider.isGreaterThanTwentyOne(player, cardCalculator);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("플레이어의 카드 합계까 21 미만인 경우 false 반환")
    void test_isGreaterThanTwentyOne_return_false() {
        Player player = new Player("pobi");
        CardCalculator cardCalculator = new CardCalculator();
        CardProvider cardProvider = new CardProvider();

        player.addCard(Card.J_DIA);
        player.addCard(Card.Q_CLOVER);

        boolean result = cardProvider.isGreaterThanTwentyOne(player, cardCalculator);

        assertThat(result).isFalse();
    }
}