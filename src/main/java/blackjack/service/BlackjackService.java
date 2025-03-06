package blackjack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import blackjack.domain.deck.Deck;
import blackjack.domain.deck.RandomCardStrategy;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.dto.GamerDto;
import blackjack.dto.request.NamesRequestDto;
import blackjack.dto.request.SelectionRequestDto;
import blackjack.dto.response.FinalResultResponseDto;
import blackjack.dto.response.RoundResultsResponseDto;
import blackjack.dto.response.StartingCardsResponseDto;

public class BlackjackService {

    private final Deck deck = Deck.generateFrom(new RandomCardStrategy());
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

    public void drawCards(
        Function<String, SelectionRequestDto> readSelection,
        Consumer<GamerDto> printAdditionalCard,
        Consumer<String> printBustNotice
    ) {
        for (var player : players) {
            while (readSelection.apply(player.getName()).selection()) {
                player.drawCard(deck);
                printAdditionalCard.accept(GamerDto.from(player));
                if (!player.canReceiveAdditionalCards()) {
                    printBustNotice.accept(player.getName());
                    break;
                }
            }
        }
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
