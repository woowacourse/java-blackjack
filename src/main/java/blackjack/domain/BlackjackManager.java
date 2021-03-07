package blackjack.domain;

import blackjack.domain.carddeck.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class BlackjackManager {

    private static final int INIT_CARD_COUNT = 2;

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final Players players;

    public BlackjackManager(final Dealer dealer, final Players players) {
        this(CardDeck.newShuffledDeck(), dealer, players);
    }

    private BlackjackManager(final CardDeck cardDeck, final Dealer dealer, final Players players) {
        this.cardDeck = cardDeck;
        this.dealer = dealer;
        this.players = players;
    }

    public void initDrawCards() {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            this.dealer.addCard(cardDeck.draw());
            this.players.drawCards(cardDeck);
        }
    }

    public static GameResultDto getGameResult(Dealer dealer, Players players) {
        Map<String, GameResult> playerResult = getPlayersResult(dealer, players);
        return new GameResultDto(playerResult, getDealerResult(playerResult));
    }

    private static Map<String, GameResult> getPlayersResult(Dealer dealer, Players players) {
        Map<String, GameResult> playersResult = new HashMap<>();
        players.toList()
            .forEach(player -> playersResult.put(player.getName(), GameResult.judgeHand(
                dealer.getTotalScore(), player.getTotalScore())));
        return playersResult;
    }

    private static Map<GameResult, Integer> getDealerResult(Map<String, GameResult> playerResult) {
        Map<GameResult, Integer> dealerResult = new EnumMap<>(GameResult.class);
        Arrays.asList(GameResult.values()).forEach(value -> dealerResult.put(value, 0));
        playerResult.values()
            .forEach(result -> dealerResult.computeIfPresent(GameResult.reverseResult(result),
                ((gameResult, count) -> ++count)));
        return dealerResult;
    }
}
