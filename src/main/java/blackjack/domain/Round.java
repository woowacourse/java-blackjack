package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.view.dto.PlayerResultDto;
import blackjack.view.dto.PlayerStatusDto;
import blackjack.view.dto.RoundStatusDto;

import java.util.*;
import java.util.stream.Collectors;

public class Round {
    public static final int GAME_OVER_SCORE = 21;

    private final Deck deck;
    private final Dealer dealer;
    private final List<Player> players;

    public Round(final Deck deck, final Dealer dealer, final List<Player> players) {
        this.deck = deck;
        this.dealer = dealer;
        this.players = new ArrayList<>(players);
    }

    public void initialize() {
        dealer.addFirstCards(deck.makeTwoCards());
        players.forEach(player -> player.addFirstCards(deck.makeTwoCards()));
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public RoundStatusDto getRoundStatus() {
        return new RoundStatusDto(dealer.getName(),
                getDealerCardStatus(),
                players.stream()
                        .map(this::getPlayerStatusDto)
                        .collect(Collectors.toList()),
                dealer.getScore());
    }

    public Queue<Outcome> makeRoundOutComes() {
        return players.stream()
                .map(player -> Outcome.findOutcome(dealer.getScore(), player.getScore()))
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    public List<PlayerResultDto> makePlayerResults(final Queue<Outcome> gameOutComes) {
        return players.stream()
                .map(player -> new PlayerResultDto(player.getName(), gameOutComes.poll()))
                .collect(Collectors.toList());
    }

    public Map<String, Queue<Outcome>> finishGame() {
        return Result.findResults(this);
    }

    public boolean addDealerCard() {
        if (!dealer.isGameOver(GAME_OVER_SCORE)) {
            dealer.addCard(deck.makeOneCard());
            return true;
        }
        return false;
    }

    public void addPlayerCard(Player player) {
        Player findPlayer = players.stream()
                .filter(p -> p.equals(player))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 유저입니다"));
        findPlayer.addCard(deck.makeOneCard());
    }

    private List<String> getDealerCardStatus() {
        return dealer.getCardsStatus();
    }

    private PlayerStatusDto getPlayerStatusDto(final Player player) {
        return new PlayerStatusDto(player.getName(), player.getCardsStatus(), player.getScore());
    }
}
