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
        assertThat(player.getValue()).isEqualTo(11);
        card = new Card(CardShape.DIAMOND, CardNumber.KING);
        player.drawCard(card);
        player.drawCard(card);
        assertThat(player.getValue()).isEqualTo(21);
    }

    @DisplayName("A 2개 판단 테스트")
    @Test
    void aceTwo() {
        Player player = new Player();
        Card card = new Card(CardShape.DIAMOND, CardNumber.ACE);
        player.drawCard(card);
        player.drawCard(card);
        assertThat(player.getValue()).isEqualTo(12);
    }

    @DisplayName("A 3개 판단 테스트")
    @Test
    void aceThree() {
        Player player = new Player();
        Card card = new Card(CardShape.DIAMOND, CardNumber.ACE);
        player.drawCard(card);
        player.drawCard(card);
        player.drawCard(card);
        assertThat(player.getValue()).isEqualTo(13);
    }

    @DisplayName("플레이어는 갖고있는 카드들의 숫자 총 합이 21 이하일 때 선택 가능")
    @Test
    void canDrawCard1() {
        Player player = new Player();
        Card twoCard = new Card(CardShape.DIAMOND, CardNumber.TWO);
        player.drawCard(twoCard);
        assertThat(player.isCanDraw()).isTrue();
    }

    @DisplayName("플레이어는 갖고있는 카드들의 숫자 총 합이 21 이하일 때 선택 가능")
    @Test
    void canDrawCard21() {
        Player player = new Player();
        Card sevenCard = new Card(CardShape.DIAMOND, CardNumber.SEVEN);
        player.drawCard(sevenCard);
        player.drawCard(sevenCard);
        player.drawCard(sevenCard);
        assertThat(player.isCanDraw()).isTrue();
    }

    @DisplayName("플레이어는 갖고있는 카드들의 숫자 총 합이 21 초과일 때 선택 불가능")
    @Test
    void cannotDrawCard22() {
        Player player = new Player();
        Card sevenCard = new Card(CardShape.DIAMOND, CardNumber.SEVEN);
        player.drawCard(sevenCard);
        player.drawCard(sevenCard);
        Card eightCard = new Card(CardShape.DIAMOND, CardNumber.EIGHT);
        player.drawCard(eightCard);
        assertThat(player.isCanDraw()).isFalse();
    }
}
