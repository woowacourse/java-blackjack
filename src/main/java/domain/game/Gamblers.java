package domain.game;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

import domain.card.CardPack;
import domain.participant.Dealer;
import domain.participant.Gambler;
import domain.participant.Player;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Gamblers {

    private static final int MAX_PLAYERS_COUNT = 8;

    private final Dealer dealer;
    private final Map<Player, BettingMoney> playerBettings;

    public Gamblers(Dealer dealer, Map<Player, BettingMoney> playerBettings) {
        validatePlayers(playerBettings.keySet());
        this.dealer = dealer;
        this.playerBettings = playerBettings;
    }

    public void distributeSetUpCards(CardPack cardPack) {
        dealer.takeCards(cardPack.poll(), cardPack.poll());
        playerBettings.keySet().forEach(player -> player.takeCards(cardPack.poll(), cardPack.poll()));
    }

    public void distributeExtraCardsToPlayers(CardPack cardPack, GamblerAnswer playerAnswer) {
        playerBettings.keySet().forEach(player -> distributeExtraCards(player, playerAnswer, cardPack));
    }

    public void distributeExtraCardsToDealer(CardPack cardPack, GamblerAnswer dealerAnswer) {
        distributeExtraCards(dealer, dealerAnswer, cardPack);
    }

    private void distributeExtraCards(Gambler gambler, GamblerAnswer gamblerAnswer,
        CardPack cardPack) {
        while (gambler.canTakeMoreCard() && gamblerAnswer.isAnswerOK(gambler)) {
            gambler.takeCards(cardPack.poll());
            gamblerAnswer.notifyResult(gambler);
        }
    }

    public Map<Winning, Long> evaluateDealerWinnings() {
        return evaluatePlayerWinnings().values()
            .stream()
            .collect(groupingBy(Winning::reverse, counting()));
    }

    public Map<Player, Winning> evaluatePlayerWinnings() {
        return playerBettings.keySet().stream()
            .collect(toMap(
                Function.identity(),
                dealer::judgeWinningForPlayer
            ));
    }

    private void validatePlayers(Collection<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("플레이어가 최소 1명은 있어야 합니다.");
        }
        if (players.size() > MAX_PLAYERS_COUNT) {
            throw new IllegalArgumentException("최대 " + MAX_PLAYERS_COUNT + "명의 플레이어만 참가할 수 있습니다.");
        }
    }
}
