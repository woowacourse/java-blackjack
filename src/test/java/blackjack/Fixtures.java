package blackjack;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Pattern;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.player.BetAmount;
import blackjack.domain.participant.player.Player;
import java.util.List;

public class Fixtures {
    public static final BetAmount BET_AMOUNT_10000 = new BetAmount(10000);
    public static final List<Card> BLACKJACK_CARDS = List.of(new Card(CardNumber.ACE, Pattern.DIAMOND),
            new Card(CardNumber.KING, Pattern.HEART));
    public static final List<Card> CARDS_SUM_15 = List.of(new Card(CardNumber.KING, Pattern.HEART),
            new Card(CardNumber.FIVE, Pattern.CLOVER));

    public static final List<Card> CARDS_SUM_17 = List.of(new Card(CardNumber.KING, Pattern.HEART),
            new Card(CardNumber.SEVEN, Pattern.CLOVER));
    public static final Player PLAYER_WITH_10000 = new Player(new Name("로지"), new BetAmount(10000));
    public static final Player PLAYER_WITH_20000 = new Player(new Name("홍실"), new BetAmount(20000));
    public static final List<Card> CARDS_OF_BUST = List.of(new Card(CardNumber.KING, Pattern.HEART),
            new Card(CardNumber.KING, Pattern.DIAMOND),
            new Card(CardNumber.KING, Pattern.SPADE));

}
