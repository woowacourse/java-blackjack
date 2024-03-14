package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.Players;
import blackjack.domain.card.Card;
import blackjack.domain.stategy.NoShuffleStrategy;
import blackjack.dto.ProfitResult;
import blackjack.strategy.ShuffleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.fixture.TrumpCardFixture.aceSpadeTrumpCard;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("딜러")
public class DealerTest {

    private final ShuffleStrategy shuffleStrategy = new NoShuffleStrategy();

    private Deck deck;
    private Dealer dealer;
    private final Card cardAceSpade = aceSpadeTrumpCard();

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
        Players players = Players.of(List.of("choco", "clover"), List.of("500000", "100000"), dealer);
        players.getPlayers().get(1).draw(dealer);

        ProfitResult profitResult = players.createProfitResult(dealer);

        //when & then
        assertThat(dealer.calculateProfit(profitResult)).isEqualTo(400000);
    }
}
