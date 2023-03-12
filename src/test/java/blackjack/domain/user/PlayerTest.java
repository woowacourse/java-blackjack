package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("Player의 total score가 21이 넘지 않으면 false를 반환한다.")
    void total_score_under_21() {
        // given
        String name = "merry";

        // when
        Player player = new Player(new Name(name));
        player.handCards.updateCardScore(new Card(CardNumber.ACE, CardSymbol.HEART));
        player.handCards.updateCardScore(new Card(CardNumber.FIVE, CardSymbol.HEART));

        // then
        assertThat(player.isBust()).isFalse();
    }

    @Test
    @DisplayName("User의 total score가 21이 넘으면 true를 반환한다.")
    void total_score_over_21() {
        // given
        String name = "merry";

        // given & when
        Player player = new Player(new Name(name));
        player.handCards.updateCardScore(new Card(CardNumber.KING, CardSymbol.HEART));
        player.handCards.updateCardScore(new Card(CardNumber.FIVE, CardSymbol.HEART));
        player.handCards.updateCardScore(new Card(CardNumber.KING, CardSymbol.HEART));

        // then
        assertThat(player.isBust()).isTrue();
    }

    @Test
    @DisplayName("Dealer가 블랙잭이 아닐 때, Player가 블랙잭이라면 베팅 금액의 1.5배를 받는다.")
    void player_blackjack() {
        // given
        String name = "merry";
        int betAmount = 10000;
        Player player = new Player(new Name(name));
        player.setBetAmount(betAmount);

        // when
        player.handCards.updateCardScore(new Card(CardNumber.ACE, CardSymbol.HEART));
        player.handCards.updateCardScore(new Card(CardNumber.KING, CardSymbol.HEART));
        player.checkBlackjack(false);

        // then
        assertThat(player.getReceivingAmount()).isEqualTo(betAmount * 1.5);
    }
}
