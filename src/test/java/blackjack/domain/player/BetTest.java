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
    @DisplayName("승리시 1.5배를 얻는다.")
    void checkParticipantWinProfit(){
        Deck deck = new Deck(new DeckGeneratorImpl());
        List<Card> initCards = new ArrayList<>();
        initCards.add(deck.draw());
        initCards.add(deck.draw());

        Participant participantWin = new Participant(initCards, "pobi", new Bet(1000));
        participantWin.win();

        assertThat(participantWin.getBetProfit()).isEqualTo(500);
    }

    @Test
    @DisplayName("패배시 배팅돈을 모두 잃는다.")
    void checkParticipantLoseProfit(){
        Deck deck = new Deck(new DeckGeneratorImpl());
        List<Card> initCards = new ArrayList<>();
        initCards.add(deck.draw());
        initCards.add(deck.draw());

        Participant participantLose = new Participant(initCards, "pobi", new Bet(1000));
        participantLose.lose();

        assertThat(participantLose.getBetProfit()).isEqualTo(-1000);
    }
}
