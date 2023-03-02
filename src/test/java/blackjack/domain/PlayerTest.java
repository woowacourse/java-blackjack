package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("생성 테스트")
    @Test
    void Should_Create_When_NewPlayer() {
        assertDoesNotThrow(()->new Player(new Name("name")));
    }

    @DisplayName("플레이어에게 카드를 전달하면 플레이어는 카드를 받는다.")
    @Test
    void Should_AddCard_When_AddCard() {
        Card card = new Card(CardNumber.ACE, CardSymbol.HEARTS);
        Player tori = new Player(new Name("tori"));
        tori.addCard(card);
        assertThat(tori.getCards()).contains(card);
    }

    @Test
    void showCards() {
    }

    @Test
    void isHit() {
    }

    @Test
    void calculateScore() {
    }
}
