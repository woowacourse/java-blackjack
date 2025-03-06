package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 딜러는_게임_시작_시_두_장의_카드를_받는다() {
        //given
        CardDeck cardDeck = CardDeck.createCards();
        Dealer dealer = new Dealer();

        //when
        Hand actual = dealer.drawCardWhenStart(cardDeck);

        //then
        assertThat(actual.getCards()).hasSize(2);
    }

    @Test
    void 딜러는_한_장의_카드를_받는다() {
        //given
        CardDeck cardDeck = CardDeck.createCards();
        Dealer dealer = new Dealer();

        //when
        Hand actual = dealer.drawCard(cardDeck);

        //then
        assertThat(actual.getCards()).hasSize(1);
    }

    @Test
    void 딜러가_보유한_카드의_합계를_구한다() {
        //given
        Dealer dealer = new Dealer();
        Hand hand = dealer.getHand();
        List<Card> cards = hand.getCards();
        cards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        cards.add(new Card(Pattern.CLOVER, CardNumber.TEN));

        //when & then
        assertThat(dealer.calculateTotalCardNumber()).isEqualTo(20);
    }

    @Test
    void 딜러가_보유한_카드의_합계가_21을_넘었는지_판정한다() {
        //given
        Dealer dealer = new Dealer();
        Hand hand = dealer.getHand();
        List<Card> cards = hand.getCards();
        cards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        cards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        //when & then
        assertThat(dealer.isOverBurstBound()).isFalse();
    }

    @Test
    void 딜러가_보유한_카드의_합계가_16을_넘었는지_판정한다() {
        //given
        Dealer dealer = new Dealer();
        Hand hand = dealer.getHand();
        List<Card> cards = hand.getCards();
        cards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        cards.add(new Card(Pattern.CLOVER, CardNumber.SEVEN));

        //when & then
        assertThat(dealer.isOverDrawBound()).isTrue();
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다() {
        //given
        Dealer dealer = new Dealer();
        Hand dealerHand = dealer.getHand();
        List<Card> dealerCards = dealerHand.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.SEVEN));

        Player player = new Player("pobi");
        Hand playerHand = player.getHand();
        List<Card> playerCards = playerHand.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.SIX));

        //when
        List<String> gameResult = dealer.judgeGameResult(List.of(player));

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of("패"));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_둘다_버스트() {
        //given
        Dealer dealer = new Dealer();
        Hand dealerHand = dealer.getHand();
        List<Card> dealerCards = dealerHand.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.HEART, CardNumber.TEN));

        Player player = new Player("pobi");
        Hand playerHand = player.getHand();
        List<Card> playerCards = playerHand.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        playerCards.add(new Card(Pattern.HEART, CardNumber.TEN));

        //when
        List<String> gameResult = dealer.judgeGameResult(List.of(player));

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of("패"));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_딜러만_버스트() {
        //given
        Dealer dealer = new Dealer();
        Hand dealerHand = dealer.getHand();
        List<Card> dealerCards = dealerHand.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.HEART, CardNumber.TEN));

        Player player = new Player("pobi");
        Hand playerHand = player.getHand();
        List<Card> playerCards = playerHand.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));

        //when
        List<String> gameResult = dealer.judgeGameResult(List.of(player));

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of("승"));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_둘다_블랙잭() {
        //given
        Dealer dealer = new Dealer();
        Hand dealerHand = dealer.getHand();
        List<Card> dealerCards = dealerHand.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        Player player = new Player("pobi");
        Hand playerHand = player.getHand();
        List<Card> playerCards = playerHand.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        //when
        List<String> gameResult = dealer.judgeGameResult(List.of(player));

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of("무"));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_딜러만_블랙잭() {
        //given
        Dealer dealer = new Dealer();
        Hand dealerHand = dealer.getHand();
        List<Card> dealerCards = dealerHand.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        Player player = new Player("pobi");
        Hand playerHand = player.getHand();
        List<Card> playerCards = playerHand.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.EIGHT));
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TWO));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        //when
        List<String> gameResult = dealer.judgeGameResult(List.of(player));

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of("패"));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_플레이어만_블랙잭() {
        //given
        Dealer dealer = new Dealer();
        Hand dealerHand = dealer.getHand();
        List<Card> dealerCards = dealerHand.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.EIGHT));
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TWO));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        Player player = new Player("pobi");
        Hand playerHand = player.getHand();
        List<Card> playerCards = playerHand.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        //when
        List<String> gameResult = dealer.judgeGameResult(List.of(player));

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of("승"));
    }


}
