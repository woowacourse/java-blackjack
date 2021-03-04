package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardType;
import blackjack.domain.card.CardValue;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("카드를 받는다.")
    void test_receive_card() {
        Participant player = new Player("pobi", cards -> 0);
        Card card = new Card(CardType.DIAMOND, CardValue.TEN);
        player.receiveCard(card);
        Assertions.assertThat(player.showCards().contains(card)).isTrue();
    }

    @ParameterizedTest
    @DisplayName("딜러가 카드를 한장을 더 뽑을 수 있는지 확인한다")
    @CsvSource(value = {
            "21:true", "22:false"
    }, delimiter = ':')
    void test_player_is_receive_card(int totalScore, boolean actual) {
        //given
        Participant player = new Player("pobi", cards -> totalScore);

        //when
        boolean isReceived = player.isReceiveCard();

        //then
        assertThat(isReceived).isEqualTo(actual);
    }
}
