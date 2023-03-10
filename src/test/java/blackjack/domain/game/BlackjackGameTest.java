package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSuit;
import blackjack.domain.player.Participants;
import blackjack.domain.player.Player;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @Test
    @DisplayName("플레이어는 두 장씩 카드를 받는다")
    void addTwoCardsTest() {
        List<String> names = List.of("jamie", "boxster");
        Participants participants = Participants.from(names);

        BlackjackGame blackjackGame = new BlackjackGame(participants);
        blackjackGame.dealOutCard();

        Player player = participants.getPlayers().get(0);
        assertThat(player.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("게임 승패를 반환한다")
    void getGameResultTest() {
        List<String> names = List.of("jamie");
        Participants participants = Participants.from(names);
        List<Card> cards = new ArrayList<>(
                List.of(
                        new Card(CardSuit.HEART, CardNumber.TWO), new Card(CardSuit.HEART, CardNumber.SEVEN),
                        new Card(CardSuit.HEART, CardNumber.JACK), new Card(CardSuit.SPADE, CardNumber.KING)
                ));
        BlackjackGame blackjackGame = new BlackjackGame(participants, new CardDeck(cards));
        blackjackGame.dealOutCard();

        BlackjackResult result = blackjackGame.getResult();

        Player player = participants.getPlayers().get(0);
        assertThat(result.get(player)).isEqualTo(GameResult.LOSE);
    }
}
