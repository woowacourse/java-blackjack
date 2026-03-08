package blackjack.service;

import blackjack.model.Card;
import blackjack.model.CardsGenerator;
import blackjack.model.Rank;
import blackjack.model.Suit;
import java.util.List;
import org.junit.jupiter.api.Test;

class BlackjackServiceTest {

    private static final CardsGenerator FIXED_CARD_GENERATOR = () -> {
        return List.of(new Card(Rank.KING, Suit.CLOVER),
            new Card(Rank.KING, Suit.CLOVER))
            ;
    };

    @Test
    void 덱을_생성한다() {

    }

}