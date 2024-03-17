package model.game;

import static model.card.CardNumber.JACK;
import static model.card.CardNumber.TWO;
import static model.card.CardShape.CLOVER;
import static model.card.CardShape.HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.betting.Bet;
import model.betting.PlayerBets;
import model.card.Card;
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
        BlackjackGame blackjackGame = new BlackjackGame();
        ParticipantCards participantCards = blackjackGame.dealInitialCards(players);

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
        ParticipantCard playerCard = blackjackGame.dealCard(player);
        assertAll(
            () -> assertThat(playerCard.getName()).isEqualTo("jojo"),
            () -> assertThat(playerCard.getCards()).hasSize(3)
        );
    }

    @DisplayName("최초 딜러의 카드 합이 16점 이하이면 카드 1장을 지급")
    @Test
    void dealerHitTurn() {
        Players players = Players.from(List.of("조조", "릴리"));
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.dealInitialCards(players);

        boolean isDealerHit = blackjackGame.dealerHitTurn();
        if (isDealerHit) {
            assertThat(blackjackGame.dealerCardSize()).isEqualTo(3);
        }
        if (!isDealerHit) {
            assertThat(blackjackGame.dealerCardSize()).isEqualTo(2);
        }
    }

    @DisplayName("게임 종료 시 스코어 계산 후 반환")
    @Test
    void finishAndReturnParticipantScores() {
        Players players = Players.from(List.of("조조", "릴리"));
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.dealInitialCards(players);

        List<Integer> scores = players.getPlayers()
            .stream()
            .map(Participant::score)
            .toList();

        ParticipantScores participantScores = blackjackGame.finish(players);
        int i = 0;
        for (ParticipantScore playerScore : participantScores.getPlayerScores()) {
            assertThat(playerScore.getScore()).isEqualTo(scores.get(i++));
        }
    }

    @DisplayName("플레이어의 승무패 결과에 따라 딜러의 총 수익 계산")
    @Test
    void calculateProfit() {
        Players players = Players.from(List.of("조조"));
        PlayerBets bets = prepareBets();
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.dealInitialCards(players);

        ParticipantProfits participantProfits = blackjackGame.calculateProfit(players, bets);
        int dealerScore = (-1) * participantProfits.getPlayerProfits()
            .stream()
            .mapToInt(ParticipantProfit::getProfit)
            .sum();

        assertThat(dealerScore).isEqualTo(participantProfits.getDealerProfit().getProfit());
    }

    private PlayerBets prepareBets() {
        Map<String, Bet> bets = new HashMap<>();
        bets.put("조조", new Bet(10000));
        return new PlayerBets(bets);
    }
}
