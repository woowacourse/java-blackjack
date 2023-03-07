package domain.user;

import domain.PlayerResultRepository;
import domain.Result;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<String> nameValues) {
        this.players = nameValues.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void calculateAllResults(PlayerResultRepository playerResultRepository, Dealer dealer) {
        this.players.forEach(player -> playerResultRepository.save(player, calculateResult(player, dealer)));
    }

    private Result calculateResult(Player player, Dealer dealer) {
        if (player.isBust()) {
            return Result.LOSE;
        }
        if (dealer.isBust() || player.calculateScore() > dealer.calculateScore()) {
            return Result.WIN;
        }
        if (player.calculateScore() == dealer.calculateScore()) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }
}
