package blackjack.player;

import blackjack.GameReport;
import blackjack.player.card.CardFactory;

import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public void initCards(CardFactory cardFactory) {
        for (int i = 0; i < 2; i++) {
            players.forEach(player -> player.addCard(cardFactory.drawCard()));
        }
    }

    public List<GameReport> getReports() {
        Player dealer = findDealer();
        List<Player> gamblers = findGamblers();

        return gamblers.stream()
                .map(dealer::getReport)
                .collect(Collectors.toList());
    }

    private List<Player> findGamblers() {
        return players.stream()
                .filter(Player::isGambler)
                .collect(Collectors.toList());
    }

    private Player findDealer() {
        return players.stream()
                .filter(Player::isDealer)
                .findFirst()
                .orElseThrow(AssertionError::new);
    }
}
