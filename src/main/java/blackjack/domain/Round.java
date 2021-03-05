package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.view.dto.PlayerResultDto;
import blackjack.view.dto.PlayerStatusDto;
import blackjack.view.dto.RoundStatusDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Round {
    public static final int GAME_OVER_SCORE = 21;
    private static final int FIRST_INDEX = 0;

    private final List<Card> shuffledCards;
    private final Dealer dealer;
    private final List<Player> players;

    private Round(final List<Card> cards, final Dealer dealer, final List<Player> players) {
        this.shuffledCards = new ArrayList<>(cards);
        this.dealer = dealer;
        this.players = new ArrayList<>(players);
    }

    public static Round generateWithRandomCards(final Dealer dealer, final List<Player> players) {
        return new Round(Card.getShuffledCards(), dealer, players);
    }

    public List<Card> makeTwoCards() {
        return IntStream.range(0, 2)
                .mapToObj(count -> shuffledCards.remove(FIRST_INDEX))
                .collect(Collectors.toList());
    }

    public Card makeOneCard() {
        return shuffledCards.remove(FIRST_INDEX);
    }

    public List<String> getDealerCardStatus() {
        return dealer.getCardsStatus();
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
                dealer.calculateScore(GAME_OVER_SCORE));
    }

    public List<Outcome> makeRoundOutComes() {
        return players.stream()
                .map(player -> Outcome.findOutcome(dealer.calculateScore(GAME_OVER_SCORE), player.calculateScore(GAME_OVER_SCORE)))
                .collect(Collectors.toList());
    }

    public List<PlayerResultDto> makePlayerResults(List<Outcome> gameOutComes) {
        return players.stream()
                .map(player -> new PlayerResultDto(player.getName(), gameOutComes.remove(FIRST_INDEX)))
                .collect(Collectors.toList());
    }

    public Map<String, List<Outcome>> finishGame() {
        return Result.findResults(this);
    }

    public boolean addDealerCard() {
        if (!dealer.isGameOver(GAME_OVER_SCORE)) {
            dealer.addCard(makeOneCard());
            return true;
        }
        return false;
    }

    public void initialize() {
        dealer.addFirstCards(makeTwoCards());
        players.forEach(player -> player.addFirstCards(makeTwoCards()));
    }

    private PlayerStatusDto getPlayerStatusDto(final Player player) {
        return new PlayerStatusDto(player.getName(), player.getCardsStatus(), player.calculateScore(GAME_OVER_SCORE));
    }
}
