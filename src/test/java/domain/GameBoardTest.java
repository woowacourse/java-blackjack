package domain;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardNumber;
import domain.card.CardSymbol;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameBoardTest {

    @Test
    void 게임판_테스트() {
        //given
        Player player1 = Player.from("우가");
        Player player2 = Player.from("히스타");

        //when
        GameBoard gameBoard = new GameBoard(List.of(player1, player2));

        //then
        Assertions.assertThat(gameBoard).isInstanceOf(GameBoard.class);
    }

    @Test
    void 카드를_모두에게_2장씩_나눠준다() {
        //given
        List<Participant> participants = List.of(
                Player.from("우가"),
                Player.from("히스타"),
                Dealer.generate()
        );
        GameBoard gameBoard = new GameBoard(participants);

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
                Player.from("우가"),
                Player.from("히스타"),
                Dealer.generate()
        );
        GameBoard gameBoard = new GameBoard(participants);

        //when
        gameBoard.drawTwoCards();

        Assertions.assertThat(gameBoard.getPlayingCard().getCards().size()).isEqualTo(46);
    }

    @Test
    void 카드를_참가자에게_1장_준다() {
        //given
        Participant targetParticipant = Player.from("우가");
        List<Participant> participants = List.of(
                targetParticipant,
                Player.from("히스타"),
                Dealer.generate()
        );
        GameBoard gameBoard = new GameBoard(participants);

        //when
        gameBoard.drawCardTo(targetParticipant);

        //then
        CardDeck ownedCardDeck = gameBoard.getCardDeckOfParticipant().get(targetParticipant);
        Assertions.assertThat(ownedCardDeck.getCards().size()).isEqualTo(1);
    }

    @Test
    void 한명의_카드덱을_가져온다() {
        //given
        Participant targetParticipant = Player.from("우가");
        List<Participant> participants = List.of(
                targetParticipant,
                Player.from("히스타"),
                Dealer.generate()
        );
        GameBoard gameBoard = new GameBoard(participants);
        gameBoard.drawCardTo(targetParticipant);
        //when
        CardDeck cardDeck = gameBoard.getCardDeckOf(targetParticipant);

        //then
        Assertions.assertThat(cardDeck.getCards().size()).isEqualTo(1);
    }

    @Test
    void 한명의_총_점수를_계산한다() {
        //given
        Participant targetParticipant = Player.from("우가");
        List<Participant> participants = List.of(
                targetParticipant,
                Player.from("히스타"),
                Dealer.generate()
        );
        Card card = new Card(CardNumber.TWO, CardSymbol.CLOVER);
        GameBoard gameBoard = new GameBoard(participants);
        CardDeck cardDeck = gameBoard.getPlayingCard();
        List<Card> cards = cardDeck.getCards();
        cards.clear();
        cards.add(card);

        gameBoard.drawCardTo(targetParticipant);

        //when
        int actual = gameBoard.getTotalScoreOf(targetParticipant);

        //then
        Assertions.assertThat(actual).isEqualTo(2);
    }
}
