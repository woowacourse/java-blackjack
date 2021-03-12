package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Face;
import blackjack.domain.card.Suit;
import blackjack.domain.result.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ChallengerTest {
    Dealer dealer;

    @BeforeEach
    void setUp() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.NINE));
        dealer = new Dealer(new Cards(cardList));
    }


    @Test
    @DisplayName("블랙잭이 아니고, 챌린저의 점수가 딜러의 점수보다 높으면, 승리를 반환한다.")
    void getChallengerResultIsWinner() {
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Suit.DIAMOND, Face.QUEEN));
        cardList.add(new Card(Suit.DIAMOND, Face.KING));

        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));
        assertThat(challenger.getResult(dealer)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("챌린저만 블랙잭이면, 블랙잭 승리를 반환한다.")
    void getBlackJackWin() {
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Suit.DIAMOND, Face.ACE));
        cardList.add(new Card(Suit.DIAMOND, Face.KING));

        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));
        assertThat(challenger.getResult(dealer)).isEqualTo(Result.BLACKJACK);
    }

    @Test
    @DisplayName("챌린저와 딜러가 둘다 블랙잭이면, 무승부를 반환한다.")
    void getChallengerResultTwinBlackJack() {
        List<Card> playerCards = new ArrayList<>();
        playerCards.add(new Card(Suit.DIAMOND, Face.QUEEN));
        playerCards.add(new Card(Suit.DIAMOND, Face.ACE));

        List<Card> dealerCards = new ArrayList<>();
        dealerCards.add(new Card(Suit.CLOVER, Face.QUEEN));
        dealerCards.add(new Card(Suit.CLOVER, Face.ACE));

        Challenger challenger = new Challenger(new Cards(playerCards), new Name("pobi"));
        Dealer dealer = new Dealer(new Cards(dealerCards));

        assertThat(challenger.getResult(dealer)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("챌린저의 점수가 딜러의 점수와 같으면, 무승부를 반환한다.")
    void getChallengerResultIsDrawer() {
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Suit.DIAMOND, Face.QUEEN));
        cardList.add(new Card(Suit.DIAMOND, Face.NINE));

        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));
        assertThat(challenger.getResult(dealer)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("챌린저의 점수가 딜러의 점수보다 낮으면, 패배를 반환한다.")
    void getChallengerResultIsLoser() {
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Suit.DIAMOND, Face.ACE));
        cardList.add(new Card(Suit.DIAMOND, Face.ACE));

        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));
        assertThat(challenger.getResult(dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("챌린저가 버스트가 나고 딜러도 버스트가 나면 챌린저가 패배한다.")
    void getChallengerResultbbothBust() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.HEART, Face.JACK));
        cardList.add(new Card(Suit.SPADE, Face.JACK));

        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));

        Cards cards = new Cards(dealer.getHand());
        cards.add(new Card(Suit.HEART, Face.JACK));

        Dealer dealer1 = new Dealer(cards);

        assertThat(challenger.getResult(dealer1)).isEqualTo(Result.LOSE);
    }
}