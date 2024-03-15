package blackjack.dto;

import blackjack.domain.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.stategy.NoShuffleStrategy;
import blackjack.fixture.PlayerFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("수익 계산")
class ProfitResultTest {

    private Player choco;
    private Player clover;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        Deck deck = new Deck(new NoShuffleStrategy());
        dealer = new Dealer(deck);
        choco = PlayerFixture.playerChoco(dealer);
        clover = PlayerFixture.playerClover(dealer);
    }

    @DisplayName("모든 사용자의 수익을 합해서 반환한다.")
    @Test
    void sumProfits() {
        //given
        ProfitResult profitResult = new ProfitResult();
        profitResult.addProfitResult(choco, new BigDecimal(1000));
        profitResult.addProfitResult(clover, new BigDecimal(1000));

        //when & then
        assertThat(profitResult.sumAllProfit()).isEqualTo(new BigDecimal(2000));
    }

    @DisplayName("존재하지 않는 사용자로 조회하면 예외가 발생한다.")
    @Test
    void findByPlayer() {
        //given
        ProfitResult profitResult = new ProfitResult();
        profitResult.addProfitResult(choco, new BigDecimal(1000));
        profitResult.addProfitResult(clover, new BigDecimal(1000));

        //when & then
        assertThatThrownBy(() -> profitResult.findByPlayer(new Player("nope", dealer)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
