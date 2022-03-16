package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("카드 받는 기능 테스트")
    @Test
    void addCard() {
        Player player = new Player("pobi");
        player.addCard(Card.generateCard(BlackjackCardType.DIAMOND_3));
        int playerCardSize = player.getMyCards().size();
        assertThat(playerCardSize).isEqualTo(1);
    }

    @DisplayName("스코어 21 넘으면 burst인지 확인하는 기능 true 리턴하는지 테스트")
    @Test
    void burst() {
        Player player = new Player("pobi");
        player.addCard(Card.generateCard(BlackjackCardType.DIAMOND_10));
        player.addCard(Card.generateCard(BlackjackCardType.DIAMOND_10));
        player.addCard(Card.generateCard(BlackjackCardType.DIAMOND_10));
        assertThat(player.isBurst()).isTrue();
    }

    @DisplayName("스코어 21 못넘으면 burst인지 확인하는 기능 False 리턴하는지 테스트")
    @Test
    void burst2() {
        Player player = new Player("pobi");
        player.addCard(Card.generateCard(BlackjackCardType.DIAMOND_10));
        assertThat(player.isBurst()).isFalse();
    }
}
