package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    Deck deck = new Deck(CardFactory.createCardList());
    CardHand cardHand = deck.draw(2);

    @Test
    @DisplayName("딜러 이름 테스트")
    void dealer_Name() {
        User dealer = new Dealer(cardHand);
        assertThat(dealer.toString()).isEqualTo("딜러");
    }

    @Test
    @DisplayName("이름을 불러오는 지 테스트")
    void name_Test() {
        Player player = new Player("DD", deck.draw(2));
        assertThat(player.toString()).isEqualTo("DD");
    }
}
