package blackjack.domain.gamer;

import blackjack.domain.Outcome;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import blackjack.domain.utils.CardDeck;
import blackjack.dto.PlayerResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(final List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public List<Participant> getUnmodifiableList() {
        return Collections.unmodifiableList(players);
    }

    public Map<String, Cards> cards() {
        final Map<String, Cards> nameAndCards = new LinkedHashMap<>();

        for (Player player : players) {
            nameAndCards.put(player.getName(), player.getCards());
        }

        return nameAndCards;
    }

    public List<String> names() {
        return players.stream()
            .map(Player::getName)
            .collect(Collectors.toList());
    }

    public void takeTwoCards(final CardDeck cardDeck) {
        for (Player player : players) {
            player.takeCard(cardDeck.pop());
            player.takeCard(cardDeck.pop());
        }
    }

    public List<PlayerResult> resultOfPlayers(final Score dealerScore) {
        List<PlayerResult> results = new ArrayList<>();

        for (Player player : players) {
            results
                .add(new PlayerResult(player, outcomeOfPlayer(dealerScore, player.finalScore())));
        }

        return results;
    }

    private Outcome outcomeOfPlayer(final Score dealer, final Score player) {
        if (dealer.isBlackjack()) {
            return blackjackCase(player);
        }
        if (dealer.isBurst()) {
            return Outcome.WIN;
        }
        return compareWith(dealer, player);
    }

    private Outcome blackjackCase(final Score playerScore) {
        if (playerScore.isBlackjack()) {
            return Outcome.DRAW;
        }
        return Outcome.LOSE;
    }

    private Outcome compareWith(final Score dealerScore, final Score playerScore) {
        return Outcome.getInstance(playerScore, dealerScore);
    }

}
