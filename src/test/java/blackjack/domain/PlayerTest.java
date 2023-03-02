package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
//    새로 뽑을 수 있는지 판단

//    점수 계산

    @DisplayName("카드 한장씩 잘 받는지 테스트")
    @Test
    void receiveCardSuccess(){
        Player player = new Player(new ArrayList<>(List.of(new Card(TrumpShape.DIAMOND, TrumpNumber.EIGHT), new Card(TrumpShape.HEART, TrumpNumber.JACK))));
        Card card = new Card(TrumpShape.CLOVER, TrumpNumber.FIVE);
        player.receiveCard(card);
        List<Card> cards = player.getCards();
        assertThat(cards.get(cards.size() - 1)).isEqualTo(card);
    }
}
