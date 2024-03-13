package blackjack.dto;

import blackjack.domain.Deck;
import blackjack.domain.GameResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.stategy.NoShuffleStrategy;
import blackjack.fixture.PlayerFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("수익 계산")
class ProfitsTest {

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
        Profits profits = new Profits();
        profits.addProfit(choco, GameResult.WIN);
        profits.addProfit(clover, GameResult.WIN);

        //when & then
        assertThat(profits.sumProfits()).isEqualTo(2000);
    }

    @DisplayName("존재하지 않는 사용자로 조회하면 예외가 발생한다.")
    @Test
    void findByPlayer() {
        //given
        Profits profits = new Profits();
        profits.addProfit(choco, GameResult.WIN);
        profits.addProfit(clover, GameResult.WIN);

        //when & then
        assertThatThrownBy(() -> profits.findByPlayer(new Player("nope", dealer, "1000")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}