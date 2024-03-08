package blackjack.model.referee;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Denomination;
import blackjack.model.card.Suit;
import blackjack.model.cardgenerator.SequentialCardGenerator;
import blackjack.model.dealer.Dealer;
import blackjack.model.player.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RefereeTest {
    @Test
    @DisplayName("딜러와 플레이어의 카드 합을 비교하여 승패를 가린다")
    void determineOutcomeTest() {
        // given
        List<Card> dealerCards = List.of(
                new Card(Suit.HEART, Denomination.TEN),
                new Card(Suit.HEART, Denomination.TEN)
        );
        Dealer dealer = new Dealer(new SequentialCardGenerator(dealerCards));
        List<Card> playerCards = List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.HEART, Denomination.TWO)
        );
        Player player = new Player("mia", new SequentialCardGenerator(playerCards));

        // when
        Referee referee = new Referee(dealer);
        MatchResult matchResult = referee.determineMatchResult(player); // TODO: 접근제어자 default 인데 private으로 변경해야함

        // then
        assertThat(matchResult).isEqualTo(MatchResult.LOSE);
    }
}
