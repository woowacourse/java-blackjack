import ScoreResult.ScoreBoard;
import bank.GamblingStatement;
import bank.Money;
import card.GameCardDeck;
import participant.Dealer;
import participant.Participant;
import participant.Participants;
import participant.Player;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

public class GamblingStatementTest {

    @Test
    void 생성_확인() {
        //given
        Map<Participant, Money> originGamblingStatement = new HashMap<>();

        //when
        GamblingStatement gamblingStatement = new GamblingStatement(originGamblingStatement);

        //then
        Assertions.assertThat(gamblingStatement).isInstanceOf(GamblingStatement.class);
    }

    @DisplayName("승리시 수익 배팅금액 받기, 플레이어 1승, 플레이어 1승")
    @Test
    void 수익_계산1() {
        //given
        List<Participant> originParticipants = List.of(new Player("우가"), new Player("베루스"), new Dealer());
        Participants participants = new Participants(originParticipants);
        Participants onlyPlayer = participants.findPlayers();

        Participant dealer = participants.findDealer();
        Participant player1 = onlyPlayer.getParticipants().getFirst();
        Participant player2 = onlyPlayer.getParticipants().getLast();


        Map<Participant, Money> participantMoney = Map.of(
                participants.getParticipants().getFirst(), new Money("10000"),
                participants.getParticipants().get(1), new Money("10000"));
        GamblingStatement gamblingStatement = new GamblingStatement(participantMoney);

        GameCardDeck gameCardDeck = GameCardDeck.generateFullPlayingCard();
        participants.drawTwoCards(gameCardDeck);
        participants.drawTwoCards(gameCardDeck);

        //when
        GamblingStatement profitParticipantMoney = gamblingStatement.calculateProfit(new ScoreBoard(participants));

        Map<Participant, Money> profitMoney = profitParticipantMoney.getGamblingStatement();
        //then
        assertAll(
                () -> Assertions.assertThat(profitMoney.get(player1).getAmount()).isEqualTo(BigDecimal.valueOf(10000)),
                () -> Assertions.assertThat(profitMoney.get(player2).getAmount()).isEqualTo(BigDecimal.valueOf(10000))
        );
    }

    @DisplayName("무승부시 수익 0원, 플레이어 1무, 플레이어 1무")
    @Test
    void 수익_계산2() {
        //given
        List<Participant> originParticipants = List.of(new Player("우가"), new Player("베루스"), new Dealer());
        Participants participants = new Participants(originParticipants);
        Participants onlyPlayer = participants.findPlayers();

        Participant dealer = participants.findDealer();
        Participant player1 = onlyPlayer.getParticipants().getFirst();
        Participant player2 = onlyPlayer.getParticipants().getLast();


        Map<Participant, Money> participantMoney = Map.of(
                participants.getParticipants().getFirst(), new Money("10000"),
                participants.getParticipants().get(1), new Money("10000"));
        GamblingStatement gamblingStatement = new GamblingStatement(participantMoney);

        GameCardDeck gameCardDeck = GameCardDeck.generateFullPlayingCard();
        player1.drawCard(gameCardDeck, 1);
        player2.drawCard(gameCardDeck, 1);
        dealer.drawCard(gameCardDeck, 1);

        //when
        GamblingStatement profitParticipantMoney = gamblingStatement.calculateProfit(new ScoreBoard(participants));

        Map<Participant, Money> profitMoney = profitParticipantMoney.getGamblingStatement();

        //then
        assertAll(
                () -> Assertions.assertThat(profitMoney.get(player1).getAmount()).isEqualTo(BigDecimal.valueOf(0)),
                () -> Assertions.assertThat(profitMoney.get(player2).getAmount()).isEqualTo(BigDecimal.valueOf(0))
        );
    }

