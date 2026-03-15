package team.blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.blackjack.service.dto.PlayerRequest;

class BlackjackGameTest {
    private List<PlayerRequest> playerRequests;
    private BlackjackGame blackjackGame;

    @BeforeEach
    void setUp() {
        playerRequests = List.of(new PlayerRequest("pobi", 10000),
                new PlayerRequest("pobi", 20000));
        blackjackGame = new BlackjackGame(playerRequests);
    }

    @Test
    void 플레이어는_처음_게임_시작시에_서로_다른_2장의_카드를_받는다() {
        Player newPlayer = new Player("pobi", 10000);

        blackjackGame.dealInitialCardsTo(newPlayer);

        assertThat(newPlayer.getHands().getFirst().getCards().size()).isEqualTo(2);
    }

    @Test
    void 딜러는_처음_게임_시작시에_서로_다른_2장의_카드를_받는다() {
        Dealer dealer = new Dealer();

        blackjackGame.dealInitialCardsTo(dealer);

        assertThat(dealer.getHand().getCards().size()).isEqualTo(2);
    }

    @Test
    void 플레이어에게_카드_한장을_발급한다() {
        Player player = new Player("pobi", 10000);

        blackjackGame.dealCardTo(player);

        assertThat(player.getHands().getFirst().getCards().size()).isEqualTo(1);
    }

    @Test
    void 딜러에게_카드_한장을_발급한다() {
        Dealer dealer = new Dealer();

        blackjackGame.dealCardTo(dealer);

        assertThat(dealer.getHand().getCards().size()).isEqualTo(1);
    }

    /**
     * 수익 금액 계산 테스트
     */
    @Test
    void 딜러의_수익금액은_플레이어의_수익합산의_음수이다() {
        Map<String, BigDecimal> playerPayouts = Map.of("pobi", BigDecimal.valueOf(-10000), "jason",
                BigDecimal.valueOf(20000));

        BigDecimal dealerPayout = blackjackGame.calculateDealerPayout(playerPayouts);

        assertThat(dealerPayout).isEqualTo(BigDecimal.valueOf(-10000));
    }
}
