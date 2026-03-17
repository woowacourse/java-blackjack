package domain;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Rank;
import domain.card.Suit;
import domain.game.GameResult;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static domain.BlackjackRule.DEALER_NAME;

class GameResultTest {
    Player pobi;
    Players players;
    Dealer dealer;

    @BeforeEach
    void setUp() {
        pobi = new Player("pobi");
        players = new Players(List.of(pobi));

        pobi.bet(BigDecimal.valueOf(1000));

        dealer = new Dealer(DEALER_NAME);
    }


    private Cards cards(Card... cards) {
        return new Cards(List.of(cards));
    }

    private Card card(Rank rank, Suit suit) {
        return new Card(rank, suit);
    }
}
