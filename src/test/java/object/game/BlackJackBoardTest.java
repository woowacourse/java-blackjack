package object.game;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import object.card.Card;
import object.card.CardDeck;
import object.card.CardNumber;
import object.card.CardSymbol;
import object.participant.Dealer;
import object.participant.Participant;
import object.participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BlackJackBoardTest {

    @Test
    void 게임판_테스트() {
        //given
        Player player1 = Player.from("우가");
        Player player2 = Player.from("히스타");

        //when
        BlackJackBoard blackJackBoard = new BlackJackBoard(List.of(player1, player2));

        //then
        Assertions.assertThat(blackJackBoard).isInstanceOf(BlackJackBoard.class);
    }

    @Test
    void 게임판_정적_팩토리_메서드_테스트() {
        // given
        Dealer dealer = Dealer.generate();
        List<Player> players = List.of(Player.from("우가"), Player.from("히스타"));

        // when
        BlackJackBoard blackJackBoard = BlackJackBoard.generateOf(dealer, players);

        // then
        Assertions.assertThat(blackJackBoard).isInstanceOf(BlackJackBoard.class);
    }

    @Test
    void 게임에_참여중인_플레이어_가져오기_테스트() {
        // given
        Dealer dealer = Dealer.generate();
        List<Player> players = List.of(Player.from("우가"), Player.from("히스타"));
        BlackJackBoard blackJackBoard = BlackJackBoard.generateOf(dealer, players);

        // when
        List<Participant> gameBoardPlayers = blackJackBoard.getPlayers();

        // then
        Assertions.assertThat(gameBoardPlayers).containsExactlyElementsOf(players);
    }

    @Test
    void 게임에_참여중인_딜러_가져오기_테스트() {
        // given
        Dealer dealer = Dealer.generate();
        List<Player> players = List.of(Player.from("우가"), Player.from("히스타"));
        BlackJackBoard blackJackBoard = BlackJackBoard.generateOf(dealer, players);

        // when
        Dealer gameBoardDealer = (Dealer) blackJackBoard.getDealer();

        // then
        Assertions.assertThat(gameBoardDealer).isEqualTo(dealer);
    }

    @Test
    void 게임에_참여중인_참가자_모두_가져오기_테스트() {
        // given
        Dealer dealer = Dealer.generate();
        List<Player> players = List.of(Player.from("우가"), Player.from("히스타"));
        BlackJackBoard blackJackBoard = BlackJackBoard.generateOf(dealer, players);

        List<Participant> expectedParticipants = new ArrayList<>(players);
        expectedParticipants.add(dealer);

        // when
        List<Participant> participants = blackJackBoard.getParticipants();

        // then
        Assertions.assertThat(participants).containsExactlyInAnyOrderElementsOf(expectedParticipants);
    }

    @Test
    void 카드를_모두에게_2장씩_나눠준다() {
        //given
        List<Participant> participants = List.of(
                Player.from("우가"),
                Player.from("히스타"),
                Dealer.generate()
        );
        BlackJackBoard blackJackBoard = new BlackJackBoard(participants);

        //when
        blackJackBoard.drawTwoCards();

        Map<Participant, CardDeck> cardDeckOfParticipant = blackJackBoard.getCardDeckOfParticipant();

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
        BlackJackBoard blackJackBoard = new BlackJackBoard(participants);

        //when
        blackJackBoard.drawTwoCards();

        Assertions.assertThat(blackJackBoard.getPlayingCard().getCards().size()).isEqualTo(46);
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
        BlackJackBoard blackJackBoard = new BlackJackBoard(participants);

        //when
        blackJackBoard.drawCardTo(targetParticipant);

        //then
        CardDeck ownedCardDeck = blackJackBoard.getCardDeckOfParticipant().get(targetParticipant);
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
        BlackJackBoard blackJackBoard = new BlackJackBoard(participants);
        blackJackBoard.drawCardTo(targetParticipant);
        //when
        CardDeck cardDeck = blackJackBoard.getCardDeckOf(targetParticipant);

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
        BlackJackBoard blackJackBoard = new BlackJackBoard(participants);
        CardDeck cardDeck = blackJackBoard.getPlayingCard();
        List<Card> cards = cardDeck.getCards();
        cards.clear();
        cards.add(card);

        blackJackBoard.drawCardTo(targetParticipant);

        //when
        int actual = blackJackBoard.getScoreOf(targetParticipant).getScore();

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
        BlackJackBoard blackJackBoard = new BlackJackBoard(participants);
        CardDeck cardDeck = blackJackBoard.getPlayingCard();
        List<Card> cards = cardDeck.getCards();
        cards.clear();
        cards.add(card1);
        cards.add(card2);

        blackJackBoard.drawCardTo(targetParticipant);
        blackJackBoard.drawCardTo(targetParticipant);

        //when
        boolean actual = blackJackBoard.ableToDraw(targetParticipant);

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
        BlackJackBoard blackJackBoard = new BlackJackBoard(participants);
        CardDeck cardDeck = blackJackBoard.getPlayingCard();
        List<Card> cards = cardDeck.getCards();
        cards.clear();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        blackJackBoard.drawCardTo(targetParticipant);
        blackJackBoard.drawCardTo(targetParticipant);
        blackJackBoard.drawCardTo(targetParticipant);

        //when
        boolean actual = blackJackBoard.ableToDraw(targetParticipant);

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

        BlackJackBoard blackJackBoard = new BlackJackBoard(participants);

        CardDeck cardDeck = blackJackBoard.getPlayingCard();
        List<Card> cards = cardDeck.getCards();
        cards.clear();
        cards.add(card);
        cards.add(card2);
        cards.add(card3);


        blackJackBoard.drawCardTo(targetParticipant);
        blackJackBoard.drawCardTo(targetParticipant);
        blackJackBoard.drawCardTo(targetParticipant);

        //when
        int actual = blackJackBoard.getScoreOf(targetParticipant).getScore();

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

        BlackJackBoard blackJackBoard = new BlackJackBoard(participants);
        CardDeck cardDeck = blackJackBoard.getPlayingCard();

        List<Card> cards = cardDeck.getCards();
        cards.clear();

        cards.add(new Card(CardNumber.ACE, CardSymbol.HEART));
        cards.add(new Card(CardNumber.TWO, CardSymbol.DIAMOND));
        cards.add(new Card(CardNumber.THREE, CardSymbol.SPADE));
        cards.add(new Card(CardNumber.FOUR, CardSymbol.CLOVER));
        cards.add(new Card(CardNumber.FIVE, CardSymbol.HEART));
        cards.add(new Card(CardNumber.SIX, CardSymbol.DIAMOND));

        blackJackBoard.drawCardTo(participant1);
        blackJackBoard.drawCardTo(participant1);
        blackJackBoard.drawCardTo(participant2);
        blackJackBoard.drawCardTo(participant2);
        blackJackBoard.drawCardTo(dealer);
        blackJackBoard.drawCardTo(dealer);

        //when
        blackJackBoard.calculateBattleResult();

        //then
        assertAll(
                () -> Assertions.assertThat(participant1.getGameRecord().containsKey(GameResult.WIN)).isTrue(),
                () -> Assertions.assertThat(participant1.getGameRecord().containsKey(GameResult.LOSE)).isFalse(),
                () -> Assertions.assertThat(participant2.getGameRecord().containsKey(GameResult.LOSE)).isTrue(),
                () -> Assertions.assertThat(participant2.getGameRecord().containsKey(GameResult.WIN)).isFalse(),
                () -> Assertions.assertThat(dealer.getGameRecord().containsKey(GameResult.WIN)).isTrue(),
                () -> Assertions.assertThat(dealer.getGameRecord().containsKey(GameResult.LOSE)).isTrue(),
                () -> Assertions.assertThat(dealer.getGameRecord().containsKey(GameResult.DRAW)).isFalse()
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

        BlackJackBoard blackJackBoard = new BlackJackBoard(participants);
        CardDeck cardDeck = blackJackBoard.getPlayingCard();

        List<Card> cards = cardDeck.getCards();
        cards.clear();

        cards.add(new Card(CardNumber.ACE, CardSymbol.HEART));
        cards.add(new Card(CardNumber.TWO, CardSymbol.DIAMOND));
        cards.add(new Card(CardNumber.THREE, CardSymbol.SPADE));
        cards.add(new Card(CardNumber.TEN, CardSymbol.CLOVER));
        cards.add(new Card(CardNumber.SEVEN, CardSymbol.HEART));
        cards.add(new Card(CardNumber.SIX, CardSymbol.DIAMOND));

        blackJackBoard.drawCardTo(participant1);
        blackJackBoard.drawCardTo(participant1);
        blackJackBoard.drawCardTo(participant2);
        blackJackBoard.drawCardTo(participant2);
        blackJackBoard.drawCardTo(dealer);
        blackJackBoard.drawCardTo(dealer);

        //when
        blackJackBoard.calculateBattleResult();

        //then
        assertAll(
                () -> Assertions.assertThat(participant1.getGameRecord().containsKey(GameResult.DRAW)).isTrue(),
                () -> Assertions.assertThat(participant1.getGameRecord().containsKey(GameResult.WIN)).isFalse(),
                () -> Assertions.assertThat(participant1.getGameRecord().containsKey(GameResult.LOSE)).isFalse(),
                
                () -> Assertions.assertThat(participant2.getGameRecord().containsKey(GameResult.DRAW)).isTrue(),
                () -> Assertions.assertThat(participant2.getGameRecord().containsKey(GameResult.WIN)).isFalse(),
                () -> Assertions.assertThat(participant2.getGameRecord().containsKey(GameResult.LOSE)).isFalse(),

                () -> Assertions.assertThat(dealer.getGameRecord().containsKey(GameResult.DRAW)).isTrue(),
                () -> Assertions.assertThat(dealer.getGameRecord().containsKey(GameResult.WIN)).isFalse(),
                () -> Assertions.assertThat(dealer.getGameRecord().containsKey(GameResult.LOSE)).isFalse()
        );
    }

    @Test
    void 모두가_버스트일때_플레이어_패배_확인() {
        //given
        Participant participant1 = Player.from("우가");
        Participant participant2 = Player.from("히스타");
        Participant dealer = Dealer.generate();

        List<Participant> participants = List.of(
                participant1,
                participant2,
                dealer
        );

        BlackJackBoard blackJackBoard = new BlackJackBoard(participants);
        CardDeck cardDeck = blackJackBoard.getPlayingCard();

        List<Card> cards = cardDeck.getCards();
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

        blackJackBoard.drawCardTo(participant1);
        blackJackBoard.drawCardTo(participant1);
        blackJackBoard.drawCardTo(participant1);

        blackJackBoard.drawCardTo(participant2);
        blackJackBoard.drawCardTo(participant2);
        blackJackBoard.drawCardTo(participant2);

        blackJackBoard.drawCardTo(dealer);
        blackJackBoard.drawCardTo(dealer);
        blackJackBoard.drawCardTo(dealer);

        //when
        blackJackBoard.calculateBattleResult();

        //then
        assertAll(
                () -> Assertions.assertThat(participant1.getGameRecord().containsKey(GameResult.LOSE)).isTrue(),
                () -> Assertions.assertThat(participant2.getGameRecord().containsKey(GameResult.LOSE)).isTrue(),
                () -> Assertions.assertThat(dealer.getGameRecord().containsKey(GameResult.WIN)).isTrue()
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1000, 1000, BLACKJACK_WIN, WIN, -2500",
            "1000, 1000, WIN, WIN, -2000",
            "1000, 1000, LOSE, LOSE, 2000",
            "1000, 1000, DRAW, DRAW, 0",
    })
    void 딜러_수익_계산_테스트(
            int p1BetMoney, int p2BetMoney,
            GameResult p1Result, GameResult p2Result,
            int expectedProfit
    ) {
        // given
        Participant participant1 = Player.from("우가");
        Participant participant2 = Player.from("히스타");
        Participant dealer = Dealer.generate();

        List<Participant> participants = List.of(
                participant1,
                participant2,
                dealer
        );

        participant1.bet(p1BetMoney);
        participant2.bet(p2BetMoney);
        participant1.applyGameRecord(p1Result);
        participant2.applyGameRecord(p2Result);

        BlackJackBoard blackJackBoard = new BlackJackBoard(participants);

        // when
        blackJackBoard.calculateDealerProfit();

        // then
        int actual = dealer.getProfit();
        Assertions.assertThat(actual).isEqualTo(expectedProfit);
    }
}
