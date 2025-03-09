package domain;

import domain.card.Card;
import domain.card.GameCardDeck;
import domain.card.ParticipantCardDeck;
import domain.card.CardNumber;
import domain.card.CardSymbol;
import domain.participant.BattleResult;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

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

        Map<Participant, ParticipantCardDeck> cardDeckOfParticipant = gameBoard.getCardDeckOfParticipant();

        for(Map.Entry<Participant, ParticipantCardDeck> entry : cardDeckOfParticipant.entrySet()) {
            ParticipantCardDeck ownedParticipantCardDeck = entry.getValue();
            Assertions.assertThat(ownedParticipantCardDeck.getCards().size()).isEqualTo(2);
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
        ParticipantCardDeck ownedParticipantCardDeck = gameBoard.getCardDeckOfParticipant().get(targetParticipant);
        Assertions.assertThat(ownedParticipantCardDeck.getCards().size()).isEqualTo(1);
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
        ParticipantCardDeck participantCardDeck = gameBoard.getCardDeckOf(targetParticipant);

        //then
        Assertions.assertThat(participantCardDeck.getCards().size()).isEqualTo(1);
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
        GameCardDeck gameCardDeck = gameBoard.getPlayingCard();
        List<Card> cards = gameCardDeck.getCards();
        cards.clear();
        cards.add(card);

        gameBoard.drawCardTo(targetParticipant);

        //when
        int actual = gameBoard.getScoreOf(targetParticipant);

        //then
        Assertions.assertThat(actual).isEqualTo(2);
    }

    @ParameterizedTest
    @CsvSource(value = {"SIX, TEN, true", "SEVEN, TEN, false"})
    void 딜러_점수_스테이_확인(CardNumber cardNumber1, CardNumber cardNumber2, boolean expectedResult) {
        //given
        Participant targetParticipant = Dealer.generate();
        List<Participant> participants = List.of(
                targetParticipant,
                Player.from("히스타"),
                Player.from("우가")
        );
        Card card1 = new Card(cardNumber1, CardSymbol.CLOVER);
        Card card2 = new Card(cardNumber2, CardSymbol.HEART);
        GameBoard gameBoard = new GameBoard(participants);
        GameCardDeck gameCardDeck = gameBoard.getPlayingCard();
        List<Card> cards = gameCardDeck.getCards();
        cards.clear();
        cards.add(card1);
        cards.add(card2);

        gameBoard.drawCardTo(targetParticipant);
        gameBoard.drawCardTo(targetParticipant);

        //when
        boolean actual = gameBoard.ableToDraw(targetParticipant);

        //then
        Assertions.assertThat(actual).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource(value = {"TWO, EIGHT, TEN, true", "FOUR, SEVEN, TEN, false"})
    void 참가자_점수_스테이_확인(CardNumber cardNumber1, CardNumber cardNumber2, CardNumber cardNumber3, boolean expectedResult) {
        //given
        Participant targetParticipant = Player.from("우가");
        List<Participant> participants = List.of(
                targetParticipant,
                Player.from("히스타"),
                Dealer.generate()
        );
        Card card1 = new Card(cardNumber1, CardSymbol.CLOVER);
        Card card2 = new Card(cardNumber2, CardSymbol.HEART);
        Card card3 = new Card(cardNumber3, CardSymbol.DIAMOND);
        GameBoard gameBoard = new GameBoard(participants);
        GameCardDeck gameCardDeck = gameBoard.getPlayingCard();
        List<Card> cards = gameCardDeck.getCards();
        cards.clear();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        gameBoard.drawCardTo(targetParticipant);
        gameBoard.drawCardTo(targetParticipant);
        gameBoard.drawCardTo(targetParticipant);

        //when
        boolean actual = gameBoard.ableToDraw(targetParticipant);

        //then
        Assertions.assertThat(actual).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource(value = {"ACE, TEN, TEN, 21", "ACE, EIGHT, TWO, 21", "ACE, ACE, NINE, 21", "ACE, ACE, TEN, 12", "ACE, ACE, ACE, 13"})
    void 에이스_점수_계산_확인(CardNumber cardNumber, CardNumber cardNumber2, CardNumber cardNumber3, int expectedResult) {
        //given
        Participant targetParticipant = Player.from("우가");
        List<Participant> participants = List.of(
                targetParticipant,
                Player.from("히스타"),
                Dealer.generate()
        );
        Card card = new Card(cardNumber, CardSymbol.CLOVER);
        Card card2 = new Card(cardNumber2, CardSymbol.DIAMOND);
        Card card3 = new Card(cardNumber3, CardSymbol.HEART);

        GameBoard gameBoard = new GameBoard(participants);

        GameCardDeck gameCardDeck = gameBoard.getPlayingCard();
        List<Card> cards = gameCardDeck.getCards();
        cards.clear();
        cards.add(card);
        cards.add(card2);
        cards.add(card3);


        gameBoard.drawCardTo(targetParticipant);
        gameBoard.drawCardTo(targetParticipant);
        gameBoard.drawCardTo(targetParticipant);

        //when
        int actual = gameBoard.getScoreOf(targetParticipant);

        //then
        Assertions.assertThat(actual).isEqualTo(expectedResult);
    }

    @Test
    void 승패_계산_확인() {
        //given
        Participant participant1 = Player.from("우가");
        Participant participant2 = Player.from("히스타");
        Participant dealer = Dealer.generate();

        List<Participant> participants = List.of(
                participant1,
                participant2,
                dealer
        );

        GameBoard gameBoard = new GameBoard(participants);
        GameCardDeck gameCardDeck = gameBoard.getPlayingCard();

        List<Card> cards = gameCardDeck.getCards();
        cards.clear();

        cards.add(new Card(CardNumber.ACE, CardSymbol.HEART));
        cards.add(new Card(CardNumber.TWO, CardSymbol.DIAMOND));
        cards.add(new Card(CardNumber.THREE, CardSymbol.SPADE));
        cards.add(new Card(CardNumber.FOUR, CardSymbol.CLOVER));
        cards.add(new Card(CardNumber.FIVE, CardSymbol.HEART));
        cards.add(new Card(CardNumber.SIX, CardSymbol.DIAMOND));

        gameBoard.drawCardTo(participant1);
        gameBoard.drawCardTo(participant1);
        gameBoard.drawCardTo(participant2);
        gameBoard.drawCardTo(participant2);
        gameBoard.drawCardTo(dealer);
        gameBoard.drawCardTo(dealer);

        //when
        gameBoard.calculateBattleResult();

        //then
        assertAll(
                () -> Assertions.assertThat(participant1.getBattleResult().containsKey(BattleResult.WIN)).isTrue(),
                () -> Assertions.assertThat(participant2.getBattleResult().containsKey(BattleResult.LOSE)).isTrue(),
                () -> Assertions.assertThat(dealer.getBattleResult().containsKey(BattleResult.WIN)).isTrue(),
                () -> Assertions.assertThat(dealer.getBattleResult().containsKey(BattleResult.LOSE)).isTrue()
                );
    }

    @Test
    void 무승부_계산_확인() {
        //given
        Participant participant1 = Player.from("우가");
        Participant participant2 = Player.from("히스타");
        Participant dealer = Dealer.generate();

        List<Participant> participants = List.of(
                participant1,
                participant2,
                dealer
        );

        GameBoard gameBoard = new GameBoard(participants);
        GameCardDeck gameCardDeck = gameBoard.getPlayingCard();

        List<Card> cards = gameCardDeck.getCards();
        cards.clear();

        cards.add(new Card(CardNumber.ACE, CardSymbol.HEART));
        cards.add(new Card(CardNumber.TWO, CardSymbol.DIAMOND));
        cards.add(new Card(CardNumber.THREE, CardSymbol.SPADE));
        cards.add(new Card(CardNumber.TEN, CardSymbol.CLOVER));
        cards.add(new Card(CardNumber.SEVEN, CardSymbol.HEART));
        cards.add(new Card(CardNumber.SIX, CardSymbol.DIAMOND));

        gameBoard.drawCardTo(participant1);
        gameBoard.drawCardTo(participant1);
        gameBoard.drawCardTo(participant2);
        gameBoard.drawCardTo(participant2);
        gameBoard.drawCardTo(dealer);
        gameBoard.drawCardTo(dealer);

        //when
        gameBoard.calculateBattleResult();

        //then
        assertAll(
                () -> Assertions.assertThat(participant1.getBattleResult().containsKey(BattleResult.DRAW)).isTrue(),
                () -> Assertions.assertThat(participant1.getBattleResult().containsKey(BattleResult.WIN)).isFalse(),
                () -> Assertions.assertThat(participant1.getBattleResult().containsKey(BattleResult.LOSE)).isFalse(),
                
                () -> Assertions.assertThat(participant2.getBattleResult().containsKey(BattleResult.DRAW)).isTrue(),
                () -> Assertions.assertThat(participant2.getBattleResult().containsKey(BattleResult.WIN)).isFalse(),
                () -> Assertions.assertThat(participant2.getBattleResult().containsKey(BattleResult.LOSE)).isFalse(),

                () -> Assertions.assertThat(dealer.getBattleResult().containsKey(BattleResult.DRAW)).isTrue(),
                () -> Assertions.assertThat(dealer.getBattleResult().containsKey(BattleResult.WIN)).isFalse(),
                () -> Assertions.assertThat(dealer.getBattleResult().containsKey(BattleResult.LOSE)).isFalse()
        );
    }

    @Test
    void 모두가_버스트_무승부_발생_확인() {
        //given
        Participant participant1 = Player.from("우가");
        Participant participant2 = Player.from("히스타");
        Participant dealer = Dealer.generate();

        List<Participant> participants = List.of(
                participant1,
                participant2,
                dealer
        );

        GameBoard gameBoard = new GameBoard(participants);
        GameCardDeck gameCardDeck = gameBoard.getPlayingCard();

        List<Card> cards = gameCardDeck.getCards();
        cards.clear();

        cards.add(new Card(CardNumber.TEN, CardSymbol.HEART));
        cards.add(new Card(CardNumber.TEN, CardSymbol.DIAMOND));
        cards.add(new Card(CardNumber.TWO, CardSymbol.SPADE));

        cards.add(new Card(CardNumber.TEN, CardSymbol.HEART));
        cards.add(new Card(CardNumber.TEN, CardSymbol.DIAMOND));
        cards.add(new Card(CardNumber.TWO, CardSymbol.SPADE));

        cards.add(new Card(CardNumber.TEN, CardSymbol.HEART));
        cards.add(new Card(CardNumber.TEN, CardSymbol.DIAMOND));
        cards.add(new Card(CardNumber.TWO, CardSymbol.SPADE));

        gameBoard.drawCardTo(participant1);
        gameBoard.drawCardTo(participant1);
        gameBoard.drawCardTo(participant1);

        gameBoard.drawCardTo(participant2);
        gameBoard.drawCardTo(participant2);
        gameBoard.drawCardTo(participant2);

        gameBoard.drawCardTo(dealer);
        gameBoard.drawCardTo(dealer);
        gameBoard.drawCardTo(dealer);

        //when
        gameBoard.calculateBattleResult();

        //then
        assertAll(
                () -> Assertions.assertThat(participant1.getBattleResult().containsKey(BattleResult.DRAW)).isTrue(),
                () -> Assertions.assertThat(participant2.getBattleResult().containsKey(BattleResult.DRAW)).isTrue(),
                () -> Assertions.assertThat(dealer.getBattleResult().containsKey(BattleResult.DRAW)).isTrue()
        );
    }
}
