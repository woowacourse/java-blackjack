package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import blackjack.domain.Judgement;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        players = new ArrayList<>(players);
        validatePlayers(players);
        this.players = players;
    }

    private void validatePlayers(List<Player> players) {
        validateNull(players);
        validateSize(players);
        validateDuplicatedName(players);
    }

    private void validateNull(List<Player> players) {
        Objects.requireNonNull(players, "[ERROR] Players 를 null 로 생성할 수 없습니다.");
    }

    private void validateSize(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] Players 생성시 최소 한 명의 Player 가 필요합니다.");
        }
    }

    private void validateDuplicatedName(List<Player> players) {
        int nameCount = (int)players.stream()
            .map(Player::getName)
            .distinct()
            .count();
        if (nameCount < players.size()) {
            throw new IllegalArgumentException("[ERROR] Player 이름은 중복될 수 없습니다.");
        }
    }

    public Map<String, Judgement> calculateJudgmentResult(Dealer dealer) {
        return players.stream()
            .collect(Collectors.toMap(Participant::getName, player -> judge(dealer, player)));
    }

    private Judgement judge(Dealer dealer, Player player) {
        if (player.isBust()) {
            return Judgement.LOSE;
        }
        if (dealer.isBust()) {
            return Judgement.WIN;
        }
        if (dealer.isBlackJack() || player.isBlackJack()) {
            return judgeWithBlackJack(dealer, player);
        }
        return judgeWithScore(dealer, player);
    }

    private Judgement judgeWithBlackJack(Dealer dealer, Player player) {
        if (dealer.isBlackJack() && player.isBlackJack()) {
            return Judgement.DRAW;
        }
        if (dealer.isBlackJack()) {
            return Judgement.LOSE;
        }
        return Judgement.WIN;
    }

    private Judgement judgeWithScore(Dealer dealer, Player player) {
        int dealerScore = dealer.calculateScore();
        int playerScore = player.calculateScore();
        if (dealerScore == playerScore) {
            return Judgement.DRAW;
        }
        if (dealerScore > playerScore) {
            return Judgement.LOSE;

        }
        return Judgement.WIN;
    }

    public List<Player> getValues() {
        return List.copyOf(players);
    }
}
