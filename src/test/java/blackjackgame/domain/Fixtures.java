package blackjackgame.domain;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.CloverCard;
import blackjackgame.domain.card.DiamondCard;
import blackjackgame.domain.card.HeartCard;
import blackjackgame.domain.card.SpadeCard;
import java.util.List;

public class Fixtures {
    //버스트
    public static final List<Card> JACK_KING_NINE_CARDS = List.of(CloverCard.CLOVER_JACK, DiamondCard.DIAMOND_KING,
            SpadeCard.SPADE_NINE);

    // 1 ~ 16
    public static final List<Card> SIX_FOUR_CARDS = List.of(CloverCard.CLOVER_SIX, HeartCard.HEART_FOUR);
    public static final List<Card> ACE_ACE_ACE_ACE_CARDS = List.of(CloverCard.CLOVER_ACE, SpadeCard.SPADE_ACE,
            DiamondCard.DIAMOND_ACE, HeartCard.HEART_ACE);

    // 17 ~ 20
    public static final List<Card> EIGHT_NINE_CARDS = List.of(CloverCard.CLOVER_EIGHT, SpadeCard.SPADE_NINE);
    public static final List<Card> ACE_THREE_FOUR_CARDS = List.of((CloverCard.CLOVER_ACE), SpadeCard.SPADE_THREE,
            HeartCard.HEART_FOUR);

    // 21
    public static final List<Card> ACE_KING_CARDS = List.of(CloverCard.CLOVER_ACE, CloverCard.CLOVER_KING);
    public static final List<Card> JACK_KING_ACE_CARDS = List.of(CloverCard.CLOVER_JACK, DiamondCard.DIAMOND_KING,
            SpadeCard.SPADE_ACE);
}
