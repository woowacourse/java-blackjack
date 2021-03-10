package blackjack;

public class OutcomeTest {
    /*

    @Test
    @DisplayName("Dealer:21, Player:22 경우 -> 딜러 승")
    void result0() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.KING),
            Card.from(Suits.CLOVER, Denominations.SIX));
        Dealer dealer = new Dealer(new Cards(cards));
        dealer.takeCard(Card.from(Suits.SPADE, Denominations.SIX));

        final Score dealerScore = dealer.sumCardsForResult();

        assertThat(Outcome.getInstance(dealerScore, Score.of(22))).isEqualTo(Outcome.WIN);
    }


    @Test
    @DisplayName("Dealer: 20, Player:22 -> 딜러 패")
    void result2() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.KING),
            Card.from(Suits.CLOVER, Denominations.KING));
        Dealer dealer = new Dealer(new Cards(cards));

        final Score dealerScore = dealer.sumCardsForResult();

        assertThat(Outcome.getInstance(dealerScore, Score.of(22))).isEqualTo(Outcome.LOSE);
    }

    @Test
    @DisplayName("Dealer:20, Player:21 경우 -> 딜러 패")
    void result1() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.KING),
            Card.from(Suits.CLOVER, Denominations.KING));
        Dealer dealer = new Dealer(new Cards(cards));
        final Score dealerScore = dealer.sumCardsForResult();

        assertThat(Outcome.getInstance(dealerScore, Score.of(21))).isEqualTo(Outcome.LOSE);
    }

    @Test
    @DisplayName("Dealer:20, Player:20 -> 딜러 무")
    void result3() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.KING),
            Card.from(Suits.CLOVER, Denominations.KING));
        Dealer dealer = new Dealer(new Cards(cards));

        final Score dealerScore = dealer.sumCardsForResult();

        assertThat(Outcome.getInstance(dealerScore, Score.of(20))).isEqualTo(Outcome.DRAW);
    }

    @Test
    @DisplayName("Dealer:21, Player:21 -> 딜러 무")
    void result4() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.KING),
            Card.from(Suits.CLOVER, Denominations.SIX));
        Dealer dealer = new Dealer(new Cards(cards));
        dealer.takeCard(Card.from(Suits.SPADE, Denominations.SIX));

        final Score dealerScore = dealer.sumCardsForResult();

        assertThat(Outcome.getInstance(dealerScore, Score.of(21))).isEqualTo(Outcome.DRAW);
    }

    @Test
    @DisplayName("Dealer:22, Player:22 -> 딜러 무")
    void result5() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.KING),
            Card.from(Suits.CLOVER, Denominations.SIX));
        Dealer dealer = new Dealer(new Cards(cards));
        dealer.takeCard(Card.from(Suits.SPADE, Denominations.SEVEN));

        final Score dealerScore = dealer.sumCardsForResult();

        assertThat(Outcome.getInstance(dealerScore, Score.of(22))).isEqualTo(Outcome.DRAW);
    }
     */
}
