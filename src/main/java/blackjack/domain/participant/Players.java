package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.game.PlayerResult;
import blackjack.domain.game.Referee;
import blackjack.domain.game.result.GameResult;
import blackjack.domain.participant.rule.DealerRule;
import blackjack.domain.participant.rule.PlayerRule;
import java.util.List;

public class Players {

    private static final int MAX_PLAYERS_SIZE = 4;

    private final Dealer dealer;
    private final List<Player> players;

    private Players(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        validateSize(players);
        validateDistinct(players);
        this.players = players;
    }

    public static Players from(List<String> names) {
        List<Player> players = names.stream()
                .map(Player::from)
                .toList();
        return new Players(new Dealer(), players);
    }

    private void validateSize(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("최소 한 명의 플레이어가 있어야 합니다.");
        }
        if (players.size() > MAX_PLAYERS_SIZE) {
            throw new IllegalArgumentException("최대 4명의 플레이어만 참여 가능합니다.");
        }
    }

    private void validateDistinct(List<Player> players) {
        if (isDuplicated(players)) {
            throw new IllegalArgumentException("중복된 이름을 사용할 수 없습니다.");
        }
    }

    private boolean isDuplicated(List<Player> players) {
        return players.size() != players.stream().distinct().count();
    }

    public void drawStartCards(Deck deck) {
        dealer.drawStartCards(deck);
        for (Player player : players) {
            player.drawStartCards(deck);
        }
    }

    public void play(Deck deck) {
        for (Player player : players) {
            player.play(deck);
        }
        dealer.play(deck);
    }

    public boolean isDealerDrawable() {
        return dealer.isDrawable();
    }

    public void drawDealerCard(Deck deck) {
        dealer.add(deck.draw());
    }

    public List<PlayerResult> getAllResult() {
        return players.stream()
                .map(this::getPlayerResult)
                .toList();
    }

    private PlayerResult getPlayerResult(Player player) {
        GameResult gameResult = Referee.getGameResult(dealer, player);
        return new PlayerResult(player.getName(), gameResult);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
