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
        deck.addCard(new CourtCard(Pattern.SPADE, "K"));
        deck.addCard(new StandardCard(Pattern.SPADE, "4"));
        Assertions.assertThat(referee.calculateDeckScore(deck)).isEqualTo(14);
    }

    @Test
    void testBurstCase() {
        CardDeck deck = new CardDeck();
        deck.addCard(new CourtCard(Pattern.SPADE, "K"));
        deck.addCard(new CourtCard(Pattern.SPADE, "J"));
        deck.addCard(new CourtCard(Pattern.SPADE, "Q"));

        Assertions.assertThat(referee.calculateDeckScore(deck)).isEqualTo(-1);
    }


    @Nested
    class appendAceCase {

        @Test
        void testOneAceCase() {
            CardDeck deck = new CardDeck();
            deck.addCard(new CourtCard(Pattern.SPADE, "K"));
            deck.addCard(new StandardCard(Pattern.SPADE, "4"));
            deck.addCard(new AceCard(Pattern.HEART));
            Assertions.assertThat(referee.calculateDeckScore(deck)).isEqualTo(15);
        }

        @Test
        void testElevenAceCase() {
            CardDeck deck = new CardDeck();
            deck.addCard(new StandardCard(Pattern.SPADE, "2"));
            deck.addCard(new StandardCard(Pattern.SPADE, "4"));
            deck.addCard(new AceCard(Pattern.HEART));

            Assertions.assertThat(referee.calculateDeckScore(deck)).isEqualTo(17);
        }

        @Test
        void testManyAceCase() {
            CardDeck deck = new CardDeck();
            deck.addCard(new StandardCard(Pattern.SPADE, "2"));
            deck.addCard(new AceCard(Pattern.HEART));
            deck.addCard(new AceCard(Pattern.CLUB));
            deck.addCard(new StandardCard(Pattern.SPADE, "4"));
            deck.addCard(new AceCard(Pattern.DIAMOND));

            Assertions.assertThat(referee.calculateDeckScore(deck)).isEqualTo(19);
        }

        @Test
        void testManyAceBurstCase() {
            CardDeck deck = new CardDeck();
            deck.addCard(new CourtCard(Pattern.SPADE, "K"));
            deck.addCard(new AceCard(Pattern.SPADE));
            deck.addCard(new AceCard(Pattern.HEART));
            deck.addCard(new CourtCard(Pattern.SPADE, "J"));
            deck.addCard(new AceCard(Pattern.CLUB));

            Assertions.assertThat(referee.calculateDeckScore(deck)).isEqualTo(-1);
        }
    }

    @Test
    void testResult() {

        //int dealerScore = 20;
        //List<Integer> playerScores = List.of(21, 9, 20, -1);

        Participant dealer = new Dealer();
        Players players = new Players("무민,아마란스,프리지아,수국");
        Card card1 = new StandardCard(Pattern.CLUB, "10");
        Card card2 = new StandardCard(Pattern.CLUB, "4");
        Card card3 = new StandardCard(Pattern.CLUB, "6");

        dealer.hit(new StubCardPicker(card1));
        dealer.hit(new StubCardPicker(card2));
        dealer.hit(new StubCardPicker(card3));

        Card card4 = new StandardCard(Pattern.SPADE, "10");
        Card card5 = new AceCard(Pattern.SPADE);
        players.getPlayers().get(0).hit(new StubCardPicker(card4));
        players.getPlayers().get(0).hit(new StubCardPicker(card5));

        Card card7 = new StandardCard(Pattern.CLUB, "9");
        players.getPlayers().get(1).hit(new StubCardPicker(card7));

        Card card8 = new StandardCard(Pattern.HEART, "10");
        Card card9 = new StandardCard(Pattern.HEART, "4");
        Card card10 = new StandardCard(Pattern.HEART, "6");

        players.getPlayers().get(2).hit(new StubCardPicker(card8));
        players.getPlayers().get(2).hit(new StubCardPicker(card9));
        players.getPlayers().get(2).hit(new StubCardPicker(card10));

        Card card11 = new StandardCard(Pattern.HEART, "10");
        Card card12 = new StandardCard(Pattern.DIAMOND, "10");
        Card card13 = new CourtCard(Pattern.DIAMOND, "K");

        players.getPlayers().get(3).hit(new StubCardPicker(card11));
        players.getPlayers().get(3).hit(new StubCardPicker(card12));
        players.getPlayers().get(3).hit(new StubCardPicker(card13));

        List<Result> result = referee.judge(dealer, players);
        Assertions.assertThat(result)
            .isEqualTo(List.of(Result.WIN, Result.LOSE, Result.DRAW, Result.LOSE));
    }


}