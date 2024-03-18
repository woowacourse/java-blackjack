package blackjack.domain.fixture;

import blackjack.domain.card.Hand;

public class HandFixture {

    public static Hand BLACKJACK = new Hand(CardsFixture.BLACKJACK);
    public static Hand BUSTED = new Hand(CardsFixture.BUSTED);
    public static Hand CARDS_SCORE_16 = new Hand(CardsFixture.CARDS_SCORE_16);
    public static Hand CARDS_SCORE_17 = new Hand(CardsFixture.CARDS_SCORE_17);
    public static Hand CARDS_SCORE_21 = new Hand(CardsFixture.CARDS_SCORE_21);
}
