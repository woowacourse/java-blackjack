package blackjack.domain.profit;

import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.GameResult;
import blackjack.domain.game.ResultStatus;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static blackjack.domain.card.Denomination.*;
import static blackjack.fixture.BattingAmountRepositoryFixture.배팅_금액_정보;
import static blackjack.fixture.CardFixture.카드;
import static blackjack.fixture.DealerFixture.딜러;
import static blackjack.fixture.PlayerFixture.플레이어들;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class ProfitCalculatorTest {
    private Dealer dealer;
    private Players players;
    private Player player;
    private BlackjackGame blackjackGame;
    private ProfitCalculator profitCalculator;

    @BeforeEach
    void setUp() {
        dealer = 딜러();
        players = 플레이어들("pobi");
        player = players.findPlayerByIndex(0);
        blackjackGame = new BlackjackGame(dealer, players);
        profitCalculator = new ProfitCalculator(배팅_금액_정보(player, 10000));
    }

    @Test
    void 플레이어가_블랙잭이라면_배팅_금액의_1_5배를_받는다() {
        dealer.receiveCard(카드(TEN));
        player.receiveCard(카드(ACE));
        player.receiveCard(카드(JACK));
        final GameResult gameResult = blackjackGame.compareDealerAndPlayers();
        final Map<Player, ResultStatus> result = gameResult.getResult();

        final ProfitResult profitResult = profitCalculator.calculate(dealer, result);

        assertThat(profitResult.getResult()).containsExactly(
                entry(dealer, -15000), entry(player, 15000)
        );
    }

    @Test
    void 플레이어가_블랙잭이이고_딜러가_블랙잭이면_배팅_금액을_돌려받는다() {
        dealer.receiveCard(카드(ACE));
        dealer.receiveCard(카드(TEN));
        player.receiveCard(카드(ACE));
        player.receiveCard(카드(JACK));
        final GameResult gameResult = blackjackGame.compareDealerAndPlayers();
        final Map<Player, ResultStatus> result = gameResult.getResult();

        final ProfitResult profitResult = profitCalculator.calculate(dealer, result);

        assertThat(profitResult.getResult()).containsExactly(
                entry(dealer, 0), entry(player, 0)
        );
    }

    @Test
    void 플레이어가_이겼다면_배팅_금액을_얻는다() {
        dealer.receiveCard(카드(NINE));
        dealer.receiveCard(카드(SIX));
        player.receiveCard(카드(TEN));
        player.receiveCard(카드(SIX));
        player.stay();
        final GameResult gameResult = blackjackGame.compareDealerAndPlayers();
        final Map<Player, ResultStatus> result = gameResult.getResult();

        final ProfitResult profitResult = profitCalculator.calculate(dealer, result);

        assertThat(profitResult.getResult()).containsExactly(
                entry(dealer, -10000), entry(player, 10000)
        );
    }

    @Test
    void 플레이어가_졌다면_배팅_금액을_잃는다() {
        dealer.receiveCard(카드(TEN));
        dealer.receiveCard(카드(SIX));
        player.receiveCard(카드(FIVE));
        player.receiveCard(카드(FOUR));
        player.stay();
        final GameResult gameResult = blackjackGame.compareDealerAndPlayers();
        final Map<Player, ResultStatus> result = gameResult.getResult();

        final ProfitResult profitResult = profitCalculator.calculate(dealer, result);

        assertThat(profitResult.getResult()).containsExactly(
                entry(dealer, 10000), entry(player, -10000)
        );
    }

    @Test
    void 플레이어가_딜러와_비겼다면_배팅_금액을_돌려받는다() {
        dealer.receiveCard(카드(TEN));
        dealer.receiveCard(카드(JACK));
        player.receiveCard(카드(KING));
        player.receiveCard(카드(QUEEN));
        player.stay();
        final GameResult gameResult = blackjackGame.compareDealerAndPlayers();
        final Map<Player, ResultStatus> result = gameResult.getResult();

        final ProfitResult profitResult = profitCalculator.calculate(dealer, result);

        assertThat(profitResult.getResult()).containsExactly(
                entry(dealer, 0), entry(player, 0)
        );
    }
}
