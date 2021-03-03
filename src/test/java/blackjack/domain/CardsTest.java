package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {
    @DisplayName("Cards 객체를 생성한다.")
    @Test
    public void createCards() {
        Cards cards = new Cards(CardFactory.make());

        assertThat(cards).isInstanceOf(Cards.class);
    }

    @DisplayName("카드를 두장 뽑는다 - 시작 단계")
    @Test
    public void peekTwoCardsStart(){
        Cards cards = new Cards(CardFactory.make());
        GameStatus gameStatus = new GameStatus(true);
        assertThat(cards.pop(gameStatus).size()).isEqualTo(2);
        assertThat(cards.size()).isEqualTo(50);
    }

    @DisplayName("카드를 두장 뽑는다 - 진행 단계")
    @Test
    public void peekTwoCardsOngoing(){
        Cards cards = new Cards(CardFactory.make());
        GameStatus gameStatus = new GameStatus(false);
        assertThat(cards.pop(gameStatus).size()).isEqualTo(1);
        assertThat(cards.size()).isEqualTo(51);
    }
}
