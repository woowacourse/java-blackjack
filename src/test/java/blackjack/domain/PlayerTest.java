package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @DisplayName("A 1개 판단 테스트")
    @Test
    void aceOne() {
        Player player = new Player();
        Card card = new Card(CardShape.DIAMOND, CardNumber.ACE);
        player.drawCard(card);
        assertThat(player.getResult()).isEqualTo(11);
        card = new Card(CardShape.DIAMOND, CardNumber.KING);
        player.drawCard(card);
        player.drawCard(card);
        assertThat(player.getResult()).isEqualTo(21);
    }

    @DisplayName("A 2개 판단 테스트")
    @Test
    void aceTwo() {
        Player player = new Player();
        Card card = new Card(CardShape.DIAMOND, CardNumber.ACE);
        player.drawCard(card);
        player.drawCard(card);
        assertThat(player.getResult()).isEqualTo(12);
    }

    @DisplayName("A 3개 판단 테스트")
    @Test
    void aceThree() {
        Player player = new Player();
        Card card = new Card(CardShape.DIAMOND, CardNumber.ACE);
        player.drawCard(card);
        player.drawCard(card);
        player.drawCard(card);
        assertThat(player.getResult()).isEqualTo(13);
    }
}
