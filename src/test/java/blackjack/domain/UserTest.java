package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.player.Name;
import blackjack.domain.player.PlayerCards;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    @DisplayName("User의 total score가 21이 넘지 않으면 true를 반환한다.")
    void total_score_under_21() {
        // given & when
        PlayerCards playerCards = new PlayerCards();
        playerCards.updateCardScore(new Card(CardNumber.ACE, CardSymbol.HEART));
        playerCards.updateCardScore(new Card(CardNumber.FIVE, CardSymbol.HEART));
        User user = new Dealer(new Name("메리"), playerCards);

        // then
        Assertions.assertThat(user.isUnderScoreLimit()).isTrue();
    }

    @Test
    @DisplayName("User의 total score가 21이 넘으면 false를 반환한다.")
    void total_score_over_21() {
        // given & when
        PlayerCards playerCards = new PlayerCards();
        playerCards.updateCardScore(new Card(CardNumber.KING, CardSymbol.HEART));
        playerCards.updateCardScore(new Card(CardNumber.FIVE, CardSymbol.HEART));
        playerCards.updateCardScore(new Card(CardNumber.KING, CardSymbol.HEART));
        User user = new Dealer(new Name("메리"), playerCards);

        // then
        Assertions.assertThat(user.isUnderScoreLimit()).isFalse();
    }
}
