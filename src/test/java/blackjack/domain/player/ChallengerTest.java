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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

        assertThat(Result.getPlayerResult(challenger, dealer)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("챌린저의 점수가 딜러의 점수와 같으면, 무승부를 반환한다.")
    void getChallengerResultIsDrawer() {
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Suit.DIAMOND, Face.QUEEN));
        cardList.add(new Card(Suit.DIAMOND, Face.KING));

        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));

        assertThat(Result.getPlayerResult(challenger, dealer)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("챌린저의 점수가 딜러의 점수보다 낮으면, 패배를 반환한다.")
    void getChallengerResultIsLoser() {
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Suit.DIAMOND, Face.ACE));
        cardList.add(new Card(Suit.DIAMOND, Face.ACE));

        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));

        assertThat(Result.getPlayerResult(challenger, dealer)).isEqualTo(Result.LOSE);
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

        assertThat(Result.getPlayerResult(challenger, dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("딜러가 bust되면, 챌린저의 승리를 반환한다.")
    void getChallengerResultIsWinnerWhenDealerIsBusted() {
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Suit.DIAMOND, Face.ACE));
        cardList.add(new Card(Suit.DIAMOND, Face.ACE));
        dealer.receiveMoreCard(new Card(Suit.DIAMOND, Face.QUEEN));

        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));

        assertThat(Result.getPlayerResult(challenger, dealer)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("참여인원이 1명 미만일 경우, 예외 처리한다")
    void whenChallengersLessThanOneThrowsException(){
        List<Challenger> challengers = new ArrayList<>();
        assertThatThrownBy(() -> new Challengers(challengers)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임 참가자의수는 1명 이상 7명 이하여야 합니다.");
    }

    @Test
    @DisplayName("참여인원이 7명 초과일 경우, 예외 처리한다")
    void whenChallengersGreaterThanSevenThrowsException(){
        List<Challenger> challengers = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            List<Card> cardList = new ArrayList<>();

            cardList.add(new Card(Suit.DIAMOND, Face.ACE));
            cardList.add(new Card(Suit.DIAMOND, Face.ACE));

            Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));
            challengers.add(challenger);
        }
        assertThatThrownBy(() -> new Challengers(challengers)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임 참가자의수는 1명 이상 7명 이하여야 합니다.");
    }
}
