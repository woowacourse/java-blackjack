package blackjack.domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandsTest {

    @DisplayName("Cards 객체 생성")
    @Test
    void create() {
        List<Card> cards = new ArrayList<>();
        assertThatCode(() -> new Hands(cards)).doesNotThrowAnyException();
    }

    @DisplayName("Cards에 Card 객체 추가")
    @Test
    void add() {
        List<Card> cards = new ArrayList<>();
        Hands hands = new Hands(cards);
        hands.addCard(Card.create(CardSymbol.CLUB, CardValue.KING));

        assertThat(hands.toList().size()).isEqualTo(1);
    }

    @DisplayName("포인트 계산 성공 : Ace 존재하고 최대값 21 넘을 때")
    @Test
    void calculate_containsA_maxExceed() {
        List<Card> cards = new ArrayList<>();
        Hands hands = new Hands(cards);
        hands.addCard(Card.create(CardSymbol.CLUB, CardValue.ACE));
        hands.addCard(Card.create(CardSymbol.CLUB, CardValue.TWO));
        hands.addCard(Card.create(CardSymbol.CLUB, CardValue.KING));

        assertThat(hands.calculate()).isEqualTo(13);
    }

    @DisplayName("포인트 계산 성공 : Ace 존재하고 최대값 21 넘지 않을 때")
    @Test
    void calculate_containsA_maxNotExceed() {
        List<Card> cards = new ArrayList<>();
        Hands hands = new Hands(cards);
        hands.addCard(Card.create(CardSymbol.CLUB, CardValue.ACE));
        hands.addCard(Card.create(CardSymbol.CLUB, CardValue.TWO));
        hands.addCard(Card.create(CardSymbol.CLUB, CardValue.THREE));

        assertThat(hands.calculate()).isEqualTo(16);
    }

    @DisplayName("포인트 계산 성공 : Ace 존재하지 않을 때")
    @Test
    void calculate_excludeA() {
        List<Card> cards = new ArrayList<>();
        Hands hands = new Hands(cards);
        hands.addCard(Card.create(CardSymbol.HEART, CardValue.TWO));
        hands.addCard(Card.create(CardSymbol.CLUB, CardValue.TWO));
        hands.addCard(Card.create(CardSymbol.CLUB, CardValue.THREE));

        assertThat(hands.calculate()).isEqualTo(7);
    }

    @DisplayName("number만큼 카드 가지고 오기")
    @Test
    void getCardOf() {
        List<Card> cards = new ArrayList<>();
        Hands hands = new Hands(cards);
        hands.addCard(Card.create(CardSymbol.HEART, CardValue.TWO));
        hands.addCard(Card.create(CardSymbol.CLUB, CardValue.TWO));
        hands.addCard(Card.create(CardSymbol.CLUB, CardValue.THREE));

        assertThat(hands.getCardOf(2).size()).isEqualTo(2);
    }
}
