package blackjack;

import blackjack.domain.deck.Deck;
import blackjack.domain.deck.ShuffleStrategy;
import blackjack.domain.money.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.GameResults;
import blackjack.dto.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private final Deck deck;
    private final Players players;
    private final Dealer dealer;
    private final Map<Player, Money> wagers = new LinkedHashMap<>();

    public BlackjackGame(List<String> playerNames, ShuffleStrategy shuffleStrategy) {
        this.deck = new Deck();
        this.deck.shuffle(shuffleStrategy);
        this.players = new Players(playerNames.stream().map(Player::new).toList());
        this.dealer = new Dealer();
    }

    public List<String> getPlayerNames() {
        return players.players().stream()
                .map(Player::getName)
                .toList();
    }

    public void placeWager(String playerName, int amount) {
        Player player = findPlayer(playerName);
        wagers.put(player, new Money(amount));
    }

    public InitialDealDto dealInitialCards() {
        for (int i = 0; i < 2; i++) {
            players.players().forEach(p -> p.receiveCard(deck.draw()));
            dealer.receiveCard(deck.draw());
        }
        return new InitialDealDto(players, dealer);
    }

    public boolean canPlayerHit(String playerName) {
        return findPlayer(playerName).canReceiveCard();
    }

    public PlayerCardsDto hitPlayer(String playerName) {
        Player player = findPlayer(playerName);
        player.receiveCard(deck.draw());
        return new PlayerCardsDto(player);
    }

    public PlayerCardsDto getPlayerCards(String playerName) {
        return new PlayerCardsDto(findPlayer(playerName));
    }

    public boolean canDealerHit() {
        return dealer.canReceiveCard();
    }

    public void hitDealer() {
        dealer.receiveCard(deck.draw());
    }

    public List<ParticipantFinalDto> getFinalCards() {
        List<Participant> all = new ArrayList<>();
        all.add(dealer);
        all.addAll(players.players());
        return all.stream().map(ParticipantFinalDto::new).toList();
    }

    public GameResultsDto resolveResults() {
        GameResults results = GameResults.create(players, dealer);
        return new GameResultsDto(results);
    }

    public ProfitsDto calculateProfits() {
        GameResults results = GameResults.create(players, dealer);
        Map<Participant, Money> profits = results.calculateProfits(wagers, dealer);
        return ProfitsDto.from(profits);
    }

    private Player findPlayer(String name) {
        return players.players().stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElseThrow();
    }
}
