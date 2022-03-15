package blackjack.domain.participant;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Suit.CLOVER;
import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player createPlayerWithDenominations(String playerName, Denomination... denominations) {
        Player player = new Player(playerName);
        for (Denomination denomination : denominations) {
            player.receiveCard(new Card(denomination, CLOVER));
        }
        return player;
    }


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
        Card card = new Card(ACE, Suit.SPADE);

        player.receiveCard(card);

        assertThat(player.getCards()).containsExactly(card);
    }

    @Test
    @DisplayName("플레이어는 점수가 21점 이상이면 카드를 더이상 받을 수 없다")
    void player_can_not_receive_card_where_more_than_21() {
    	// given
        Player player = createPlayerWithDenominations("user a", QUEEN, ACE);

        // when

    	// then
        assertThat(player.canReceive()).isFalse();
    }
}
