package blackjack.dto;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.strategy.ReverseCardShuffleStrategy;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.profit.ProfitResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class ProfitResultDtoTest {

    @Test
    void dto로_변환할_수_있다() {
        final Dealer dealer = new Dealer(new CardDeck(), new ReverseCardShuffleStrategy());
        final Player player = new Player("pobi");
        final ProfitResult profitResult = new ProfitResult();
        profitResult.recordParticipantsProfit(dealer, player, 10000);

        final ProfitResultDto profitResultDto = ProfitResultDto.from(profitResult);

        assertThat(profitResultDto.result()).containsExactly(
                entry("딜러", -10000), entry("pobi", 10000)
        );
    }
}
