package blackjack.domain;

import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Names;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;
import blackjack.domain.player.Players;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.GamePlayerResult;
import blackjack.domain.result.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Blackjack {
    private final Deck deck;

    public Blackjack(Deck deck) {
        this.deck = deck;
    }

    public Players acceptPlayers(Names names, BattingAmounts battingAmounts) {
        Dealer dealer = Dealer.createDefaultDealer(drawTwo());
        List<GamePlayer> gamePlayers = IntStream.range(0, names.size())
                                                .mapToObj(i -> new GamePlayer(names.getName(i),
                                                        drawTwo(),
                                                        battingAmounts.getBattingAmount(i)))
                                                .toList();
        return new Players(dealer, gamePlayers);
    }

    public Result compareResults(Dealer dealer, List<GamePlayer> gamePlayers) {
        List<GamePlayerResult> gamePlayerResults = new ArrayList<>();

        for (GamePlayer gamePlayer : gamePlayers) {
            gamePlayerResults.add(
                    new GamePlayerResult(gamePlayer.getName(), gamePlayer.confirmResult(dealer)));
        }

        return new Result(gamePlayerResults, DealerResult.of(dealer.getName(), gamePlayerResults));
    }

    private Cards drawTwo() {
        return new Cards(List.of(deck.draw(), deck.draw()));
    }

    public Card draw() {
        return deck.draw();
    }

}
