package blackJack.domain;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import blackJack.domain.card.Deck;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participant;
import blackJack.domain.participant.Player;

public class BlackJackGame {

    private static final String ERROR_MESSAGE_DUPLICATE_PLAYER_NAME = "플레이어의 이름은 중복될 수 없습니다.";
    private static final String ERROR_MESSAGE_PLAYER_COUNT = "플레이어의 인원수는 1명 이상 7명 이하여야 합니다.";
    private static final int MINIMUM_COUNT = 1;
    private static final int MAXIMUM_COUNT = 7;
    private static final int INITIAL_CARD_COUNT = 2;
    private static final int BLACK_JACK = 21;
    private static final int DEALER_MAXIMUM_RECEIVE_CARD_SCORE = 16;

    private final Deck deck;
    private final Dealer dealer;
    private final List<Player> players;

    public BlackJackGame(Dealer dealer, List<Player> players) {
        validateDuplicatePlayerName(players);
        validatePlayerCount(players);
        this.deck = new Deck();
        this.dealer = dealer;
        this.players = players;
    }

    private void validateDuplicatePlayerName(List<Player> players) {
        if (players.size() != new HashSet<>(players).size()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_DUPLICATE_PLAYER_NAME);
        }
    }

    private void validatePlayerCount(List<Player> players) {
        if (players.size() < MINIMUM_COUNT || players.size() > MAXIMUM_COUNT) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PLAYER_COUNT);
        }
    }

    public void firstCardDispensing() {
        distributeCard(dealer, INITIAL_CARD_COUNT);
        players.forEach(player -> distributeCard(player, INITIAL_CARD_COUNT));
    }

    public void distributeCard(Participant participant, int count) {
        for (int i = 0; i < count; i++) {
            participant.receiveCard(deck.getCard());
        }
    }

    public boolean hasNextTurn(Participant participant) {
        if (participant instanceof Dealer) {
            return participant.getScore() <= DEALER_MAXIMUM_RECEIVE_CARD_SCORE;
        }
        return participant.getScore() <= BLACK_JACK;
    }

    public Map<Player, WinOrLose> calculateGameResult() {
        final Map<Player, WinOrLose> gameResult = new LinkedHashMap<>();

        for (Player player : players) {
            WinOrLose winOrLose = WinOrLose.calculateWinOrLose(player.getScore(), dealer.getScore());
            gameResult.put(player, winOrLose);
        }

        return gameResult;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
