package blackjack.domain.state;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import blackjack.domain.betting.BettingMoney;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.ParticipantCards;
import blackjack.domain.card.Suit;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FinishedTest {

    ParticipantCards cardsSet;
    ParticipantCards blackjackCardsSet;

    Card card = new Card(Suit.CLOVER, Denomination.TWO);

    @BeforeEach
    void setupCards() {
        cardsSet = new ParticipantCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.DIAMOND, Denomination.FIVE),
            new Card(Suit.HEART, Denomination.TWO)));

        blackjackCardsSet = new ParticipantCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.DIAMOND, Denomination.JACK)));
    }

    @Test
    @DisplayName("Finished상태일 때 isFinished는 true이다.")
    void isFinished() {
        Finished finished = new Stay(cardsSet);

        assertThat(finished.isFinished()).isEqualTo(true);
    }

    @Test
    @DisplayName("Finished상태일 때 draw를 호출하면 예외가 발생.")
    void drawFail() {
        Finished finished = new Stay(cardsSet);

        assertThatThrownBy(() -> finished.draw(card))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("[ERROR] 턴이 끝난 후 카드를 새로 뽑을 수 없습니다.");
    }

    @Test
    @DisplayName("Finished상태일 때 stay를 호출하면 예외가 발생.")
    void stayFail() {
        Finished finished = new Stay(cardsSet);

        assertThatThrownBy(finished::stay)
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("[ERROR] 턴이 끝난 후 스테이를 외칠 수 없습니다.");
    }

    @Test
    @DisplayName("finished면서 플레이어만 블랙잭인 경우 profit 계산")
    void profit() {
        Finished finished = new Blackjack(blackjackCardsSet);
        State dealerState = new Stay(cardsSet);
        BettingMoney money = new BettingMoney(1000);

        double profit = finished.profit(money.getBettingMoney(), dealerState);

        assertThat(profit).isEqualTo(1500);
    }

}
