package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("Player의 total score가 21이 넘지 않으면 true를 반환한다.")
    void total_score_under_21() {
        // given & when
        Player player = new Player(new Name("merry"));
        player.handCards.updateCardScore(new Card(CardNumber.ACE, CardSymbol.HEART));
        player.handCards.updateCardScore(new Card(CardNumber.FIVE, CardSymbol.HEART));

        // then
        Assertions.assertThat(player.isUnderBust()).isTrue();
    }

    @Test
    @DisplayName("User의 total score가 21이 넘으면 false를 반환한다.")
    void total_score_over_21() {
        // given & when
        Player player = new Player(new Name("Merry"));
        player.handCards.updateCardScore(new Card(CardNumber.KING, CardSymbol.HEART));
        player.handCards.updateCardScore(new Card(CardNumber.FIVE, CardSymbol.HEART));
        player.handCards.updateCardScore(new Card(CardNumber.KING, CardSymbol.HEART));

        // then
        Assertions.assertThat(player.isUnderBust()).isFalse();
    }
}
