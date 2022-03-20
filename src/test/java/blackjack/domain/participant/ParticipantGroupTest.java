package blackjack.domain.participant;

import static blackjack.domain.fixture.CardRepository.CLOVER10;
import static blackjack.domain.fixture.CardRepository.CLOVER2;
import static blackjack.domain.fixture.CardRepository.CLOVER3;
import static blackjack.domain.fixture.CardRepository.CLOVER4;
import static blackjack.domain.fixture.CardRepository.CLOVER5;
import static blackjack.domain.fixture.CardRepository.CLOVER6;
import static blackjack.domain.fixture.CardRepository.CLOVER7;
import static blackjack.domain.fixture.CardRepository.CLOVER_ACE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Hand;
import blackjack.domain.money.Money;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantGroupTest {


    private ParticipantGroup participantGroup;

    @BeforeEach
    void setUp() {
        Dealer dealer = Dealer.of(Hand.of(CLOVER4, CLOVER5));
        Players players = Players.of(List.of(
                Player.of("winPlayer", Hand.of(CLOVER6, CLOVER7), Money.from(10000)),
                Player.of("blackjackWinPlayer", Hand.of(CLOVER_ACE, CLOVER10), Money.from(10000)),
                Player.of("losePlayer", Hand.of(CLOVER2, CLOVER3), Money.from(10000))
        ));

        participantGroup = new ParticipantGroup(dealer, players);
    }

    @DisplayName("calculateDealerProfit 는 딜러의 수익을 Money 로 반환한다.")
    @Test
    void calculateDealerProfit_returnsProfitOfDealer() {
        // given & when
        Money actual = participantGroup.calculateDealerProfit();
        Money expected = Money.from(-15000);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}