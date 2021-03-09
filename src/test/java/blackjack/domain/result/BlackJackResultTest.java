package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Nickname;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackJackResultTest {
    Dealer dealer;
    Player player1;
    Player player2;
    Players players;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        player1 = new Player(new Nickname("air"));
        player2 = new Player(new Nickname("pick"));

        dealer.firstDraw(
                new Card(Shape.SPADE, Denomination.SEVEN),
                new Card(Shape.SPADE, Denomination.ACE)
        );
        dealer.stay();

        player1.firstDraw(
                new Card(Shape.SPADE, Denomination.JACK),
                new Card(Shape.SPADE, Denomination.ACE)
        );
        player2.firstDraw(
                new Card(Shape.SPADE, Denomination.JACK),
                new Card(Shape.SPADE, Denomination.SEVEN)
        );
        player2.stay();

        players = new Players(Arrays.asList(player1, player2));
    }

    @Test
    @DisplayName("게임 결과 생성")
    void createBlackJackResult() {
        BlackJackResult blackJackResult = new BlackJackResult(players.verifyResultByCompareScore(dealer));
        assertThat(blackJackResult).isNotNull();
    }

    @Test
    @DisplayName("딜러의 승패 결과 확인")
    void getResultSucceed() {
        BlackJackResult blackJackResult = new BlackJackResult(players.verifyResultByCompareScore(dealer));
        Map<MatchResult, Integer> dealerResult = blackJackResult.getDealerResult();

        assertThat(dealerResult.get(MatchResult.WIN)).isEqualTo(1);
        assertThat(dealerResult.get(MatchResult.LOSE)).isEqualTo(1);
        assertThat(dealerResult.get(MatchResult.DRAW)).isEqualTo(0);
    }
}
