package blackjack.domain;

import static org.assertj.core.api.Assertions.entry;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantResultsTest {

    private ParticipantResults participantResults;
    private final Participant dealer = new Dealer();
    private final List<Participant> players = new ArrayList<>();

    @BeforeEach
    void setUp() {
        dealer.receiveCard(new Card(CardNumber.TEN, CardType.CLOVER));
        for (int i = 0; i < 3; i++) {
            Participant player = new Player(i + "", 1000);
            player.receiveCard(new Card(CardNumber.EIGHT, CardType.CLOVER));
            players.add(player);
        }
        participantResults = new ParticipantResults(dealer, players);
    }

    @Test
    @DisplayName("딜러의 수익을 계산")
    void calculateTotalPlayersRate() {
        Assertions.assertThat(participantResults.calculateTotalPlayersRate()).isEqualTo(-3000);
    }

    @Test
    @DisplayName("참가자의 이름과 수익이 담긴 Map을 올바르게 반환하는지 확인")
    void makeParticipantResults() {
        final Map<Name, Integer> results = participantResults.makeParticipantResults();
        Assertions.assertThat(results).hasSize(4)
            .contains(entry(new Name("딜러"), 3000), entry(new Name("0"), -1000),
                entry(new Name("1"), -1000), entry(new Name("2"), -1000));
    }

}