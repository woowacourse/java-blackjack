package blackjack.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.dto.GameResultDto;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;
import blackjack.domain.result.BlackJackResult;

public class BlackJackGame {

    public static final int MAX_CARD_VALUE = 21;
    private static final int INIT_DISTRIBUTION_COUNT = 2;
    private static final int ADDITIONAL_DISTRIBUTE_STANDARD = 16;
    private static final int DEFAULT_COUNT = 0;
    private static final int INCREASE_COUNT = 1;

    private static final String DUPLICATION_NAME_ERROR = "중복된 이름이 존재합니다.";
    private static final String NOT_EXIST_PLAYER_ERROR = "플레이어가 존재하지 않습니다.";

    private final CardFactory cardFactory;
    private final List<Player> players;
    private final Dealer dealer;

    public BlackJackGame(List<String> names) {
        validateDuplicationNames(names);
        this.cardFactory = new CardFactory(Card.getCards());
        this.dealer = new Dealer();
        this.players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private void validateDuplicationNames(List<String> names) {
        int count = (int) names.stream()
                .distinct()
                .count();
        if (count != names.size()) {
            throw new IllegalArgumentException(DUPLICATION_NAME_ERROR);
        }
    }

    public void distributeFirstCards() {
        for (int i = 0; i < INIT_DISTRIBUTION_COUNT; i++) {
            distributeCard(dealer);
            players.forEach(this::distributeCard);
        }
    }

    public int distributeAdditionalToDealer() {
        int count = 0;
        while (!dealer.isOverThan(ADDITIONAL_DISTRIBUTE_STANDARD)) {
            distributeCard(dealer);
            count++;
        }
        return count;
    }

    private void distributeCard(Gamer gamer) {
        gamer.addCard(cardFactory.draw());
    }

    public void distributeCardToPlayer(String name) {
        findPlayerByName(name).addCard(cardFactory.draw());
    }

    public Player findPlayerByName(String name) {
        return players.stream()
                .filter(player -> player.isSameName(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_PLAYER_ERROR));
    }

    public boolean isBurst(String name) {
        Player player = findPlayerByName(name);
        return player.getCardsNumberSum() > MAX_CARD_VALUE;
    }

    public GameResultDto createResult() {
        Map<BlackJackResult, Integer> dealerResult = initDealerResult();
        Map<String, BlackJackResult> playerResults = createPlayerResults(dealerResult);
        return new GameResultDto(playerResults, dealerResult);
    }

    private Map<String, BlackJackResult> createPlayerResults(Map<BlackJackResult, Integer> dealerResult) {
        Map<String, BlackJackResult> playerResults = new HashMap<>();
        for (Player player : players) {
            BlackJackResult result = player.match(dealer);
            playerResults.put(player.getName().getValue(), result);
            dealerResult.merge(result.getReverse(), INCREASE_COUNT, Integer::sum);
        }
        return playerResults;
    }

    private Map<BlackJackResult, Integer> initDealerResult() {
        Map<BlackJackResult, Integer> dealerResult = new HashMap<>();
        for (BlackJackResult blackJackResult : BlackJackResult.values()) {
            dealerResult.put(blackJackResult, DEFAULT_COUNT);
        }
        return dealerResult;
    }

    public List<String> getPlayerNames() {
        return players.stream()
            .map(Player::getName)
            .map((Name::getValue))
            .collect(Collectors.toList());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
