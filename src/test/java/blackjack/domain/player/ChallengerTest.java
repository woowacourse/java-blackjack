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

public class ChallengerTest {

    Dealer dealer;

    @BeforeEach
    void setUp() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.KING));
        dealer = new Dealer(new Cards(cardList));
    }

    @Test
    @DisplayName("챌린저의 점수가 딜러의 점수보다 높으면, 승리를 반환한다.")
    void getChallengerResultIsWinner() {
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Suit.DIAMOND, Face.ACE));
        cardList.add(new Card(Suit.DIAMOND, Face.KING));

        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));

        assertThat(challenger.getChallengerResult(dealer)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("챌린저의 점수가 딜러의 점수와 같으면, 무승부를 반환한다.")
    void getChallengerResultIsDrawer() {
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Suit.DIAMOND, Face.QUEEN));
        cardList.add(new Card(Suit.DIAMOND, Face.KING));

        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));

        assertThat(challenger.getChallengerResult(dealer)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("챌린저의 점수가 딜러의 점수보다 낮으면, 패배를 반환한다.")
    void getChallengerResultIsLoser() {
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Suit.DIAMOND, Face.ACE));
        cardList.add(new Card(Suit.DIAMOND, Face.ACE));

        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));

        assertThat(challenger.getChallengerResult(dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("챌린저가 bust되면, 챌린저의 패배를 반환한다.")
    void getChallengerResultIsLoserWhenChallengerIsBusted() {
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Suit.DIAMOND, Face.ACE));
        cardList.add(new Card(Suit.DIAMOND, Face.ACE));
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.QUEEN));

        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));

        assertThat(challenger.getChallengerResult(dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("딜러가 bust되면, 챌린저의 승리를 반환한다.")
    void getChallengerResultIsWinnerWhenDealerIsBusted() {
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Suit.DIAMOND, Face.ACE));
        cardList.add(new Card(Suit.DIAMOND, Face.ACE));
        dealer.receiveMoreCard(new Card(Suit.DIAMOND, Face.QUEEN));

        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));

        assertThat(challenger.getChallengerResult(dealer)).isEqualTo(Result.WIN);
    }
}
