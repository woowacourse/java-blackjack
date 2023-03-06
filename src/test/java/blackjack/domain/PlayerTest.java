package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayerTest {

    static Player player;

    @BeforeEach
    void setUp() {
        player = new Player("IO");
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.EIGHT));
        player.receiveCard(new Card(CardShape.HEART, CardNumber.JACK));

    }

    @Test
    @DisplayName("카드 한장씩 잘 받는지 테스트")
    void receiveCardTest() {
        Card card = new Card(CardShape.CLOVER, CardNumber.FIVE);

        player.receiveCard(card);

        List<Card> cards = player.getCards();
        assertThat(cards.get(cards.size() - 1)).isEqualTo(card);
    }

    @Test
    @DisplayName("카드 새로 뽑을 수 있는지 판단 - 성공")
    void isAbleToReceiveTest() {
        assertThat(player.isAbleToReceive()).isTrue();
    }

    @Test
    @DisplayName("카드 새로 뽑을 수 있는지 판단 - 실패")
    void isAbleToReceiveFailTest() {
        player.receiveCard(new Card(CardShape.HEART, CardNumber.KING));

        assertThat(player.isAbleToReceive()).isFalse();
    }
}
