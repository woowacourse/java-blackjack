package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.Players;
import blackjack.domain.card.Card;
import blackjack.domain.stategy.NoShuffleStrategy;
import blackjack.dto.ProfitResult;
import blackjack.strategy.shuffle.ShuffleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static blackjack.fixture.CardFixture.aceSpadeCard;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("딜러")
public class DealerTest {

    private final ShuffleStrategy shuffleStrategy = new NoShuffleStrategy();

    private Deck deck;
    private Dealer dealer;
    private final Card cardAceSpade = aceSpadeCard();

    @BeforeEach
    void setUp() {
        deck = new Deck(shuffleStrategy);
        dealer = new Dealer(deck);
    }

    @DisplayName("딜러는 한 장을 뽑아서 손패에 넣는다.")
    @Test
    void drawExtraCard() {
        //when
        dealer.drawExtraCard();

        //then
        assertThat(dealer.getHandCards()).contains(cardAceSpade);
    }

    @DisplayName("딜러는 16점 이상이면 더 이상 카드를 받지 않는다.")
    @Test
    void cantDrawExtraCard() {
        //given
        for (int i = 0; i < 4; i++) {
            dealer.drawExtraCard();
        }

        //when
        dealer.drawExtraCard();

        //then
        assertThat(dealer.getScore()).isEqualTo(20L);
    }

    @DisplayName("딜러의 첫번째 카드를 공개한다.")
    @Test
    void showFirstCard() {
        //when & then
        assertThat(dealer.showFirstCard()).isEqualTo(cardAceSpade);
    }

    @DisplayName("딜러는 자신의 수익률을 계산한다.")
    @Test
    void calculateProfit() {
        //given
        Map<String, String> playersBetting = new HashMap<>();
        playersBetting.put("choco", "500000");
        playersBetting.put("clover", "100000");

        Players players = Players.of(playersBetting, dealer);
        players.getPlayers().get(1).draw(dealer);

        ProfitResult profitResult = players.createProfitResult(dealer);

        //when & then
        assertThat(dealer.calculateProfit(profitResult)).isEqualTo(BigDecimal.valueOf(400000));
    }
}
