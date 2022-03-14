package blackjack.domain.player;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckGeneratorImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetTest {

    @Test
    @DisplayName("승리시 1.5배, 패배시 배팅돈을 잃는다.")
    void checkParticipantProfit(){
        Deck deck = new Deck(new DeckGeneratorImpl());
        List<Card> initCards = new ArrayList<>();
        initCards.add(deck.draw());
        initCards.add(deck.draw());

        Participant participantWin = new Participant(initCards, "pobi", new Bet(1000));
        Participant participantLose = new Participant(initCards, "pobi", new Bet(1000));
        participantWin.win();
        participantLose.lose();

        assertThat(participantWin.getBetProfit()).isEqualTo(500);
        assertThat(participantLose.getBetProfit()).isEqualTo(-1000);
    }
}
