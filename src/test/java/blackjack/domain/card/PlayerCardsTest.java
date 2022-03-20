package blackjack.domain.card;

import static blackjack.domain.CardFixture.SPADE_ACE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerCardsTest {

    @Test
    @DisplayName("PlayerCards 클래스는 Card 리스트를 입력받으면 정상적으로 생성된다.")
    void create_dealer() {
        List<Card> cards = new ArrayList<>();
        cards.add(SPADE_ACE);

        PlayerCards playerCards = new PlayerCards(cards);

        assertThat(playerCards.get()).hasSize(1);
        assertThat(playerCards.get().get(0)).isEqualTo(SPADE_ACE);
    }

    @Test
    @DisplayName("PlayerCards 클래스는 파라미터가 없으면 빈 카드를 가진 객체가 정상적으로 생성된다.")
    void create_dealer_empty() {
        PlayerCards playerCards = new PlayerCards();

        assertThat(playerCards.get()).hasSize(0);
    }

    @Test
    @DisplayName("getTotalScore 메서드는 카드의 총합을 반환한다.")
    void get_total_score() {
        List<Card> newCards = new ArrayList<>();
        newCards.add(Card.of(CardNumber.ACE, Type.SPADE));
        newCards.add(Card.of(CardNumber.TEN, Type.SPADE));
        PlayerCards hasAcePlayerCards = new PlayerCards(newCards);

        assertThat(hasAcePlayerCards.getTotalScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("Ace 가진 카드의 총합이 21을 넘지 않으면 Ace는 11로 계산한다.")
    void ace_calculate_11() {
        List<Card> newCards = new ArrayList<>();
        newCards.add(Card.of(CardNumber.ACE, Type.SPADE));
        newCards.add(Card.of(CardNumber.TEN, Type.SPADE));
        PlayerCards hasAcePlayerCards = new PlayerCards(newCards);

        assertThat(hasAcePlayerCards.getTotalScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("Ace 가진 카드의 총합이 21을 넘으면 Ace는 1로 계산한다.")
    void ace_calculate_1() {
        List<Card> newCards = new ArrayList<>();
        newCards.add(Card.of(CardNumber.TEN, Type.CLOVER));
        newCards.add(Card.of(CardNumber.THREE, Type.HEART));
        newCards.add(Card.of(CardNumber.ACE, Type.SPADE));
        PlayerCards hasAcePlayerCards = new PlayerCards(newCards);

        assertThat(hasAcePlayerCards.getTotalScore()).isEqualTo(14);
    }

    @Test
    @DisplayName("특정 CardNumber 보유 여부를 반환한다.")
    void contains_card_number() {
        List<Card> newCards = new ArrayList<>();
        newCards.add(Card.of(CardNumber.TEN, Type.CLOVER));
        newCards.add(Card.of(CardNumber.THREE, Type.HEART));
        newCards.add(Card.of(CardNumber.ACE, Type.SPADE));
        PlayerCards playerCards = new PlayerCards(newCards);

        assertThat(playerCards.containsCardNumber(CardNumber.TEN)).isTrue();
        assertThat(playerCards.containsCardNumber(CardNumber.KING)).isFalse();
    }

    @Test
    @DisplayName("equals, hashCode, toString 테스트")
    void equals() {
        PlayerCards o1 = new PlayerCards(new ArrayList<>());
        PlayerCards o2 = new PlayerCards(new ArrayList<>());
        Object o = new Object();

        assertThat(o1.equals(o2)).isTrue();
        assertThat(o1.equals(o1)).isTrue();
        assertThat(o1.equals(o)).isFalse();
        assertThat(o1.hashCode() == o2.hashCode()).isTrue();
        assertThat(o1.toString()).isEqualTo(o2.toString());
    }
}
