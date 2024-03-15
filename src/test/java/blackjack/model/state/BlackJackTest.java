package blackjack.model.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.cards.Card;
import blackjack.model.cards.CardNumber;
import blackjack.model.cards.CardShape;
import blackjack.model.cards.Cards;
import blackjack.vo.Money;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackTest {
    private BlackJack blackJack;

    @BeforeEach
    void setUp() {
        Cards blackJackCards = new Cards(
                List.of(new Card(CardNumber.ACE, CardShape.SPADE),
                        new Card(CardNumber.JACK, CardShape.HEART))
        );
        blackJack = new BlackJack(blackJackCards);
    }

    @DisplayName("블랙잭 상태일 때 추가 카드를 뽑을 수 없다")
    @Test
    void draw() {
        Card card = new Card(CardNumber.THREE, CardShape.CLOVER);

        assertThatThrownBy(() -> blackJack.draw(card))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("블랙잭 상태에서는 카드 여러장을 뽑을 수 없다")
    @Test
    void drawCards() {
        List<Card> cardsToAdd = List.of(new Card(CardNumber.TWO, CardShape.SPADE),
                new Card(CardNumber.THREE, CardShape.HEART));

        assertThatThrownBy(() -> blackJack.drawCards(cardsToAdd))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("블랙잭 상태는 게임이 종료된 상태로 stand할 수 없다")
    @Test
    void stand() {
        assertThatThrownBy(() -> blackJack.stand())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("블랙잭 상태로 승리하면 베팅금액의 1.5배를 받는다")
    @Test
    void calculateProfit() {
        Money betAmount = new Money(1000);

        assertThat(blackJack.calculateProfit(betAmount)).isEqualTo(new Money(1500));
    }

}
