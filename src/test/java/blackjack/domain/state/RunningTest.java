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

public class RunningTest {

    ParticipantCards cardsSet;
    ParticipantCards blackjackCardsSet;

    @BeforeEach
    void setupCards() {
        cardsSet = new ParticipantCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.DIAMOND, Denomination.FIVE),
            new Card(Suit.HEART, Denomination.TWO)));

        blackjackCardsSet = new ParticipantCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.DIAMOND, Denomination.JACK)));
    }

    @Test
    @DisplayName("Running상태일 때 isFinished는 false이다.")
    void isFinished() {
        Running running = new Hit(cardsSet);

        assertThat(running.isFinished()).isEqualTo(false);
    }

    @Test
    @DisplayName("Running상태일 때 getScore를 호출하면 예외가 발생.")
    void drawFail() {
        Running running = new Hit(cardsSet);

        assertThatThrownBy(running::getScore)
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("[ERROR] 게임이 끝나지 않은 경우 점수를 가져올 수 없습니다.");
    }

    @Test
    @DisplayName("Running상태일 때 profit을 호출하면 예외가 발생.")
    void stayFail() {
        Running running = new Hit(cardsSet);
        BettingMoney money = new BettingMoney(1000);
        Finished dealerState = new Stay(cardsSet);

        assertThatThrownBy(() -> running.profit(money.getBettingMoney(), dealerState))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("[ERROR] 게임이 끝나지 않은 경우 수익을 구할 수 없습니다.");
    }

    @Test
    @DisplayName("참가자 카드를 반환해준다.")
    void getParticipantCards() {
        Running running = new Hit(cardsSet);

        assertThat(running.getParticipantCards()).isSameAs(cardsSet);
    }

}
