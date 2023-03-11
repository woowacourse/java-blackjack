package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantProfitsTest {

    @Test
    @DisplayName("딜러의 총 수익과 참여자의 수익금이 정확히 계산되어야 한다.")
    void create_success() {
        // given
        Player player1 = new Player("glen", List.of(
                Card.of(Suit.DIAMOND, Rank.ACE),
                Card.of(Suit.DIAMOND, Rank.KING)
        ), Bet.of(100));
        Player player2 = new Player("glen", List.of(
                Card.of(Suit.DIAMOND, Rank.KING),
                Card.of(Suit.DIAMOND, Rank.KING)
        ), Bet.of(100));
        Player player3 = new Player("glen", List.of(
                Card.of(Suit.DIAMOND, Rank.KING),
                Card.of(Suit.DIAMOND, Rank.KING),
                Card.of(Suit.DIAMOND, Rank.KING)
        ), Bet.of(100));
        Dealer dealer = new Dealer(List.of(
                Card.of(Suit.DIAMOND, Rank.KING),
                Card.of(Suit.DIAMOND, Rank.SEVEN)
        ));
        // when
        ParticipantProfits participantProfits = ParticipantProfits.of(List.of(player1, player2, player3), dealer);

        // then
        assertThat(participantProfits.getParticipantProfits())
                .hasSize(4);
        assertThat(participantProfits.getParticipantProfits().get(0).getProfit())
                .isEqualTo(-150);
        assertThat(participantProfits.getParticipantProfits().get(1).getProfit())
                .isEqualTo(150);
        assertThat(participantProfits.getParticipantProfits().get(2).getProfit())
                .isEqualTo(100);
        assertThat(participantProfits.getParticipantProfits().get(3).getProfit())
                .isEqualTo(-100);
    }
}
