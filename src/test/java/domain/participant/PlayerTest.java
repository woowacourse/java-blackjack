package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.result.Outcome;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("처음 받은 카드 2장의 합이 21이면 첫 블랙잭으로 인정된다.")
    @Test
    void isFirstBlackJack() {
        Player player = new Player("pobi", new Money("10000"));
        player.drawCard(new Card(0));
        player.drawCard(new Card(10));
        assertThat(player.isFirstBlackJack()).isTrue();
    }

    @DisplayName("카드가 3장 이상이면서 합이 21이면 첫 블랙잭이 아니다.")
    @Test
    void isNotFirstBlackJackWhenCardSizeIsThree() {
        Player player = new Player("pobi", new Money("10000"));
        player.drawCard(new Card(6));
        player.drawCard(new Card(7));
        player.drawCard(new Card(5));
        assertThat(player.isFirstBlackJack()).isFalse();
    }

    @DisplayName("플레이어는 버스트)되지 않고, 블랙잭이 아닐 때만 카드를 더 뽑을 수 있다.")
    @Test
    void canDraw() {
        Player player = new Player("pobi", new Money("10000"));
        player.drawCard(new Card(10));
        player.drawCard(new Card(4));
        assertThat(player.canDraw()).isTrue();
    }

    @DisplayName("플레이어 점수가 21이면 카드를 더 뽑을 수 없다.")
    @Test
    void cannotDrawWhenScoreIs21() {
        Player player = new Player("pobi", new Money("10000"));
        player.drawCard(new Card(10));
        player.drawCard(new Card(11));
        player.drawCard(new Card(0));
        assertThat(player.canDraw()).isFalse();
    }

    @DisplayName("플레이어가 버스트되면 카드를 더 뽑을 수 없다.")
    @Test
    void cannotDrawWhenBust() {
        Player player = new Player("pobi", new Money("10000"));
        player.drawCard(new Card(10));
        player.drawCard(new Card(11));
        player.drawCard(new Card(12));
        assertThat(player.canDraw()).isFalse();
    }

    @DisplayName("게임 결과(Outcome)에 따라 플레이어의 수익을 정상적으로 계산한다.")
    @Test
    void calculateProfit() {
        Player player = new Player("pobi", new Money("10000"));
        assertThat(player.calculateProfit(Outcome.BLACKJACK_WIN).getAmount()).isEqualTo(15000);
        assertThat(player.calculateProfit(Outcome.WIN).getAmount()).isEqualTo(10000);
        assertThat(player.calculateProfit(Outcome.DRAW).getAmount()).isEqualTo(10000);
        assertThat(player.calculateProfit(Outcome.LOSE).getAmount()).isEqualTo(-10000);
    }
}
