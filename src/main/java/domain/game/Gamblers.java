package domain.game;

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
    private final List<Player> players;

    public Gamblers(Dealer dealer, List<Player> players) {
        validatePlayers(players);
        this.dealer = dealer;
        this.players = players;
    }

    public void distributeSetUpCards(CardPack cardPack) {
        dealer.takeCards(cardPack.poll(), cardPack.poll());
        players.forEach(player -> player.takeCards(cardPack.poll(), cardPack.poll()));
    }

    public void distributeExtraCardsToPlayers(CardPack cardPack, GamblerAnswer playerAnswer) {
        players.forEach(player -> distributeExtraCards(player, playerAnswer, cardPack));
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

    public Map<Player, GamblingMoney> evaluatePlayerProfits() {
        return players.stream()
            .collect(toMap(
                Function.identity(),
                player -> player.calculateProfit(dealer),
                (exist, replace) -> exist,
                LinkedHashMap::new
            ));
    }

    public GamblingMoney evaluateDealerProfit() {
        Map<Player, GamblingMoney> playerProfits = evaluatePlayerProfits();
        GamblingMoney sum = GamblingMoney.sum(playerProfits.values());
        return sum.negative();
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
