package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayerTest {

    static Player player;

    @BeforeAll
    static void setUp() {
        player = new Player(new ArrayList<>(List.of(new Card(TrumpShape.DIAMOND, TrumpNumber.EIGHT), new Card(TrumpShape.HEART, TrumpNumber.JACK))));
    }

    @Test
    @DisplayName("카드 한장씩 잘 받는지 테스트")
    void receiveCardSuccess() {
        Card card = new Card(TrumpShape.CLOVER, TrumpNumber.FIVE);
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
        player.receiveCard(new Card(TrumpShape.HEART, TrumpNumber.KING));
        assertThat(player.isAbleToReceive()).isFalse();
    }
}
