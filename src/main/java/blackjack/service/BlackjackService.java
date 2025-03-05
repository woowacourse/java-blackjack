package blackjack.service;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.deck.Deck;
import blackjack.domain.deck.RandomCardStrategy;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.dto.NamesRequestDto;
import blackjack.dto.StartingCardsResponseDto;

public class BlackjackService {

    private final Deck deck = new Deck(new RandomCardStrategy());
    private final Dealer dealer = new Dealer();
    private final List<Player> players = new ArrayList<>();

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
}
