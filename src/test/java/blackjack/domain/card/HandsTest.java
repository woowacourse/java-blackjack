//package blackjack.domain.card;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//class HandsTest {
//
//    @DisplayName("초기 카드는 2장만 가능합니다.")
//    @Test
//    void limit_2() {
//        List<Card> cards = new ArrayList<>();
//        cards.add(Card.of(Suit.CLUB, Denomination.KING));
//        cards.add(Card.of(Suit.CLUB, Denomination.QUEEN));
//        cards.add(Card.of(Suit.HEART, Denomination.QUEEN));
//        Hands hands = new Hands();
//        assertThatIllegalArgumentException()
//                .isThrownBy(() -> hands.initialize(cards));
//    }
//
//    @DisplayName("Hands 객체 생성")
//    @Test
//    void create() {
//        List<Card> cards = new ArrayList<>();
//        cards.add(Card.of(Suit.CLUB, Denomination.KING));
//        cards.add(Card.of(Suit.CLUB, Denomination.QUEEN));
//        assertThatCode(() -> {
//            Hands hands = new Hands();
//            hands.initialize(cards);
//        }).doesNotThrowAnyException();
//    }
//
//    @DisplayName("Hands에 Card 객체 추가")
//    @Test
//    void add() {
//        List<Card> cards = new ArrayList<>();
//        cards.add(Card.of(Suit.CLUB, Denomination.KING));
//        cards.add(Card.of(Suit.CLUB, Denomination.QUEEN));
//        Hands hands = new Hands();
//        hands.initialize(cards);
//
//        assertThat(hands.toList().size()).isEqualTo(2);
//    }
//
//    @DisplayName("포인트 계산 성공 : Ace 존재하고 최대값 21 넘을 때")
//    @Test
//    void calculate_containsA_maxExceed() {
//        List<Card> cards = new ArrayList<>();
//        cards.add(Card.of(Suit.CLUB, Denomination.ACE));
//        cards.add(Card.of(Suit.CLUB, Denomination.TWO));
//        Hands hands = new Hands();
//        hands.initialize(cards);
//        hands.addCard(Card.of(Suit.CLUB, Denomination.KING));
//
//        assertThat(hands.calculate()).isEqualTo(13);
//    }
//
//    @DisplayName("포인트 계산 성공 : Ace 존재하고 최대값 21 넘지 않을 때")
//    @Test
//    void calculate_containsA_maxNotExceed() {
//        List<Card> cards = new ArrayList<>();
//        cards.add(Card.of(Suit.CLUB, Denomination.ACE));
//        cards.add(Card.of(Suit.CLUB, Denomination.TWO));
//        Hands hands = new Hands();
//        hands.initialize(cards);
//        hands.addCard(Card.of(Suit.CLUB, Denomination.THREE));
//
//        assertThat(hands.calculate()).isEqualTo(16);
//    }
//
//    @DisplayName("포인트 계산 성공 : Ace 존재하지 않을 때")
//    @Test
//    void calculate_excludeA() {
//        List<Card> cards = new ArrayList<>();
//        cards.add(Card.of(Suit.HEART, Denomination.TWO));
//        cards.add(Card.of(Suit.CLUB, Denomination.TWO));
//        Hands hands = new Hands();
//        hands.initialize(cards);
//        hands.addCard(Card.of(Suit.CLUB, Denomination.THREE));
//
//        assertThat(hands.calculate()).isEqualTo(7);
//    }
//
//    @DisplayName("포인트 계산 성공 : 특수 케이스 A,2,8")
//    @Test
//    void calculate_with_A_2_8() {
//        List<Card> cards = new ArrayList<>();
//        cards.add(Card.of(Suit.HEART, Denomination.ACE));
//        cards.add(Card.of(Suit.CLUB, Denomination.TWO));
//        Hands hands = new Hands();
//        hands.initialize(cards);
//        hands.addCard(Card.of(Suit.CLUB, Denomination.EIGHT));
//
//        assertThat(hands.calculate()).isEqualTo(21);
//    }
//
//    @DisplayName("포인트 계산 성공 : 특수 케이스 10,3,A,A,A,Q")
//    @Test
//    void calculate_with_10_3_A_A_A_Q() {
//        List<Card> cards = new ArrayList<>();
//        cards.add(Card.of(Suit.HEART, Denomination.TEN));
//        cards.add(Card.of(Suit.HEART, Denomination.THREE));
//        Hands hands = new Hands();
//        hands.initialize(cards);
//        hands.addCard(Card.of(Suit.SPADE, Denomination.ACE));
//        hands.addCard(Card.of(Suit.CLUB, Denomination.ACE));
//        hands.addCard(Card.of(Suit.HEART, Denomination.ACE));
//        hands.addCard(Card.of(Suit.CLUB, Denomination.QUEEN));
//
//        assertThat(hands.calculate()).isEqualTo(26);
//    }
//
//    @DisplayName("Ace를 가지고 있는지 확인")
//    @Test
//    void containsAce() {
//        List<Card> cards = new ArrayList<>();
//        cards.add(Card.of(Suit.HEART, Denomination.ACE));
//        cards.add(Card.of(Suit.CLUB, Denomination.TWO));
//        Hands hands = new Hands();
//        hands.initialize(cards);
//        hands.addCard(Card.of(Suit.CLUB, Denomination.THREE));
//
//        assertTrue(hands.containsAce());
//    }
//
//    @DisplayName("number만큼 카드 가지고 오기")
//    @Test
//    void getCardOf() {
//        List<Card> cards = new ArrayList<>();
//        cards.add(Card.of(Suit.HEART, Denomination.TWO));
//        cards.add(Card.of(Suit.CLUB, Denomination.TWO));
//        Hands hands = new Hands();
//        hands.initialize(cards);
//        hands.addCard(Card.of(Suit.CLUB, Denomination.THREE));
//
//        assertThat(hands.getCardsWithSize(2).size()).isEqualTo(2);
//    }
//
//    @DisplayName("blackjack인지 확인")
//    @Test
//    void isBlackjack() {
//        List<Card> cards = new ArrayList<>();
//        cards.add(Card.of(Suit.HEART, Denomination.ACE));
//        cards.add(Card.of(Suit.CLUB, Denomination.TEN));
//        Hands hands = new Hands();
//        hands.initialize(cards);
//        assertTrue(hands.isBlackjack());
//    }
//
//    @DisplayName("isBust 확인")
//    @Test
//    public void isBust() {
//        List<Card> cards = new ArrayList<>();
//        cards.add(Card.of(Suit.HEART, Denomination.ACE));
//        cards.add(Card.of(Suit.CLUB, Denomination.TEN));
//        Hands hands = new Hands();
//        hands.initialize(cards);
//        assertFalse(hands.isBust());
//    }
//}
