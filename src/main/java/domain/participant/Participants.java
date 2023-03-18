package domain.participant;

import domain.card.Card;
import domain.card.Deck;
import domain.game.GameResult;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Participants {

    private static final String DEALER_NAME = "딜러";
    private static final int MIN_COUNT = 1;
    private static final int MAX_COUNT = 7;

    private final Dealer dealer;
    private final List<Player> players;

    private Participants(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static Participants create(final List<String> playersName, final Function<String, Integer> readBetAmount) {
        final List<String> playersTrimName = processTrimPlayersName(playersName);

        validateDuplicateNames(playersTrimName);
        validatePlayerCount(playersTrimName);
        validatePlayerBlankName(playersTrimName);

        final Dealer dealer = Dealer.create(DEALER_NAME);
        final List<Player> players = makePlayers(playersName, readBetAmount);

        return new Participants(dealer, players);
    }

    private static List<Player> makePlayers(final List<String> playersName,
            final Function<String, Integer> readBetAmount) {
        return playersName.stream()
                .map(playerName -> makePlayer(playerName, readBetAmount))
                .collect(Collectors.toList());
    }

    private static Player makePlayer(final String playerName, final Function<String, Integer> readBetAmount) {
        try {
            final Integer apply = readBetAmount.apply(playerName);
            return Player.create(playerName, apply);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makePlayer(playerName, readBetAmount);
        }
    }

    private static List<String> processTrimPlayersName(final List<String> playerNames) {
        return playerNames.stream()
                .map(String::trim)
                .collect(Collectors.toUnmodifiableList());
    }

    private static void validateDuplicateNames(final List<String> playerNames) {
        final Set<String> uniqueNames = new HashSet<>(playerNames);

        if (playerNames.size() != uniqueNames.size()) {
            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다.");
        }
    }

    private static void validatePlayerCount(final List<String> playerNames) {
        final int playerCount = playerNames.size();

        if (playerCount < MIN_COUNT || playerCount > MAX_COUNT) {
            throw new IllegalArgumentException("플레이어는 최소 1명, 최대 7명이어야 합니다.");
        }
    }

    private static void validatePlayerBlankName(final List<String> playerNames) {
        for (String playerName : playerNames) {
            if (playerName.isBlank()) {
                throw new IllegalArgumentException("플레이어의 이름은 공백일 수 없습니다.");
            }
            if (DEALER_NAME.equals(playerName)) {
                throw new IllegalArgumentException("플레이어는 '딜러'라는 이름을 가질 수 없습니다.");
            }
        }
    }

    public boolean canDraw(final String targetPlayerName) {
        final Player targetPlayer = findPlayerByName(targetPlayerName);

        return targetPlayer.canDraw();
    }

    public void addCardForPlayer(final String targetPlayerName, final Card drawCard) {
        final Player targetPlayer = findPlayerByName(targetPlayerName);

        targetPlayer.addCard(drawCard);
    }

    public void addCardForDealer(final Card drawCard) {
        dealer.addCard(drawCard);
    }

    public int playDealerTurn(final Deck deck) {
        return dealer.playDealerTurn(deck);
    }

    public Map<String, BigDecimal> calculateProfit() {
        final Map<Player, GameResult> gameResult = calculateGameResult();

        return gameResult.keySet().stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        player -> player.calculateProfit(gameResult.getOrDefault(player, GameResult.DRAW))
                ));
    }

    private Map<Player, GameResult> calculateGameResult() {
        return players.stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> dealer.calculateResult(player.participantCard())
                ));
    }

    private Player findPlayerByName(final String targetPlayerName) {
        return players.stream().filter(player -> player.matchByName(targetPlayerName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 플레이어입니다."));
    }

    public List<String> playersName() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public List<String> getParticipantsName() {
        final List<String> participantsName = new ArrayList<>();

        participantsName.add(dealer.getName());
        participantsName.addAll(playersName());
        return participantsName;
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public Card getDealerStartCard() {
        return dealer.getStartCard();
    }

    public List<Card> getPlayerCard(final String targetPlayerName) {
        final Player targetPlayer = findPlayerByName(targetPlayerName);
        final ParticipantCard playerCard = targetPlayer.participantCard();

        return playerCard.getCards();
    }

    public List<Card> getDealerCard() {
        final ParticipantCard dealerCard = dealer.participantCard();

        return dealerCard.getCards();
    }

    public int getDealerScore() {
        return dealer.calculateScore();
    }

    public int getPlayerScore(final String targetPlayerName) {
        final Player targetPlayer = findPlayerByName(targetPlayerName);

        return targetPlayer.calculateScore();
    }
}
