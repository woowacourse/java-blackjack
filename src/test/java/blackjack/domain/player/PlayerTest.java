package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("참가자 테스트")
class PlayerTest {

    @DisplayName("플레이어 핸드에 카드를 추가할 수 있다")
    @Test
    void testAppendCardToPlayer() {
        PlayerName playerName = new PlayerName("pobi");

        Card card1 = new Card(CardShape.HEART, CardNumber.TEN);
        Card card2 = new Card(CardShape.CLUB, CardNumber.NINE);

        List<Card> hand = new ArrayList<>();
        hand.add(card1);
        hand.add(card2);

        Player player = new Player(playerName, new Hand(hand));

        Card card3 = new Card(CardShape.DIAMOND, CardNumber.TWO);
        player.appendCard(card3);

        assertThat(hand).containsExactly(card1, card2, card3);
    }
}