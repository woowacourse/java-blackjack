package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @Test
    @DisplayName("Cards 클래스는 Card 리스트를 입력받으면 정상적으로 생성된다.")
    void create_dealer() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardNumber.TEN, CardType.SPADE));

        assertThatCode(() -> new Cards(cards)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("getTotalScore 메서드는 카드의 총합을 반환한다.")
    void get_total_score() {
        List<Card> newCards = new ArrayList<>();
        newCards.add(new Card(CardNumber.ACE, CardType.SPADE));
        newCards.add(new Card(CardNumber.TEN, CardType.SPADE));
        Cards hasAceCards = new Cards(newCards);

        assertThat(hasAceCards.getTotalScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("Ace 가진 카드의 총합이 21을 넘지 않으면 Ace는 11로 계산한다.")
    void ace_calculate_11() {
        List<Card> newCards = new ArrayList<>();
        newCards.add(new Card(CardNumber.ACE, CardType.SPADE));
        newCards.add(new Card(CardNumber.TEN, CardType.SPADE));
        Cards hasAceCards = new Cards(newCards);

        assertThat(hasAceCards.getTotalScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("Ace 가진 카드의 총합이 21을 넘으면 Ace는 1로 계산한다.")
    void ace_calculate_1() {
        List<Card> newCards = new ArrayList<>();
        newCards.add(new Card(CardNumber.TEN, CardType.CLOVER));
        newCards.add(new Card(CardNumber.THREE, CardType.HEART));
        newCards.add(new Card(CardNumber.ACE, CardType.SPADE));
        Cards hasAceCards = new Cards(newCards);

        assertThat(hasAceCards.getTotalScore()).isEqualTo(14);
    }

    @Test
    @DisplayName("특정 CardNumber 보유 여부를 반환한다.")
    void contains_card_number() {
        List<Card> newCards = new ArrayList<>();
        newCards.add(new Card(CardNumber.TEN, CardType.CLOVER));
        newCards.add(new Card(CardNumber.THREE, CardType.HEART));
        newCards.add(new Card(CardNumber.ACE, CardType.SPADE));
        Cards cards = new Cards(newCards);

        assertThat(cards.containsCardNumber(CardNumber.TEN)).isTrue();
        assertThat(cards.containsCardNumber(CardNumber.KING)).isFalse();
    }
}
