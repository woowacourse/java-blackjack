package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;
import blackjack.domain.player.Name;
import blackjack.domain.player.Names;
import blackjack.domain.player.Players;
import blackjack.domain.result.Result;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Blackjack {
    private final Deck deck;

    public Blackjack(Deck deck) {
        this.deck = deck;
    }

    public Players acceptPlayers(Names names, BettingAmounts battingAmounts) {
        Dealer dealer = Dealer.createDefaultDealer(drawTwo());
        List<GamePlayer> gamePlayers = IntStream.range(0, names.size())
                                                .mapToObj(i -> new GamePlayer(names.getName(i),
                                                        drawTwo(),
                                                        battingAmounts.getBattingAmount(i)))
                                                .toList();
        return new Players(dealer, gamePlayers);
    }

    public Result compareResults(Dealer dealer, List<GamePlayer> gamePlayers) {
        Map<Name, Profit> playerResults = new LinkedHashMap<>();
        Profit dealerProfit = new Profit(0);
        playerResults.put(dealer.getName(), dealerProfit);

        for (GamePlayer gamePlayer : gamePlayers) {
            Profit playerProfit = gamePlayer.confirmProfit(dealer);
            playerResults.put(gamePlayer.getName(), playerProfit);
            dealerProfit = dealerProfit.subtractProfit(playerProfit);
        }
        playerResults.put(dealer.getName(), dealerProfit);

        return new Result(playerResults);
    }

    private Cards drawTwo() {
        return new Cards(List.of(deck.draw(), deck.draw()));
    }

    public Card draw() {
        return deck.draw();
    }

}
