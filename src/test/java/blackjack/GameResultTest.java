package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.GameResult;
import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Player;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GameResultTest {
    private final GameResult gameResult = new GameResult();
    private Participant dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer("딜러");
        dealer.receiveCard(new Card(CardNumber.TEN, CardType.CLOVER));
    }

    @Test
    @DisplayName("딜러의 승수가 잘 구해지는지 확인")
    void calculateDealerWinCount() {
        final List<Participant> players = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Participant player = new Player(i + "");
            player.receiveCard(new Card(CardNumber.EIGHT, CardType.CLOVER));
            players.add(player);
        }
        assertThat(gameResult.calculateDealerWinCount(dealer, players)).isEqualTo(3);
    }

    @ParameterizedTest
    @DisplayName("승패에 따른 Result 객체가 잘 반환되는지 확인")
    @CsvSource(value = {"TWO:LOSE", "ACE:WIN"}, delimiter = ':')
    void decideWinner(final CardNumber input, final Result expected) {
        final Participant player = new Player("j.on");
        player.receiveCard(new Card(input, CardType.CLOVER));
        assertThat(gameResult.decideWinner(player, dealer)).isEqualTo(expected);
    }

}
