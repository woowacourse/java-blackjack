package blackjack.domain.round;

import blackjack.domain.deck.Deck;
import blackjack.domain.deck.RandomCardStrategy;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.dto.response.RoundResultsResponseDto;
import blackjack.dto.response.StartingCardsResponseDto;

import java.util.Collections;
import java.util.List;

public class Round {

    private final Dealer dealer;
    private final List<Player> players;
    private Deck deck;

    public Round(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public StartingCardsResponseDto initialize() {
        deck = Deck.generateFrom(new RandomCardStrategy());
        dealer.initialize(deck);
        for (Player player : players) {
            player.initialize(deck);
        }
        return StartingCardsResponseDto.of(dealer, players);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public void drawPlayerCard(String playerName) {
        Player targetPlayer = players.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 이름의 플레이어가 없습니다."));
        targetPlayer.drawCard(deck);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void drawDealerCard() {
        dealer.drawCard(deck);
    }

    public RoundResultsResponseDto getRoundResults() {
        return RoundResultsResponseDto.of(dealer, players);
    }
}
