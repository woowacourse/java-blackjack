package blackjack.domain;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Denomination.SEVEN;
import static blackjack.domain.card.Denomination.SIX;
import static blackjack.domain.card.Suit.CLUB;
import static blackjack.domain.card.Suit.DIAMOND;
import static blackjack.domain.card.Suit.SPADE;

import blackjack.domain.card.Card;
import blackjack.domain.participants.BettingMoney;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import java.util.List;

public class Fixtures {

    public static final BettingMoney BETTING_MONEY_1000 = new BettingMoney(1000);

    public static final Dealer DEALER_17 = new Dealer("딜러", List.of(new Card(SPADE, QUEEN), new Card(CLUB, SEVEN)));
    public static final Player BLACKJACK_WIN_PLAYER =
            new Player("blackjackWinPlayer", List.of(new Card(DIAMOND, QUEEN), new Card(DIAMOND, ACE)),
                    BETTING_MONEY_1000);
    public static final Player WIN_PLAYER =
            new Player("winPlayer", List.of(new Card(DIAMOND, QUEEN), new Card(DIAMOND, JACK)), BETTING_MONEY_1000);
    public static final Player LOSE_PLAYER =
            new Player("losePlayer", List.of(new Card(CLUB, QUEEN), new Card(CLUB, SIX)), BETTING_MONEY_1000);
    public static final Player PUSH_PLAYER =
            new Player("pushPlayer", List.of(new Card(CLUB, QUEEN), new Card(CLUB, SEVEN)), BETTING_MONEY_1000);
}
