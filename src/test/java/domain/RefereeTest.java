package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import domain.deck.Deck;
import domain.player.Dealer;
import domain.player.Gambler;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RefereeTest {

    @Test
    @DisplayName("겜블러가 버스트 됐을 때 즉시 패배")
    void 겜블러가_버스트됐을_시_즉시_패배() {
        //given
        BettingMoney bettingMoney = new BettingMoney(1000);
        Gambler pobi = new Gambler("pobi", bettingMoney);
        Dealer dealer = new Dealer();

        Card ten = new Card(CardRank.TEN, CardSuit.CLOVER);
        Card king = new Card(CardRank.KING, CardSuit.CLOVER);
        Card two = new Card(CardRank.TWO, CardSuit.CLOVER);
        List<Card> pobiCards = List.of(ten, king, two);
        Deck pobiDeck = new FixedDeck(pobiCards);
        pobi.deal(pobiDeck);
        pobi.deal(pobiDeck);
        pobi.deal(pobiDeck);

        //when
        MatchResult matchResult = Referee.judge(dealer, pobi);

        //then
        assertThat(matchResult).isEqualTo(MatchResult.LOSE);
    }

    @Test
    @DisplayName("딜러와 겜블러 모두 블랙잭 시 무승부")
    void 딜러와_겜블러_동시_블랙잭() {
        //given
        BettingMoney bettingMoney = new BettingMoney(1000);
        Dealer dealer = new Dealer();
        Gambler pobi = new Gambler("pobi", bettingMoney);

        Card ten = new Card(CardRank.TEN, CardSuit.CLOVER);
        Card aceClover = new Card(CardRank.ACE, CardSuit.CLOVER);
        Card king = new Card(CardRank.KING, CardSuit.CLOVER);
        Card aceDiamond = new Card(CardRank.ACE, CardSuit.DIAMOND);

        List<Card> pobiCards = List.of(ten, aceClover);
        List<Card> dealerCards = List.of(king, aceDiamond);

        Deck pobiDeck = new FixedDeck(pobiCards);
        Deck dealerDeck = new FixedDeck(dealerCards);

        pobi.deal(pobiDeck);
        dealer.deal(dealerDeck);
        pobi.deal(pobiDeck);
        dealer.deal(dealerDeck);

        //when
        MatchResult matchResult = Referee.judge(dealer, pobi);

        //then
        assertThat(matchResult).isEqualTo(MatchResult.DRAW);
    }

    @Test
    @DisplayName("딜러와 겜블러 모두 21이지만 겜블러만 블랙잭")
    void 딜러와_겜블러_21이지만_겜블러만_블랙잭() {
        //given
        BettingMoney bettingMoney = new BettingMoney(1000);
        Dealer dealer = new Dealer();
        Gambler pobi = new Gambler("pobi", bettingMoney);

        Card ten = new Card(CardRank.TEN, CardSuit.CLOVER);
        Card aceClover = new Card(CardRank.ACE, CardSuit.CLOVER);
        Card king = new Card(CardRank.KING, CardSuit.CLOVER);
        Card five = new Card(CardRank.FIVE, CardSuit.DIAMOND);
        Card six = new Card(CardRank.SIX, CardSuit.CLOVER);

        List<Card> pobiCards = List.of(ten, aceClover);
        List<Card> dealerCards = List.of(king, five, six);

        Deck pobiDeck = new FixedDeck(pobiCards);
        Deck dealerDeck = new FixedDeck(dealerCards);

        pobi.deal(pobiDeck);
        dealer.deal(dealerDeck);
        pobi.deal(pobiDeck);
        dealer.deal(dealerDeck);
        dealer.deal(dealerDeck);

        //when
        MatchResult matchResult = Referee.judge(dealer, pobi);

        //then
        assertThat(matchResult).isEqualTo(MatchResult.BLACKJACK);
    }

    @Test
    @DisplayName("딜러와 겜블러 모두 21이지만 딜러만 블랙잭")
    void 딜러와_겜블러_21이지만_딜러만_블랙잭() {
        //given
        BettingMoney bettingMoney = new BettingMoney(1000);
        Dealer dealer = new Dealer();
        Gambler pobi = new Gambler("pobi", bettingMoney);

        Card ten = new Card(CardRank.TEN, CardSuit.CLOVER);
        Card aceClover = new Card(CardRank.ACE, CardSuit.CLOVER);
        Card king = new Card(CardRank.KING, CardSuit.CLOVER);
        Card five = new Card(CardRank.FIVE, CardSuit.DIAMOND);
        Card six = new Card(CardRank.SIX, CardSuit.CLOVER);

        List<Card> pobiCards = List.of(king, five, six);
        List<Card> dealerCards = List.of(ten, aceClover);

        Deck pobiDeck = new FixedDeck(pobiCards);
        Deck dealerDeck = new FixedDeck(dealerCards);

        pobi.deal(pobiDeck);
        dealer.deal(dealerDeck);
        pobi.deal(pobiDeck);
        dealer.deal(dealerDeck);
        pobi.deal(pobiDeck);

        //when
        MatchResult matchResult = Referee.judge(dealer, pobi);

        //then
        assertThat(matchResult).isEqualTo(MatchResult.LOSE);
    }

    @Test
    @DisplayName("딜러 버스트")
    void 딜러_버스트() {
        //given
        BettingMoney bettingMoney = new BettingMoney(1000);
        Dealer dealer = new Dealer();
        Gambler pobi = new Gambler("pobi", bettingMoney);

        Card ten = new Card(CardRank.TEN, CardSuit.CLOVER);
        Card seven = new Card(CardRank.SEVEN, CardSuit.CLOVER);
        Card king = new Card(CardRank.KING, CardSuit.CLOVER);
        Card five = new Card(CardRank.FIVE, CardSuit.DIAMOND);
        Card six = new Card(CardRank.SIX, CardSuit.CLOVER);

        List<Card> pobiCards = List.of(king, five);
        List<Card> dealerCards = List.of(ten, seven, six);

        Deck pobiDeck = new FixedDeck(pobiCards);
        Deck dealerDeck = new FixedDeck(dealerCards);

        pobi.deal(pobiDeck);
        dealer.deal(dealerDeck);
        pobi.deal(pobiDeck);
        dealer.deal(dealerDeck);
        dealer.deal(dealerDeck);

        //when
        MatchResult matchResult = Referee.judge(dealer, pobi);

        //then
        assertThat(matchResult).isEqualTo(MatchResult.WIN);
    }

    @Test
    @DisplayName("겜블러가 딜러보다 점수가 높을 때")
    void 겜블러가_딜러보다_점수가_높을_시() {
        //given
        BettingMoney bettingMoney = new BettingMoney(1000);
        Dealer dealer = new Dealer();
        Gambler pobi = new Gambler("pobi", bettingMoney);

        Card ten = new Card(CardRank.TEN, CardSuit.CLOVER);
        Card seven = new Card(CardRank.SEVEN, CardSuit.CLOVER);
        Card king = new Card(CardRank.KING, CardSuit.CLOVER);
        Card five = new Card(CardRank.FIVE, CardSuit.DIAMOND);
        Card six = new Card(CardRank.SIX, CardSuit.CLOVER);

        List<Card> pobiCards = List.of(king, five, six);
        List<Card> dealerCards = List.of(ten, seven);

        Deck pobiDeck = new FixedDeck(pobiCards);
        Deck dealerDeck = new FixedDeck(dealerCards);

        pobi.deal(pobiDeck);
        dealer.deal(dealerDeck);
        pobi.deal(pobiDeck);
        dealer.deal(dealerDeck);
        pobi.deal(pobiDeck);

        //when
        MatchResult matchResult = Referee.judge(dealer, pobi);

        //then
        assertThat(matchResult).isEqualTo(MatchResult.WIN);
    }
}
