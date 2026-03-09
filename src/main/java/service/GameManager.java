package service;

import domain.MatchResult;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import dto.DealerHandDto;
import dto.GameResultDto;
import dto.PlayersHandDto;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

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

    public void addPlayers(List<String> names) {
        List<Player> players = names.stream()
                .map(Player::new)
                .toList();


        this.players = new Players(players);
    }

    public void dealInitialCardsToParticipants() {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            dealCardToDealer();
            players.dealCardToAllPlayers(deck);
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

    public DealerHandDto toDealerHandDto() {
        return new DealerHandDto(dealer.getFirstCard(), dealer.getCards(), dealer.getScore());
    }

    public PlayersHandDto toPlayersHandDto() {
        return new PlayersHandDto(players.getPlayersHand(), players.getPlayersScore());
    }

    public GameResultDto calculateResults() {
        Map<Player, MatchResult> playersResult = players.calculateResult(dealer);
        Map<MatchResult, Integer> dealerResult = dealer.calculateResult(playersResult);

        return new GameResultDto(dealerResult, playersResult);
    }
}
