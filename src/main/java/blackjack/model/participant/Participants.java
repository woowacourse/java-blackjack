package blackjack.model.participant;

import blackjack.model.card.CardDeck;
import blackjack.model.result.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Participants {

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(Dealer dealer, List<Player> players) {
        validateDuplicatedPlayer(players);
        validatePlayerCount(players);
        this.dealer = dealer;
        this.players = new ArrayList<>(players);
    }

    private void validateDuplicatedPlayer(List<Player> players) {
        if (players.stream().distinct().count() != players.size()) {
            throw new IllegalArgumentException("이름은 중복될 수 없습니다.");
        }
    }

    private void validatePlayerCount(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("게임을 시작하려면 플레이어는 한명 이상이여야 합니다.");
        }
    }

    public void distributeTwoCardsToEach(CardDeck cardDeck) {
        dealer.play(cardDeck);
        for (Player player : players) {
            player.play(cardDeck);
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);
        return participants;
    }

    public boolean hasNextPlayer() {
        return players.stream()
                .anyMatch(player -> !player.isFinished());
    }

    public Player getNextPlayer() {
        return players.stream()
                .filter(player -> !player.isFinished())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("카드를 뽑을 수 있는 플레이어가 더 이상 없습니다."));
    }

    public void hitOrStandByPlayer(CardDeck cardDeck, Player player, boolean isHit) {
        if (isHit) {
            player.play(cardDeck);
            return;
        }
        player.changeToStand();
    }

    public int hitOrStandByDealer(CardDeck cardDeck) {
        int hitCount = 0;
        while (!dealer.isFinished()) {
            dealer.play(cardDeck);
            hitCount++;
        }
        return hitCount;
    }

    public Map<Player, Integer> getPlayerProfitResult() {
        Map<Player, Integer> playerProfitResult = new HashMap<>();
        for (Player player : players) {
            playerProfitResult.put(player, player.getProfit(dealer));
        }
        return playerProfitResult;
    }

}