    @DisplayName("패배시 수익 -배팅원, 플레이어 1패, 플레이어 1패")
    @Test
    void 수익_계산3() {
        //given
        List<Participant> originParticipants = List.of(new Player("우가"), new Player("베루스"), new Dealer());
        Participants participants = new Participants(originParticipants);
        Participants onlyPlayer = participants.findPlayers();

        Participant player1 = onlyPlayer.getParticipants().getFirst();
        Participant player2 = onlyPlayer.getParticipants().getLast();
        Participant dealer = participants.findDealer();

        Map<Participant, Money> participantMoney = Map.of(
                player1, new Money("10000"),
                player2, new Money("10000"));
        GamblingStatement gamblingStatement = new GamblingStatement(participantMoney);

        GameCardDeck gameCardDeck = GameCardDeck.generateFullPlayingCard();
        participants.drawTwoCards(gameCardDeck);
        dealer.drawCard(gameCardDeck, 4);

        //when
        GamblingStatement profitParticipantMoney = gamblingStatement.calculateProfit(new ScoreBoard(participants));

        Map<Participant, Money> profitMoney = profitParticipantMoney.getGamblingStatement();

        //then
        assertAll(
                () -> Assertions.assertThat(profitMoney.get(player1).getAmount()).isEqualTo(BigDecimal.valueOf(-10000)),
                () -> Assertions.assertThat(profitMoney.get(player2).getAmount()).isEqualTo(BigDecimal.valueOf(-10000))
        );
    }

    @DisplayName("블랙잭으로 승리시 배팅금액의 1.5배, 플레이어1 1승, 플레이어2 1패, 딜러 1패")
    @Test
    void 수익_계산4() {
        //given
        List<Participant> originParticipants = List.of(new Player("우가"), new Player("베루스"), new Dealer());
        Participants participants = new Participants(originParticipants);
        Participants onlyPlayer = participants.findPlayers();

        Participant player1 = onlyPlayer.getParticipants().getFirst();
        Participant player2 = onlyPlayer.getParticipants().getLast();
        Participant dealer = participants.findDealer();

        Map<Participant, Money> participantMoney = Map.of(
                player1, new Money("10000"),
                player2, new Money("10000"));
        GamblingStatement gamblingStatement = new GamblingStatement(participantMoney);

        GameCardDeck gameCardDeck = GameCardDeck.generateFullPlayingCard();
        player1.drawCard(gameCardDeck,1);
        player2.drawCard(gameCardDeck, 35);
        player1.drawCard(gameCardDeck, 1);
        dealer.drawCard(gameCardDeck, 2);

        //when
        GamblingStatement profitParticipantMoney = gamblingStatement.calculateProfit(new ScoreBoard(participants));

        Map<Participant, Money> profitMoney = profitParticipantMoney.getGamblingStatement();

        assertAll(
                () -> Assertions.assertThat(profitMoney.get(player1).getAmount()).isEqualTo(BigDecimal.valueOf(15000)),
                () -> Assertions.assertThat(profitMoney.get(player2).getAmount()).isEqualTo(BigDecimal.valueOf(-10000))
        );
    }

    @DisplayName("플레이어가 블랙잭인데, 딜러도 블랙잭인 경우 무승부 처리-> 수익 0원")
    @Test
    void 수익_계산5() {
        //given
        List<Participant> originParticipants = List.of(new Player("우가"), new Player("베루스"), new Dealer());
        Participants participants = new Participants(originParticipants);
        Participants onlyPlayer = participants.findPlayers();

        Participant player1 = onlyPlayer.getParticipants().getFirst();
        Participant player2 = onlyPlayer.getParticipants().getLast();
        Participant dealer = participants.findDealer();

        Map<Participant, Money> participantMoney = Map.of(
                player1, new Money("10000"),
                player2, new Money("10000"));
        GamblingStatement gamblingStatement = new GamblingStatement(participantMoney);

        GameCardDeck gameCardDeck = GameCardDeck.generateFullPlayingCard();
        player1.drawCard(gameCardDeck,1);
        dealer.drawCard(gameCardDeck, 1);
        player2.drawCard(gameCardDeck, 34);
        player1.drawCard(gameCardDeck, 1);
        dealer.drawCard(gameCardDeck, 1);

        //when
        GamblingStatement profitParticipantMoney = gamblingStatement.calculateProfit(new ScoreBoard(participants));

        Map<Participant, Money> profitMoney = profitParticipantMoney.getGamblingStatement();

        assertAll(
                () -> Assertions.assertThat(profitMoney.get(player1).getAmount()).isEqualTo(BigDecimal.valueOf(0)),
                () -> Assertions.assertThat(profitMoney.get(player2).getAmount()).isEqualTo(BigDecimal.valueOf(-10000))
        );
    }

}
