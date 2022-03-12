package blackjack.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.Player;
import blackjack.domain.result.BlackJackResult;
import blackjack.dto.GameResultDto;
import blackjack.dto.GamerDto;

public class BlackJackGame {

    public static final int MAX_CARD_VALUE = 21;
    private static final int INIT_DISTRIBUTION_COUNT = 2;
    private static final int ADDITIONAL_DISTRIBUTE_STANDARD = 16;
    private static final int DEFAULT_COUNT = 0;
    private static final int INCREASE_COUNT = 1;

    private final CardFactory cardFactory;
    private final Gamers gamers;

    public BlackJackGame(List<String> names) {
        this.cardFactory = new CardFactory(Card.getCards());
        this.gamers = new Gamers(names);
    }

    public void distributeFirstCards() {
        for (int i = 0; i < INIT_DISTRIBUTION_COUNT; i++) {
            gamers.giveCardToAllGamers(cardFactory::draw);
        }
    }

    public int distributeAdditionalToDealer() {
        int count = 0;
        while (!gamers.checkDealerOverThan(ADDITIONAL_DISTRIBUTE_STANDARD)) {
            gamers.giveCardToDealer(cardFactory::draw);
            count++;
        }
        return count;
    }

    public void distributeCardToPlayer(String name) {
        gamers.giveCardToPlayer(name, cardFactory::draw);
    }

    public boolean isBurst(String name) {
        return gamers.checkPlayerOverThan(name, MAX_CARD_VALUE);
    }

    public GameResultDto createResult() {
        Map<BlackJackResult, Integer> dealerResult = createDealerResult();
        Map<String, BlackJackResult> playerResults = createPlayerResults(dealerResult);
        return new GameResultDto(playerResults, dealerResult);
    }

    private Map<String, BlackJackResult> createPlayerResults(Map<BlackJackResult, Integer> dealerResult) {
        Dealer dealer = gamers.getDealer();
        List<Player> players = gamers.getPlayers();

        Map<String, BlackJackResult> playerResults = new HashMap<>();
        for (Player player : players) {
            BlackJackResult result = player.match(dealer);
            playerResults.put(player.getName().getValue(), result);
            dealerResult.merge(result.getReverse(), INCREASE_COUNT, Integer::sum);
        }
        return playerResults;
    }

    private Map<BlackJackResult, Integer> createDealerResult() {
        Map<BlackJackResult, Integer> dealerResult = new HashMap<>();
        for (BlackJackResult blackJackResult : BlackJackResult.values()) {
            dealerResult.put(blackJackResult, DEFAULT_COUNT);
        }
        return dealerResult;
    }

    public List<String> getPlayerNames() {
        return gamers.findPlayerNames();
    }

    public GamerDto getDealerDto() {
        return new GamerDto(gamers.getDealer());
    }

    public List<GamerDto> getPlayerDtos() {
        return gamers.getPlayers().stream()
            .map(GamerDto::new)
            .collect(Collectors.toList());
    }

    public GamerDto getPlayerDto(String name) {
        return new GamerDto(gamers.findPlayerByName(name));
    }
}
