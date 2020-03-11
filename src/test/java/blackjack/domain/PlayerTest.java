package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    @DisplayName("사용자가 카드를 한 장 새로 받는 것 테스트")
    void receiveCard() {
        Card card1 = new Card(Suit.CLUB, Symbol.SIX);
        Card card2 = new Card(Suit.HEART, Symbol.KING);
        UserCards userCards = new UserCards(Arrays.asList(card1, card2));
        User player = new Player("pobi", userCards);
        player.receiveCard(new Card(Suit.DIAMOND, Symbol.TWO));
        assertThat(player.getTotalScore()).isEqualTo(18);
    }
}
