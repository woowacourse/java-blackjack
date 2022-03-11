package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackTest {

	@Test
	@DisplayName("참가자의 수가 8명을 초과하면 예외처리")
	void Player_Number_Exceed_Exception() {
		assertThatThrownBy(() -> {
			List<String> playerNames = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i");
			BlackJack.createFrom(playerNames);
		}).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 참가자의 수는 8명을 초과할 수 없습니다.");
	}

	@Test
	@DisplayName("카드를 해당 플레이어에게 한장 나누어주는지")
	void Hand_Out_Card_To() {
		List<String> playerNames = Arrays.asList("a", "b");
		BlackJack blackJack = BlackJack.createFrom(playerNames);
		blackJack.handOutCardTo(blackJack.getPlayers().get(0));
		assertThat(blackJack.getPlayers().get(0).getCards().size()).isEqualTo(1);
		assertThat(blackJack.getPlayers().get(1).getCards().size()).isEqualTo(0);
	}

	@Test
	@DisplayName("스타팅 카드를 두 장씩 배분하는지")
	void Hand_Out_Two_Starting_Cards() {
		List<String> playerNames = Arrays.asList("a", "b");
		BlackJack blackJack = BlackJack.createFrom(playerNames);
		blackJack.handOutStartingCards();
		assertThat(blackJack.getDealer().getCards().size()).isEqualTo(2);
		assertThat(blackJack.getPlayers().get(0).getCards().size()).isEqualTo(2);
		assertThat(blackJack.getPlayers().get(1).getCards().size()).isEqualTo(2);
	}

    @Test
    @DisplayName("16 이하 일때 딜러가 한장의 카드를 더 받는지")
    void Dealer_Receive_Additional_Card_Under_16(){
        List<String> playerNames = Arrays.asList("a", "b");
        BlackJack blackJack = BlackJack.createFrom(playerNames);
        blackJack.getDealer().receiveCard(new Card("8스페이드", 8));
        blackJack.getDealer().receiveCard(new Card("5하트", 5));
        assertThat(blackJack.isDealerNeedAdditionalCard()).isEqualTo(true);
        blackJack.getDealer().receiveCard(new Card("7클로버", 7));
        assertThat(blackJack.isDealerNeedAdditionalCard()).isEqualTo(false);
    }
}
