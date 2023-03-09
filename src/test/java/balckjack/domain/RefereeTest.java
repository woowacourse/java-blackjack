package balckjack.domain;

import helper.StubCardPicker;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RefereeTest {

    Referee referee;

    @BeforeEach
    void init() {
        referee = new Referee();
    }

    @Test
    void testCommonCase() {
        CardDeck deck = new CardDeck();
        deck.addCard(new Card(Pattern.SPADE, Denomination.KING));
        deck.addCard(new Card(Pattern.SPADE, Denomination.FOUR));
        Assertions.assertThat(referee.calculateDeckScore(deck)).isEqualTo(14);
    }

    @Test
    void testBurstCase() {
        CardDeck deck = new CardDeck();
        deck.addCard(new Card(Pattern.SPADE, Denomination.KING));
        deck.addCard(new Card(Pattern.SPADE, Denomination.JACK));
        deck.addCard(new Card(Pattern.SPADE, Denomination.QUEEN));

        Assertions.assertThat(referee.isBurst(deck)).isTrue();
    }


    @Nested
    class appendAceCase {

        @Test
        void testOneAceCase() {
            CardDeck deck = new CardDeck();
            deck.addCard(new Card(Pattern.SPADE, Denomination.KING));
            deck.addCard(new Card(Pattern.SPADE, Denomination.FOUR));
            deck.addCard(new Card(Pattern.HEART, Denomination.ACE));
            Assertions.assertThat(referee.calculateDeckScore(deck)).isEqualTo(15);
        }

        @Test
        void testElevenAceCase() {
            CardDeck deck = new CardDeck();
            deck.addCard(new Card(Pattern.SPADE, Denomination.TWO));
            deck.addCard(new Card(Pattern.SPADE, Denomination.FOUR));
            deck.addCard(new Card(Pattern.HEART, Denomination.ACE));

            Assertions.assertThat(referee.calculateDeckScore(deck)).isEqualTo(17);
        }

        @Test
        void testManyAceCase() {
            CardDeck deck = new CardDeck();
            deck.addCard(new Card(Pattern.SPADE, Denomination.TWO));
            deck.addCard(new Card(Pattern.HEART, Denomination.ACE));
            deck.addCard(new Card(Pattern.CLOVER, Denomination.ACE));
            deck.addCard(new Card(Pattern.SPADE, Denomination.FOUR));
            deck.addCard(new Card(Pattern.DIAMOND, Denomination.ACE));

            Assertions.assertThat(referee.calculateDeckScore(deck)).isEqualTo(19);
        }

        @Test
        void testManyAceBurstCase() {
            CardDeck deck = new CardDeck();
            deck.addCard(new Card(Pattern.SPADE, Denomination.KING));
            deck.addCard(new Card(Pattern.SPADE, Denomination.ACE));
            deck.addCard(new Card(Pattern.HEART, Denomination.ACE));
            deck.addCard(new Card(Pattern.SPADE, Denomination.JACK));
            deck.addCard(new Card(Pattern.CLOVER, Denomination.ACE));

            Assertions.assertThat(referee.isBurst(deck)).isTrue();
        }
    }

    @Test
    void testResult() {

        //int dealerScore = 20;
        //List<Integer> playerScores = List.of(21, 9, 20, -1);

        Participant dealer = new Dealer();
        Players players = new Players("무민,아마란스,프리지아,수국");
        Card card1 = new Card(Pattern.CLOVER, Denomination.TEN);
        Card card2 = new Card(Pattern.CLOVER, Denomination.FOUR);
        Card card3 = new Card(Pattern.CLOVER, Denomination.SIX);

        dealer.hit(new StubCardPicker(card1));
        dealer.hit(new StubCardPicker(card2));
        dealer.hit(new StubCardPicker(card3));

        Card card4 = new Card(Pattern.SPADE, Denomination.TEN);
        Card card5 = new Card(Pattern.SPADE, Denomination.ACE);
        players.getPlayers().get(0).hit(new StubCardPicker(card4));
        players.getPlayers().get(0).hit(new StubCardPicker(card5));

        Card card7 = new Card(Pattern.CLOVER, Denomination.NINE);
        players.getPlayers().get(1).hit(new StubCardPicker(card7));

        Card card8 = new Card(Pattern.HEART, Denomination.TEN);
        Card card9 = new Card(Pattern.HEART, Denomination.FOUR);
        Card card10 = new Card(Pattern.HEART, Denomination.SIX);

        players.getPlayers().get(2).hit(new StubCardPicker(card8));
        players.getPlayers().get(2).hit(new StubCardPicker(card9));
        players.getPlayers().get(2).hit(new StubCardPicker(card10));

        Card card11 = new Card(Pattern.HEART, Denomination.TEN);
        Card card12 = new Card(Pattern.DIAMOND, Denomination.TEN);
        Card card13 = new Card(Pattern.DIAMOND, Denomination.KING);

        players.getPlayers().get(3).hit(new StubCardPicker(card11));
        players.getPlayers().get(3).hit(new StubCardPicker(card12));
        players.getPlayers().get(3).hit(new StubCardPicker(card13));

        List<Result> result = referee.judgeResult(dealer, players);
        Assertions.assertThat(result)
            .isEqualTo(List.of(Result.WIN, Result.LOSE, Result.DRAW, Result.LOSE));
    }

    @Test
    void testTwoBurstResult() {

        //int dealerScore = -1;
        //List<Integer> playerScores = List.of(21, 9, 20, -1);

        Participant dealer = new Dealer();
        Players players = new Players("무민,아마란스,프리지아,수국");
        Card card1 = new Card(Pattern.CLOVER, Denomination.TEN);
        Card card2 = new Card(Pattern.CLOVER, Denomination.FOUR);
        Card card3 = new Card(Pattern.CLOVER, Denomination.NINE);

        dealer.hit(new StubCardPicker(card1));
        dealer.hit(new StubCardPicker(card2));
        dealer.hit(new StubCardPicker(card3));

        Card card4 = new Card(Pattern.SPADE, Denomination.TEN);
        Card card5 = new Card(Pattern.SPADE, Denomination.ACE);
        players.getPlayers().get(0).hit(new StubCardPicker(card4));
        players.getPlayers().get(0).hit(new StubCardPicker(card5));

        Card card7 = new Card(Pattern.CLOVER, Denomination.NINE);
        players.getPlayers().get(1).hit(new StubCardPicker(card7));

        Card card8 = new Card(Pattern.HEART, Denomination.TEN);
        Card card9 = new Card(Pattern.HEART, Denomination.FOUR);
        Card card10 = new Card(Pattern.HEART, Denomination.SIX);

        players.getPlayers().get(2).hit(new StubCardPicker(card8));
        players.getPlayers().get(2).hit(new StubCardPicker(card9));
        players.getPlayers().get(2).hit(new StubCardPicker(card10));

        Card card11 = new Card(Pattern.HEART, Denomination.TEN);
        Card card12 = new Card(Pattern.DIAMOND, Denomination.TEN);
        Card card13 = new Card(Pattern.DIAMOND, Denomination.KING);

        players.getPlayers().get(3).hit(new StubCardPicker(card11));
        players.getPlayers().get(3).hit(new StubCardPicker(card12));
        players.getPlayers().get(3).hit(new StubCardPicker(card13));

        List<Result> result = referee.judgeResult(dealer, players);
        Assertions.assertThat(result)
            .isEqualTo(List.of(Result.WIN, Result.WIN, Result.WIN, Result.LOSE));
    }


}