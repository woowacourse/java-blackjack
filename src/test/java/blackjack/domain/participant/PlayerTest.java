package blackjack.domain.participant;

import static blackjack.domain.TestBlackjackUtils.createCardHand;
import static blackjack.domain.TestCardFixture.aceCard;
import static blackjack.domain.TestCardFixture.kingCard;
import static blackjack.domain.TestCardFixture.sevenCard;
import static blackjack.domain.TestCardFixture.tenCard;
import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Hand;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어가 정상적으로 생성되는지 확인")
    void create() {
        Player player = new Player("승팡");

        assertThat(player).isNotNull();
    }

    @Test
    @DisplayName("플레이어가 카드를 정상적으로 받는지 확인")
    void receiveCard() {
        Player player = new Player("필즈");
        player.receiveCard(aceCard);

        assertThat(player.getCards()).containsExactly(aceCard);
    }

    @Test
    @DisplayName("플레이어를 생성할때 이름이 null을 포함한채 생성하면 예외발생")
    void validateNameIsNull() {
        Hand cardHand = new Hand();

        assertThatThrownBy(() -> new Player(null, cardHand))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 이름과 카드패가 null일 수 없습니다.");
    }

    @Test
    @DisplayName("승자가 누군지 확인")
    void isWin() {
        Player seung = new Player(new Name("seung"), createCardHand(aceCard, sevenCard));
        Player pobi = new Player(new Name("pobi"), createCardHand(aceCard, kingCard));

        assertThat(seung.isWin(pobi)).isFalse();
    }

    @Test
    @DisplayName("카드를 받을 수 있는 상태인지 확인")
    void receiveCardByPlayer() {
        Player seung = new Player(new Name("seung"), createCardHand(aceCard, kingCard));

        assertThat(seung.shouldReceive()).isFalse();
    }

    @Test
    @DisplayName("카드패의 총합이 정확한지 확인")
    void sumOfCardHand() {
        Player seung = new Player(new Name("seung"), createCardHand(aceCard, tenCard));

        assertThat(seung.getCardTotalScore()).isEqualTo(21);
    }
}
