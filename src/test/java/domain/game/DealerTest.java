package domain.game;

import static domain.card.CardDeck.DRAW_COUNT_WHEN_HIT;
import static domain.card.CardDeck.DRAW_COUNT_WHEN_START;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardNumber;
import domain.card.Pattern;
import domain.card.TestShuffler;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 딜러는_게임_시작_시_두_장의_카드를_받는다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);

        //when
        dealer.drawCard(DRAW_COUNT_WHEN_START);

        //then
        assertThat(dealer.getHand().getCards()).hasSize(2);
    }

    @Test
    void 딜러는_게임_시작_시_한_장의_카드를_공개한다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);
        Hand hand = dealer.getHand();
        List<Card> cards = hand.getCards();
        cards.add(new Card(Pattern.SPADE, CardNumber.TEN));

        //when
        Card singleCard = dealer.getSingleCard();

        //then
        assertThat(singleCard.equals(new Card(Pattern.SPADE, CardNumber.TEN))).isTrue();
    }

    @Test
    void 딜러는_한_장의_카드를_받는다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);

        //when
        dealer.drawCard(DRAW_COUNT_WHEN_HIT);

        //then
        assertThat(dealer.getHand().getCards()).hasSize(1);
    }

    @Test
    void 딜러가_보유한_카드의_합계를_구한다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);
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
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);
        Hand hand = dealer.getHand();
        List<Card> cards = hand.getCards();
        cards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        cards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        //when & then
        assertThat(dealer.isOverBustBound()).isFalse();
    }

    @Test
    void 딜러가_보유한_카드의_합계가_16을_넘었는지_판정한다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);
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
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);
        Hand dealerHand = dealer.getHand();
        List<Card> dealerCards = dealerHand.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.SEVEN));

        Player player = new Player("pobi", cardDeck);
        Hand playerHand = player.getHand();
        List<Card> playerCards = playerHand.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.SIX));

        //when
        List<GameResult> gameResult = dealer.judgeGameResult(List.of(player));

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.LOSE));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_둘다_버스트() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);
        Hand dealerHand = dealer.getHand();
        List<Card> dealerCards = dealerHand.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.HEART, CardNumber.TEN));

        Player player = new Player("pobi", cardDeck);
        Hand playerHand = player.getHand();
        List<Card> playerCards = playerHand.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        playerCards.add(new Card(Pattern.HEART, CardNumber.TEN));

        //when
        List<GameResult> gameResult = dealer.judgeGameResult(List.of(player));

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.LOSE));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_딜러만_버스트() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);
        Hand dealerHand = dealer.getHand();
        List<Card> dealerCards = dealerHand.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.HEART, CardNumber.TEN));

        Player player = new Player("pobi", cardDeck);
        Hand playerHand = player.getHand();
        List<Card> playerCards = playerHand.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));

        //when
        List<GameResult> gameResult = dealer.judgeGameResult(List.of(player));

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.WIN));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_둘다_블랙잭() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);
        Hand dealerHand = dealer.getHand();
        List<Card> dealerCards = dealerHand.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        Player player = new Player("pobi", cardDeck);
        Hand playerHand = player.getHand();
        List<Card> playerCards = playerHand.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        //when
        List<GameResult> gameResult = dealer.judgeGameResult(List.of(player));

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.DRAW));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_딜러만_블랙잭() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);
        Hand dealerHand = dealer.getHand();
        List<Card> dealerCards = dealerHand.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        Player player = new Player("pobi", cardDeck);
        Hand playerHand = player.getHand();
        List<Card> playerCards = playerHand.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.EIGHT));
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TWO));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        //when
        List<GameResult> gameResult = dealer.judgeGameResult(List.of(player));

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.LOSE));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_플레이어만_블랙잭() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);
        Hand dealerHand = dealer.getHand();
        List<Card> dealerCards = dealerHand.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.EIGHT));
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TWO));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        Player player = new Player("pobi", cardDeck);
        Hand playerHand = player.getHand();
        List<Card> playerCards = playerHand.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        //when
        List<GameResult> gameResult = dealer.judgeGameResult(List.of(player));

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.WIN));
    }
}
