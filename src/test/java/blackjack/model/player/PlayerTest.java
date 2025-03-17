package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.card.Card;
import blackjack.model.card.CardShape;
import blackjack.model.card.CardType;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void 참여자의_이름이_2글자_이상_5글자가_아니면_예외가_발생한다() {
        // given

        // when & then
        assertThatThrownBy(() -> new Player("한스한스한스", 1000));
    }

    @Test
    void 참여자에게_카드를_한장_준다() {
        Player player = new Player("프리", 1000);
        player.putCard(new Card(CardShape.HEART, CardType.NORMAL_2));
        assertThat(player.getReceivedCards().size()).isEqualTo(1);
    }

    @Test
    void 참가자에게는_배팅_금액이_있다() {
        //given
        Player player = new Player("프리", 10000);
        // when
        int bettingMoney = player.getBettingMoney().getAmount();
        // then
        assertThat(bettingMoney).isEqualTo(10000);

    }

}
