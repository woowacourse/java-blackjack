package blackjack.service;

import blackjack.domain.deck.Deck;
import blackjack.domain.deck.RandomCardStrategy;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.dto.GamerDto;
import blackjack.dto.request.NamesRequestDto;
import blackjack.dto.response.FinalResultResponseDto;
import blackjack.dto.response.RoundResultsResponseDto;
import blackjack.dto.response.StartingCardsResponseDto;

public class BlackjackService {

    private final Deck deck = Deck.generateFrom(new RandomCardStrategy());
    private final Dealer dealer = new Dealer();
    private Players players;

    public void setPlayer(NamesRequestDto requestDto) {
        players = Players.of(requestDto.names().stream()
            .map(Player::new)
            .toList());
    }

    public StartingCardsResponseDto drawStartingCards() {
        dealer.initialize(deck);
        players.initialize(deck);
        return StartingCardsResponseDto.of(dealer, players);
    }

    public boolean hasMorePlayer() {
        return players.hasNext();
    }

    public String nextPlayerName() {
        return players.next().getName();
    }

    public void drawCardFor(String playerName) {
        Player player = players.findByName(playerName);
        player.drawCard(deck);
    }

    public GamerDto getPlayerCards(String playerName) {
        Player player = players.findByName(playerName);
        return GamerDto.from(player);
    }

    public boolean canReceiveAdditionalCards(String playerName) {
        Player player = players.findByName(playerName);
        return player.canReceiveAdditionalCards();
    }

    public boolean dealerCanReceiveAdditionalCards() {
        return dealer.canReceiveAdditionalCards();
    }

    public void drawCardForDealer() {
        dealer.drawCard(deck);
    }

    public RoundResultsResponseDto getRoundResults() {
        return RoundResultsResponseDto.of(dealer, players);
    }

    public FinalResultResponseDto getFinalResult() {
        return FinalResultResponseDto.of(dealer, players);
    }
}
