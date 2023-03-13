package blackjack.domain;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.FIVE;
import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Denomination.SEVEN;
import static blackjack.domain.card.Denomination.SIX;
import static blackjack.domain.card.Denomination.TEN;
import static blackjack.domain.card.Suit.CLUB;
import static blackjack.domain.card.Suit.DIAMOND;
import static blackjack.domain.card.Suit.SPADE;

import blackjack.domain.card.Card;
import blackjack.domain.participants.BettingMoney;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import java.math.BigDecimal;
import java.util.List;

public class ParticipantFixtures {

    public static final BettingMoney BETTING_MONEY_1000 = new BettingMoney(new BigDecimal("1000"));

    public static final Dealer DEALER_17 = new Dealer(List.of(new Card(SPADE, QUEEN), new Card(CLUB, SEVEN)));
    public static final Dealer DEALER_BLACKJACK = new Dealer(
            List.of(new Card(SPADE, QUEEN), new Card(CLUB, ACE)));
    public static final Dealer DEALER_BUST = new Dealer(
            List.of(new Card(SPADE, QUEEN), new Card(CLUB, SEVEN), new Card(CLUB, FIVE)));
    public static final Player PLAYER_BLACKJACK =
            new Player("playerBlackJack", List.of(new Card(DIAMOND, QUEEN), new Card(DIAMOND, ACE)),
                    BETTING_MONEY_1000);
    public static final Player PLAYER_20 =
            new Player("player20", List.of(new Card(DIAMOND, QUEEN), new Card(DIAMOND, JACK)), BETTING_MONEY_1000);
    public static final Player PLAYER_16 =
            new Player("player16", List.of(new Card(CLUB, QUEEN), new Card(CLUB, SIX)), BETTING_MONEY_1000);
    public static final Player PLAYER_17 =
            new Player("player17", List.of(new Card(CLUB, QUEEN), new Card(CLUB, SEVEN)), BETTING_MONEY_1000);
    public static final Player PLAYER_BUST =
            new Player("playerBust", List.of(new Card(CLUB, QUEEN), new Card(CLUB, KING), new Card(DIAMOND, TEN)),
                    BETTING_MONEY_1000);
}
