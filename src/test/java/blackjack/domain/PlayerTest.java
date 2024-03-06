package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    @DisplayName("보유하고 있는 카드합이 특정 값을 넘는지 확인할 수 있다")
    @Test
    void testHandSumExceedTarget() {
        PlayerName playerName = new PlayerName("pobi");

        Card card1 = new Card(CardShape.HEART, CardNumber.TEN);
        Card card2 = new Card(CardShape.CLUB, CardNumber.NINE);
        Card card3 = new Card(CardShape.CLUB, CardNumber.THREE);

        List<Card> hand = new ArrayList<>();
        hand.add(card1);
        hand.add(card2);
        hand.add(card3);

        Player player = new Player(playerName, new Hand(hand));

        boolean expected = player.handSummationExceed(21);

        assertThat(expected).isEqualTo(true);
    }
}