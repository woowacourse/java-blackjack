package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("요청한 카드 저장 확인 테스트")
    void shouldSuccessTakeCard() {
        Player player = new Player(new Name("seongha"));
        player.drawCard(new Card("10다이아몬드", 10));
        player.drawCard(new Card("3다이아몬드", 3));
        assertThat(player.getHandCards().getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("가지고 있는 카드의 합이 21 이하면 true를 반환한다.")
    void isCardValueBelow21() {
        Player player = new Player(new Name("seongha"));
        player.drawCard(new Card("10다이아몬드", 10));
        player.drawCard(new Card("6다이아몬드", 6));
        assertThat(player.isDrawable()).isTrue();
    }

    @Test
    @DisplayName("가지고 있는 카드의 합이 22 이상이면 false를 반환한다.")
    void isCardValueOver21() {
        Player player = new Player(new Name("seongha"));
        player.drawCard(new Card("10다이아몬드", 10));
        player.drawCard(new Card("6다이아몬드", 6));
        player.drawCard(new Card("8다이아몬드", 8));
        assertThat(player.isDrawable()).isFalse();
    }
}
