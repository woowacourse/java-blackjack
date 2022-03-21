package blackjack.dto;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.EIGHT;
import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.NINE;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Denomination.SEVEN;
import static blackjack.domain.card.Denomination.TEN;
import static blackjack.domain.card.Denomination.THREE;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.utils.ParticipantsCreationUtils.createDealerWithDenominations;
import static blackjack.utils.ParticipantsCreationUtils.playerBuilder;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.BettingReturn;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingReturnResponseTest {

    public static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    @DisplayName("딜러가 1승 1패일 경우에 대해 결과를 출력한다")
    void when_dealer_1win_1lose() {

        // given
        Dealer dealer = createDealerWithDenominations(THREE, NINE, EIGHT);

        Player pobi = playerBuilder()
                .name("pobi")
                .denominations(TWO, EIGHT, ACE)
                .bettingAmount(10_000)
                .build();

        Player jason = playerBuilder()
                .name("jason")
                .denominations(SEVEN, KING)
                .bettingAmount(20_000)
                .build();

        BettingReturn bettingReturn = BettingReturn.of(dealer, pobi, jason);
        BettingReturnResponse resultResponse = BettingReturnResponse.from(bettingReturn);

        String dealerRevenueMessage = resultResponse.getDealerRevenueMessage();
        String playersRevenueMessage = String.join(LINE_SEPARATOR, resultResponse.getPlayersRevenueMessage());

        // when & then
        assertThat(dealerRevenueMessage).isEqualTo("딜러: 10000");
        assertThat(playersRevenueMessage).isEqualTo("pobi: 10000" + LINE_SEPARATOR + "jason: -20000");
    }

    @Test
    @DisplayName("플레이어가 각각 블랙잭, 무승부일 경우에 대하여 결과를 출력한다")
    void when_players_1blackjack_1draw() {
        // given
        Dealer dealer = createDealerWithDenominations(THREE, NINE, EIGHT);

        Player pobi = playerBuilder()
                .name("pobi")
                .denominations(ACE, QUEEN)
                .bettingAmount(30_000)
                .build();

        Player jason = playerBuilder()
                .name("jason")
                .denominations(JACK, TEN)
                .bettingAmount(40_000)
                .build();

        BettingReturn bettingReturn = BettingReturn.of(dealer, pobi, jason);

        // when
        BettingReturnResponse resultResponse = BettingReturnResponse.from(bettingReturn);
        String dealerRevenueMessage = resultResponse.getDealerRevenueMessage();
        String playersRevenueMessage = String.join(LINE_SEPARATOR, resultResponse.getPlayersRevenueMessage());

        // then
        assertThat(dealerRevenueMessage).isEqualTo("딜러: -45000");
        assertThat(playersRevenueMessage).isEqualTo("pobi: 45000" + LINE_SEPARATOR + "jason: 0");
    }
}
