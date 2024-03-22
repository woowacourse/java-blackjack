package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.game.PlayerResult;
import blackjack.domain.game.ResultJudge;
import blackjack.domain.money.PlayersProfit;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class Players {
    private final List<Player> values;

    public Players(List<Player> values) {
        this.values = values;
    }

    public void draw(Supplier<List<Card>> pickCard) {
        for (Player player : values) {
            player.draw(pickCard.get());
        }
    }

    public PlayersProfit calculateProfit(Dealer dealer) {
        PlayersProfit playersProfit = new PlayersProfit();
        ResultJudge resultJudge = new ResultJudge();

        for (Player player : values) {
            PlayerResult result = resultJudge.judgePlayerResult(dealer, player);
            playersProfit.addPlayerChip(player, player.madeProfit(result));
        }

        return playersProfit;
    }

    public List<String> names() {
        return values.stream().map(Player::name).toList();
    }

    public List<Player> values() {
        return Collections.unmodifiableList(values);
    }
}
