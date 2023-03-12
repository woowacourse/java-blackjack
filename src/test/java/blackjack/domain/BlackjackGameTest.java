package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSuit;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackGameTest {

    @Test
    @DisplayName("플레이어는 두 장씩 카드를 받는다")
    void addTwoCardsTest() {
        List<String> names = List.of("jamie", "boxster");

        Participants participants = Participants.from(names);

        BlackjackGame blackjackGame = new BlackjackGame(participants);
        blackjackGame.dealOutCard();

        Participant participant = participants.getPlayers()
                                              .get(0);
        assertThat(participant.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("게임 결과에 대한 베팅 금액을 반환한다")
    void getGameResultTest() {
        List<String> names = List.of("jamie", "boxster");

        Participants participants = Participants.from(names);
        List<Player> players = participants.getPlayers();
        players.get(0)
               .initBetAmount(1000);
        players.get(1)
               .initBetAmount(1000);
        List<Card> cards = new ArrayList<>(List.of(
                new Card(CardSuit.HEART, CardNumber.TWO), new Card(CardSuit.HEART, CardNumber.SEVEN),
                new Card(CardSuit.HEART, CardNumber.JACK), new Card(CardSuit.SPADE, CardNumber.KING),
                new Card(CardSuit.SPADE, CardNumber.KING), new Card(CardSuit.HEART, CardNumber.THREE)));
        BlackjackGame blackjackGame = new BlackjackGame(participants, new CardDeck(cards));
        blackjackGame.dealOutCard();
        blackjackGame.calculateBetAmount();

        Assertions.assertEquals(-1000, players.get(0)
                                              .getBetAmount());
        Assertions.assertEquals(1000, players.get(1)
                                             .getBetAmount());
    }
}
