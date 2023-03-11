package blackjackgame.domain;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.CloverCard;
import blackjackgame.domain.card.DiamondCard;
import blackjackgame.domain.card.HeartCard;
import blackjackgame.domain.card.SpadeCard;
import java.util.List;

public class Fixtures {
    //버스트
    public static final List<Card> jackKingNine = List.of(CloverCard.CLOVER_JACK, DiamondCard.DIAMOND_KING,
            SpadeCard.SPADE_NINE);

    // 1 ~ 16
    public static final List<Card> sixFour = List.of(CloverCard.CLOVER_SIX, HeartCard.HEART_FOUR);
    public static final List<Card> aceAceAceAce = List.of(CloverCard.CLOVER_ACE, SpadeCard.SPADE_ACE,
            DiamondCard.DIAMOND_ACE, HeartCard.HEART_ACE);

    // 17 ~ 20
    public static final List<Card> eightNine = List.of(CloverCard.CLOVER_EIGHT, SpadeCard.SPADE_NINE);
    public static final List<Card> aceThreeFour = List.of((CloverCard.CLOVER_ACE), SpadeCard.SPADE_THREE,
            HeartCard.HEART_FOUR);

    // 21
    public static final List<Card> aceKing = List.of(CloverCard.CLOVER_ACE, CloverCard.CLOVER_KING);
    public static final List<Card> jackKingAce = List.of(CloverCard.CLOVER_JACK, DiamondCard.DIAMOND_KING,
            SpadeCard.SPADE_ACE);
}
