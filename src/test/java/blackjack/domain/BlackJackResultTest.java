package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackJackResultTest {

    @Test
    @DisplayName("게임 결과 생성")
    void createBlackJackResult() {
        BlackJackResult blackJackResult = new BlackJackResult(new Players("pika, air"), new Dealer());
        assertThat(blackJackResult).isNotNull();
    }

    @Test
    @DisplayName("딜러의 승패 결과 확인")
    void getResultSucceed() {
        Players players = new Players("pika, air");
        Dealer dealer = new Dealer();

        for (Player player : players.getPlayers()) {
            player.receiveCard(new Card(Shape.SPADE, Denomination.JACK));
            player.receiveCard(new Card(Shape.SPADE, Denomination.ACE));
        }

        dealer.receiveCard(new Card(Shape.SPADE, Denomination.FIVE));
        dealer.receiveCard(new Card(Shape.SPADE, Denomination.ACE));

        BlackJackResult blackJackResult = new BlackJackResult(players, dealer);
        Map<MatchResult, Integer> dealerResult = blackJackResult.getDealerResult();

        assertThat(dealerResult.get(MatchResult.WIN)).isEqualTo(0);
        assertThat(dealerResult.get(MatchResult.LOSE)).isEqualTo(2);
        assertThat(dealerResult.get(MatchResult.DRAW)).isEqualTo(0);
    }
}
