package blackjack.domain.gamer;

import static blackjack.CardConstant.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;

class GamerTest {

    private Gamer gamer = new Player("name", 1000);

    @Test
    @DisplayName("카드를 추가한다.")
    void addCard() {
        gamer.addCard(CLOVER_ACE);
        List<Card> cards = gamer.getCards();

        assertThat(cards.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("보유 카드 번호 합 반환")
    void calculateCardsNumberSum() {
        gamer.addCard(CLOVER_TEN);
        gamer.addCard(CLOVER_FIVE);

        int sum = gamer.sumCardsNumber();
        assertThat(sum).isEqualTo(15);
    }

    @Test
    @DisplayName("Ace가 1로 계산된 보유 카드 번호 합 반환")
    void calculateCardsNumberSumWithAceOne() {
        gamer.addCard(CLOVER_NINE);
        gamer.addCard(CLOVER_TEN);
        gamer.addCard(CLOVER_ACE);

        int sum = gamer.sumCardsNumber();
        assertThat(sum).isEqualTo(20);
    }

    @Test
    @DisplayName("Ace가 11로 계산된 보유 카드 번호 합 반환")
    void calculateCardsNumberSumWithAceEleven() {
        gamer.addCard(CLOVER_ACE);
        gamer.addCard(CLOVER_TEN);

        int sum = gamer.sumCardsNumber();
        assertThat(sum).isEqualTo(21);
    }

    @Test
    @DisplayName("Ace가 4장일 때 보유 카드 번호 합 반환")
    void calculateCardsNumberSumWithFourAce() {
        gamer.addCard(CLOVER_ACE);
        gamer.addCard(CLOVER_ACE);
        gamer.addCard(CLOVER_ACE);
        gamer.addCard(CLOVER_ACE);

        int sum = gamer.sumCardsNumber();
        assertThat(sum).isEqualTo(14);
    }
}