package blackjack.domain;

import blackjack.domain.card.CardFactory;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    private final CardFactory cardFactory;
    private final Dealer dealer;
    private final List<Player> players;

    public Game(CardFactory cardFactory, List<String> playerNames) {
        this.cardFactory = cardFactory;
        this.dealer = new Dealer();
        this.players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public int getParticipantCount() {
        return players.size() + 1;
    }

    public void init() {
        dealer.init(cardFactory);
        players.forEach(player -> player.init(cardFactory));
    }

    public int getRemainAmount() {
        return cardFactory.getRemainAmount();
    }
}
