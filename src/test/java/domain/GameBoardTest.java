package domain;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardNumber;
import domain.card.CardSymbol;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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

    @DisplayName("총 플레잉 카드 52장에서 참가자 3명이 각각 2장씩 가져가 총 46장이 남는다.")
    @Test
    void 카드덱_2장_빠지기() {
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

        Assertions.assertThat(cardDeck.getCards().size()).isEqualTo(46);
    }

    @Test
    void 카드를_참가자에게_1장_준다() {
        //given
        Participant targetParticipant = new Player("우가");
        List<Participant> participants = List.of(
                targetParticipant,
                new Player("히스타"),
                new Dealer("딜러")
        );
        CardDeck cardDeck = CardDeck.generateFullSet();
        GameBoard gameBoard = new GameBoard(cardDeck, participants);

        //when
        gameBoard.drawCardTo(targetParticipant);

        //then
        CardDeck ownedCardDeck = gameBoard.getCardDeckOfParticipant().get(targetParticipant);
        Assertions.assertThat(ownedCardDeck.getCards().size()).isEqualTo(1);
    }
}
