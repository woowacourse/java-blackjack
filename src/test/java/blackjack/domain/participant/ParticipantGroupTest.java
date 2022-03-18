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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantGroupTest {

    private Dealer dealer;
    private ParticipantGroup participantGroup;

    @BeforeEach
    void setUp() {
        dealer = Dealer.of(Hand.of(CLOVER4, CLOVER5));

        participantGroup = new ParticipantGroup(dealer);
        participantGroup.addPlayer(Player.of("winPlayer", Hand.of(CLOVER6, CLOVER7), Money.from(10000)));
        participantGroup.addPlayer(Player.of("blackjackWinPlayer", Hand.of(CLOVER_ACE, CLOVER10), Money.from(10000)));
        participantGroup.addPlayer(Player.of("losePlayer", Hand.of(CLOVER2, CLOVER3), Money.from(10000)));
    }

    @DisplayName("addPlayer 는 Player 인스턴스를 전달받고 Players 필드에 추가한다.")
    @Test
    void addPlayer_addsPlayerIntoPlayersAndReturnsPlayerCount() {
        // given
        ParticipantGroup participantGroup = ParticipantGroup.of(dealer);
        Player player = Player.of("hudi", Hand.of(CLOVER2, CLOVER3), Money.from(10000));

        // when
        participantGroup.addPlayer(player);
        int actual = participantGroup.getPlayers().getValue().size();
        int expected = 1;

        // then
        assertThat(actual).isEqualTo(expected);
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