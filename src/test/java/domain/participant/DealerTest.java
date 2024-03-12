package domain.participant;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class DealerTest {

    @Test
    @DisplayName("성공: 점수가 16점이면 카드를 받을 수 있다.")
    void receive_NoException_TotalScoreSixteen() {
        Participant dealer = new Dealer();
        dealer.receive(List.of(
                new Card(Rank.KING, Symbol.CLUB),
                new Card(Rank.SIX, Symbol.SPADE)
        ));
        assertThatCode(() -> dealer.receive(new Card(Rank.QUEEN, Symbol.DIAMOND)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패: 점수가 17점이면 카드를 받을 수 없다.")
    void receive_Exception_TotalScoreSeventeen() {
        Participant dealer = new Dealer();
        dealer.receive(List.of(
                new Card(Rank.KING, Symbol.CLUB),
                new Card(Rank.SEVEN, Symbol.HEART)
        ));
        assertThatCode(() -> dealer.receive(new Card(Rank.QUEEN, Symbol.DIAMOND)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 카드를 받을 수 없는 상태입니다.");
    }

    @Test
    @DisplayName("성공: 딜러의 공개 카드를 가져올 수 있다.(첫번째 카드)")
    void findShowingCard() {
        Dealer dealer = new Dealer();
        Card givenCard = new Card(Rank.KING, Symbol.CLUB);
        dealer.receive(givenCard);
        List<Card> showingCards = dealer.findShowingCards();

        assertThat(showingCards).contains(givenCard)
                .hasSize(1);
    }

    @Test
    @DisplayName("실패: 딜러에게 카드가 없으면 공개 카드를 가져올 수 없다.")
    void findShowingCard_Exception_DoesNotHaveCard() {
        Dealer dealer = new Dealer();
        assertThatCode(dealer::findShowingCards)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 딜러 카드가 비어 있습니다.");
    }
}
