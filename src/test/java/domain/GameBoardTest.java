package domain;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameBoardTest {

    @Test
    void 게임판_테스트() {
        //given
        Player player1 = new Player("우가");
        Player player2 = new Player("히스타");
        CardDeck gameCardDeck = CardDeck.generateFullSet();

        //when
        GameBoard gameBoard = new GameBoard(gameCardDeck, List.of(player1, player2));

        //then
        Assertions.assertThat(gameBoard).isInstanceOf(GameBoard.class);
    }

    @Test
    void 카드를_모두에게_2장씩_나눠준다() {
        //given
        List<Participant> participants = List.of(
                new Player("우가"),
                new Player("히스타"),
                new Dealer("딜러")
        );
        CardDeck cardDeck = CardDeck.generateFullSet();
        GameBoard gameBoard = new GameBoard(cardDeck, participants);

        //when
        gameBoard.drawTwoCards();

        Map<Participant, CardDeck> cardDeckOfParticipant = gameBoard.getCardDeckOfParticipant();

        for(Map.Entry<Participant, CardDeck> entry : cardDeckOfParticipant.entrySet()) {
            CardDeck ownedCardDeck = entry.getValue();
            Assertions.assertThat(ownedCardDeck.getCards().size()).isEqualTo(2);
        }
    }
}
