package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import blackjack.domain.BlackjackGame;
import blackjack.domain.Result;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackGameTest {

    private final BlackjackGame blackjackGame = new BlackjackGame();
    private final Participant dealer = new Dealer();
    private List<Participant> players = new ArrayList<>();

    @BeforeEach
    void setUp() {
        dealer.receiveCard(new Card(CardNumber.TEN, CardType.CLOVER));
        for (int i = 0; i < 3; i++) {
            Participant player = new Player(i + "");
            player.receiveCard(new Card(CardNumber.EIGHT, CardType.CLOVER));
            players.add(player);
        }
    }

    @Test
    @DisplayName("딜러의 승수가 잘 구해지는지 확인")
    void calculateDealerWinCount() {
        assertThat(blackjackGame.calculateDealerResult(dealer, players, Result.WIN)).isEqualTo(3);
    }

    @Test
    @DisplayName("딜러의 승무패 횟수가 잘 구해지는지 확인")
    void makeDealerMatchCounts() {
        final List<Integer> dealerCounts = blackjackGame.makeDealerMatchCounts(dealer, players);
        assertThat(dealerCounts).hasSize(4)
            .contains(3, 0, 0);
    }

    @Test
    @DisplayName("플레이어의 경기 결과가 잘 구해지는지 확인")
    void makePlayerResults() {
        final Map<Name, Result> playersResult = blackjackGame.makePlayerResults(players, dealer);
        assertThat(playersResult).hasSize(3)
            .contains(entry(new Name(0 + ""), Result.LOSE), entry(new Name(1 + ""), Result.LOSE),
                entry(new Name(2 + ""), Result.LOSE));
    }
}
