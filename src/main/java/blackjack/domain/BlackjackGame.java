package blackjack.domain;

import blackjack.domain.card.Hand;
import blackjack.domain.card.deck.Deck;
import blackjack.domain.dto.ResponseCardResultDto;
import blackjack.domain.dto.ResponseInitHandDto;
import blackjack.domain.dto.ResponseOutcomeDto;
import blackjack.domain.outcome.Outcome;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.UserName;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {

    private Players players;
    private Dealer dealer;

    public Players start(List<String> names) {
        List<Player> players = names.stream()
                .map(UserName::new)
                .map(Player::new)
                .collect(Collectors.toList());
        this.players = new Players(players);
        this.dealer = new Dealer();

        return this.players;
    }

    public ResponseInitHandDto takeInitHand(Deck deck) {
        dealer.takeInitHand(deck);
        players.takeInitHand(deck);

        return new ResponseInitHandDto(dealer, players);
    }

    public Player takePlayerCard(Player player, Deck deck) {
        player.hit(deck.pick());
        return player;
    }

    public Dealer takeDealerCard(Deck deck) {
        dealer.hit(deck.pick());
        return dealer;
    }

    public ResponseCardResultDto calculateCardResult() {
        Map<String, Hand> results = new LinkedHashMap<>();
        results.put(dealer.getName().get(), dealer.getCards());
        for (Player player : players.get()) {
            results.put(player.getName().get(), player.getCards());
        }

        return new ResponseCardResultDto(results);
    }

    public ResponseOutcomeDto calculateOutcome() {
        Map<Player, Outcome> playerOutcomes = new LinkedHashMap<>();
        for (Player player : players.get()) {
            Outcome outcome = Outcome.determinePlayerOutcome(dealer, player);
            playerOutcomes.put(player, outcome);
        }
        Map<Outcome, Integer> dealerOutcome = Outcome.determineDealerOutcome(playerOutcomes);

        return new ResponseOutcomeDto(dealerOutcome, playerOutcomes);
    }

    public boolean isPlayerFinished(Player player) {
        return !player.isValidRange();
    }

    public boolean isDealerFinished() {
        return !dealer.isValidRange();
    }
}
