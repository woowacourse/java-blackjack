package blackjack.service;

import java.util.function.Consumer;
import java.util.function.Function;

import blackjack.domain.deck.Deck;
import blackjack.domain.deck.RandomCardStrategy;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.dto.GamerDto;
import blackjack.dto.request.NamesRequestDto;
import blackjack.dto.request.SelectionRequestDto;
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

    public void drawCards(
        Function<String, SelectionRequestDto> readSelection,
        Consumer<GamerDto> printAdditionalCard,
        Consumer<String> printBustNotice
    ) {
        while(players.hasNext()) {
            drawCardsForCurrentPlayer(readSelection, printAdditionalCard, printBustNotice);
            players.next();
        }
    }

    private void drawCardsForCurrentPlayer(Function<String, SelectionRequestDto> readSelection, Consumer<GamerDto> printAdditionalCard,
        Consumer<String> printBustNotice) {
        Player currentPlayer = players.getCurrentPlayer();
        while (readSelection.apply(currentPlayer.getName()).selection()) {
            currentPlayer.drawCard(deck);
            printAdditionalCard.accept(GamerDto.from(currentPlayer));
            if (!currentPlayer.canReceiveAdditionalCards()) {
                printBustNotice.accept(currentPlayer.getName());
                break;
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
