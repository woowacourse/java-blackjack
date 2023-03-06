package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayerTest {

    static Player player;

    @BeforeEach
    void setUp() {
        player = new Player("IO", new ArrayList<>(List.of(new Card(Suit.DIAMOND, Letter.EIGHT), new Card(Suit.HEART, Letter.JACK))));
    }

    @Test
    @DisplayName("카드 한장씩 잘 받는지 테스트")
    void receiveCardSuccess() {
        Card card = new Card(Suit.CLOVER, Letter.FIVE);

        player.receiveCard(card);

        List<Card> cards = player.getCards();
        assertThat(cards.get(cards.size() - 1)).isEqualTo(card);
    }

    @Test
    @DisplayName("카드 새로 뽑을 수 있는지 판단 - 성공")
    void canReceiveNewCard() {
        assertThat(player.isAbleToReceive()).isTrue();
    }

    @Test
    @DisplayName("카드 새로 뽑을 수 있는지 판단 - 실패")
    void cannotReceiveNewCard() {
        player.receiveCard(new Card(Suit.HEART, Letter.KING));

        assertThat(player.isAbleToReceive()).isFalse();
    }
}
