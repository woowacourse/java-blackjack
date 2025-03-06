package blackjack.service;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.deck.Deck;
import blackjack.domain.deck.RandomCardStrategy;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.dto.GamerDto;
import blackjack.dto.request.NamesRequestDto;
import blackjack.dto.response.FinalResultResponseDto;
import blackjack.dto.response.RoundResultsResponseDto;
import blackjack.dto.response.StartingCardsResponseDto;

public class BlackjackService {

    private final Deck deck = Deck.generateFrom(new RandomCardStrategy());
    private final Dealer dealer = new Dealer();
    private final List<Player> players = new ArrayList<>();
    // TODO: 반복자 리팩토링
    private int playerCursor = 0;

    public void setPlayer(NamesRequestDto requestDto) {
        players.addAll(
            requestDto.names().stream()
                .map(Player::new)
                .toList());
    }

    public StartingCardsResponseDto drawStartingCards() {
        dealer.initialize(deck);
        for (var player : players) {
            player.initialize(deck);
        }
        return StartingCardsResponseDto.of(dealer, players);
    }

    public void nextPlayer() {
        playerCursor++;
    }

    public String getCurrentPlayerName() {
        return players.get(playerCursor).getName();
    }

    public void drawCardForCurrentPlayer() {
        Player currentPlayer =players.get(playerCursor);
        currentPlayer.drawCard(deck);
    }

    public GamerDto getNowPlayerCards() {
        return GamerDto.from(players.get(playerCursor));
    }

    public boolean hasNextPlayer() {
        return playerCursor < players.size();
    }

    public boolean currentPlayerCanReceiveAdditionalCards() {
        return players.get(playerCursor).canReceiveAdditionalCards();
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
