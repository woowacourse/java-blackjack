package domain.enums;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {

    @DisplayName("플레이어와 딜러 모두 블랙잭이면 플레이어 결과를 무승부로 반환한다.")
    @Test
    void 플레이어와_딜러_모두_블랙잭이면_플레이어_결과를_무승부로_반환한다() {
        Player player = new Player("피즈");
        Dealer dealer = new Dealer();

        List<Card> playerCards = List.of(new Card(Rank.ACE, Suit.SPADE), new Card(Rank.QUEEN, Suit.SPADE));
        List<Card> dealerCards = List.of(new Card(Rank.ACE, Suit.CLOVER), new Card(Rank.QUEEN, Suit.CLOVER));

        playerCards.forEach(player::addCard);
        dealerCards.forEach(dealer::addCard);

        assertThat(Result.calculatePlayerResult(dealer, player)).isEqualTo(Result.DRAW);
    }
}
