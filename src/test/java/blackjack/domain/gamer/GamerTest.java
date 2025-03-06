package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;

class GamerTest {

    @ParameterizedTest
    @CsvSource({
        "ACE,TWO,false",
        "ACE,ACE,false",
        "ACE,JACK,false",
    })
    @DisplayName("21이하인 경우 버스트되지 않는다")
    void isBustTest1(CardNumber cardNumber1, CardNumber cardNumber2, boolean expected) {
        Card card1 = new Card(CardType.CLOVER, cardNumber1);
        Card card2 = new Card(CardType.CLOVER, cardNumber2);
        Player player = new Player("Pobi");
        player.addCard(card1);
        player.addCard(card2);

        assertThat(player.isBust()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "FIVE,JACK,KING,true",
        "TWO,JACK,QUEEN,true",
        "JACK,QUEEN,KING,true",
    })
    @DisplayName("21초과인 경우 버스트된다")
    void isBustTest2(CardNumber cardNumber1, CardNumber cardNumber2,
        CardNumber cardNumber3, boolean expected) {
        Card card1 = new Card(CardType.CLOVER, cardNumber1);
        Card card2 = new Card(CardType.CLOVER, cardNumber2);
        Card card3 = new Card(CardType.CLOVER, cardNumber3);
        Player player = new Player("Pobi");
        player.addCard(card1);
        player.addCard(card2);
        player.addCard(card3);

        assertThat(player.isBust()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "ACE,ACE,12",
        "ACE,TWO,13",
        "ACE,KING,21",
    })
    @DisplayName("버스트되지 않으면 Ace는 11로 계산한다")
    void getSumOfCardsTest1(CardNumber cardNumber1, CardNumber cardNumber2, int expected) {
        Card card1 = new Card(CardType.CLOVER, cardNumber1);
        Card card2 = new Card(CardType.HEART, cardNumber2);
        Dealer dealer = new Dealer();
        dealer.addCard(card1);
        dealer.addCard(card2);

        assertThat(dealer.getSumOfCards()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "ACE,ACE,ACE,13",
        "ACE,ACE,KING,12",
        "ACE,QUEEN,KING,21",
    })
    @DisplayName("버스트될 것 같으면 Ace는 1로 계산한다")
    void getSumOfCardsTest2(CardNumber cardNumber1, CardNumber cardNumber2,
        CardNumber cardNumber3, int expected) {
        Card card1 = new Card(CardType.CLOVER, cardNumber1);
        Card card2 = new Card(CardType.HEART, cardNumber2);
        Card card3 = new Card(CardType.DIAMOND, cardNumber3);
        Dealer dealer = new Dealer();
        dealer.addCard(card1);
        dealer.addCard(card2);
        dealer.addCard(card3);

        assertThat(dealer.getSumOfCards()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "ACE,KING,true",
        "ACE,TWO,false",
        "ACE,ACE,false",
    })
    @DisplayName("블랙잭 여부를 확인한다")
    void isBlackjackTest(CardNumber cardNumber1, CardNumber cardNumber2, boolean expected) {
        Card card1 = new Card(CardType.CLOVER, cardNumber1);
        Card card2 = new Card(CardType.HEART, cardNumber2);
        Player player = new Player("Pobi");
        player.addCard(card1);
        player.addCard(card2);

        assertThat(player.isBlackjack()).isEqualTo(expected);
    }

    // TODO Fixture 생성 후 진행
/*
    @Test
    void getFinalResultTest() {
        Gamer dealer = new Dealer();
        Player aa = new Player("aa");
        Player bb = new Player("bb");
        Player cc = new Player("cc");
        dealer.drawCard(Deck.generateFrom(() -> new Stack<>()));
        dealer.drawCard(Deck.generateFrom(() -> new Stack<>()));
        aa.drawCard(Deck.generateFrom(() -> new Stack<>()));
        aa.drawCard(Deck.generateFrom(() -> new Stack<>()));
        bb.drawCard(Deck.generateFrom(() -> new Stack<>()));
        bb.drawCard(Deck.generateFrom(() -> new Stack<>()));
        cc.drawCard(Deck.generateFrom(() -> new Stack<>()));
        cc.drawCard(Deck.generateFrom(() -> new Stack<>()));
        List<Gamer> players = List.of(aa, bb, cc);
        Map<RoundResult, Integer> finalResult = dealer.getFinalResult(players);
        assertThat(finalResult.get(RoundResult.WIN)).isEqualTo(1);
        assertThat(finalResult.get(RoundResult.LOSE)).isEqualTo(2);
        assertThat(finalResult.get(RoundResult.TIE)).isEqualTo(0);
    }*/
}
