package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Shape;
import domain.card.Value;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        ArrayList<Card> initialCards = new ArrayList<>(
                List.of(new Card(Value.THREE, Shape.SPADE), new Card(Value.KING, Shape.SPADE)));
        dealer = new Dealer(initialCards);
    }

    @DisplayName("딜러 이름 확인을 통해 딜러 생성에 성공하였는지 알 수 있다.")
    @Test
    void createTest() {
        assertThat(dealer.getName()).isEqualTo("딜러");
    }

    @DisplayName("딜러 카드의 합계가 16점 이하면 카드를 추가로 받아야 한다.")
    @Test
    void fillCardsTest() {
        Deck deck = new Deck();

        dealer.fillCards(deck);
        assertThat(dealer.calculateScore()).isGreaterThan(16)
                .isEqualTo(23);
    }
}
