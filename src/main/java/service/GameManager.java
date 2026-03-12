package service;

import domain.MatchResult;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import dto.DealerDto;
import dto.GameResultDto;
import dto.PlayersDto;
import dto.ProfitResultDto;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class GameManager {

    private static final int INITIAL_CARD_COUNT = 2;

    private final Deck deck;
    private final Dealer dealer;
    private Players players;

    public GameManager() {
        this.deck = new Deck();
        this.dealer = new Dealer();
        this.players = new Players();
    }

    public void covertAndCreatePlayers(List<String> names) {
        List<Player> players = names.stream()
                .map(Player::new)
                .toList();

        this.players = new Players(players);
    }

    public void forEachPlayerPlaceBet(Function<String, Integer> action) {
        players.placeBetAllPlayers(action);
    }

    public void dealInitialCardsToParticipants() {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            dealCardToDealer();
            for (Player player : players.getPlayers()) {
                dealCardTo(player);
            }
        }
    }

    public void forEachPlayer(Consumer<Player> action) {
        players.playTurns(action);
    }

    public void dealCardTo(Player player) {
        players.findBy(player).receive(deck.drawCard());
    }

    public void dealCardToDealer() {
        dealer.receive(deck.drawCard());
    }

    public boolean isDealerShouldHit() {
        return dealer.shouldHit();
    }

    public DealerDto toDealerDto() {
        return new DealerDto(dealer.getFirstCard(), dealer.getCards(), dealer.getScore());
    }

    public PlayersDto toPlayersDto() {
        return new PlayersDto(players.getPlayersHand(), players.getPlayersScore());
    }

    public GameResultDto getGameResults() {
        Map<Player, MatchResult> playersMatchResult = players.calculateMatchResult(dealer);
        Map<MatchResult, Integer> dealerMatchResult = dealer.calculateMatchResult(playersMatchResult);

        return new GameResultDto(dealerMatchResult, playersMatchResult);
    }

    public ProfitResultDto getProfitResults() {
        Map<Player, MatchResult> playersMatchResult = players.calculateMatchResult(dealer);
        Map<Player, Integer> playersProfitResult = players.calculateProfitResult(playersMatchResult);
        int dealerProfitResult = dealer.calculateProfitResult(playersProfitResult);

        return new ProfitResultDto(dealerProfitResult, playersProfitResult);
    }
}
