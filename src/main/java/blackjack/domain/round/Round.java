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
    private final Deck deck;

    public Round(List<String> playerNames) {
        this.deck = Deck.generateFrom(new RandomCardStrategy());
        this.dealer = new Dealer(deck.draw(), deck.draw());
        this.players = playerNames.stream()
                .map(name -> new Player(name, deck.draw(), deck.draw()))
                .toList();
    }

    public StartingCardsResponseDto getStartingCards() {
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
        targetPlayer.addCard(deck.draw());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void drawDealerCard() {
        dealer.addCard(deck.draw());
    }

    public RoundResultsResponseDto getRoundResults() {
        return RoundResultsResponseDto.of(dealer, players);
    }
}
