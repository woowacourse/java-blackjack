package domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.CardFactory;
import domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class PlayerTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Player 생성 시 이름 인자의 null, empty 체크")
    void nullAndEmptyTest(String input) {
        assertThatThrownBy(() -> new Player(input,1)).
            isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("초기 2장의 카드 받기 테스트")
    void receiveFirstCards() {
        Deck deck = CardFactory.create();
        Player player = new Player("pobi",1);
        player.receiveFirstCards(deck);
        assertThat(player.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("한 장의 카드를 더 받기")
    void receiveCard() {
        Deck deck = CardFactory.create();
        Player player = new Player("pobi",1);
        player.receiveFirstCards(deck);
        int sizeBeforeReceiveCard = player.getCards().size();
        player.receiveCard(deck);
        assertThat(player.getCards().size()).isEqualTo(sizeBeforeReceiveCard + 1);
    }
}
