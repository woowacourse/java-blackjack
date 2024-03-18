package model.game;

import static model.card.CardNumber.JACK;
import static model.card.CardNumber.SEVEN;
import static model.card.CardNumber.SIX;
import static model.card.CardNumber.TEN;
import static model.card.CardNumber.TWO;
import static model.card.CardShape.CLOVER;
import static model.card.CardShape.HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.math.BigDecimal;
import java.util.List;
import model.betting.Bet;
import model.card.Card;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.Player;
import model.participant.Players;
import model.result.ParticipantCard;
import model.result.ParticipantCards;
import model.result.ParticipantProfit;
import model.result.ParticipantProfits;
import model.result.ParticipantScore;
import model.result.ParticipantScores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @DisplayName("게임 시작 시 딜러와 플레이어에게 카드 2장 지급")
    @Test
    void dealInitialCards() {
        Players players = Players.from(List.of("조조", "릴리"));
        Dealer dealer = new Dealer();
        BlackjackGame blackjackGame = new BlackjackGame();
        ParticipantCards participantCards = blackjackGame.dealInitialCards(dealer, players);

        assertAll(
            () -> assertThat(participantCards.playerNames()).contains("조조", "릴리"),
            () -> assertThat(participantCards.dealerName()).isEqualTo("딜러"),
            () -> assertThat(participantCards.getDealerCard().getCards()).hasSize(1),
            () -> participantCards.getPlayerCards()
                .forEach(participantCard -> assertThat(participantCard.getCards()).hasSize(2))
        );
    }

    @DisplayName("플레이어에게 추가 카드 지급")
    @Test
    void dealPlayerCard() {
        Player player = Player.of("jojo", List.of(new Card(JACK, HEART), new Card(TWO, CLOVER)));
        BlackjackGame blackjackGame = new BlackjackGame();
        ParticipantCard playerCard = blackjackGame.dealCardTo(player);
        assertAll(
            () -> assertThat(playerCard.getName()).isEqualTo("jojo"),
            () -> assertThat(playerCard.getCards()).hasSize(3)
        );
    }

    @DisplayName("최초 딜러의 카드 합이 16점 이하이면 카드 1장을 지급하고 true 반환")
    @Test
    void dealOneCardAndReturnTrue() {
        Dealer dealer = new Dealer(List.of(new Card(TEN, HEART), new Card(SIX, CLOVER)));
        BlackjackGame blackjackGame = new BlackjackGame();

        assertAll(
            () -> assertThat(blackjackGame.dealCardTo(dealer)).isTrue(),
            () -> assertThat(dealer.cardSize()).isEqualTo(3)
        );
    }

    @DisplayName("최초 딜러의 카드 합이 16점 초과면 카드 지급 안 하고 false 반환")
    @Test
    void doNotDealCardAndReturnFalse() {
        Dealer dealer = new Dealer(List.of(new Card(TEN, HEART), new Card(SEVEN, CLOVER)));
        BlackjackGame blackjackGame = new BlackjackGame();

        assertAll(
            () -> assertThat(blackjackGame.dealCardTo(dealer)).isFalse(),
            () -> assertThat(dealer.cardSize()).isEqualTo(2)
        );
    }

    @DisplayName("게임 종료 시 스코어 계산 후 반환")
    @Test
    void finishAndReturnParticipantScores() {
        Players players = Players.from(List.of("조조", "릴리"));
        Dealer dealer = new Dealer();
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.dealInitialCards(dealer, players);

        List<Integer> scores = players.getPlayers()
            .stream()
            .map(Participant::score)
            .toList();

        ParticipantScores participantScores = blackjackGame.finish(dealer, players);
        int i = 0;
        for (ParticipantScore playerScore : participantScores.getPlayerScores()) {
            assertThat(playerScore.getScore()).isEqualTo(scores.get(i++));
        }
    }

    @DisplayName("플레이어의 승무패 결과에 따라 딜러의 총 수익 계산")
    @Test
    void calculateProfit() {
        Players players = Players.from(List.of("조조"));
        prepareBets(players);
        Dealer dealer = new Dealer();
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.dealInitialCards(dealer, players);

        ParticipantProfits participantProfits = blackjackGame.calculateProfit(dealer, players);
        BigDecimal dealerProfit = participantProfits.getPlayerProfits()
            .stream()
            .map(ParticipantProfit::getProfit)
            .reduce(BigDecimal.ZERO, BigDecimal::subtract);

        assertThat(dealerProfit).isEqualTo(participantProfits.getDealerProfit().getProfit());
    }

    private void prepareBets(Players players) {
        players.getPlayers()
            .get(0)
            .setBet(new Bet("10000"));
    }
}
