package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.common.Names;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;
import blackjack.domain.player.Players;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.GamePlayerResult;
import blackjack.domain.result.Result;
import java.util.ArrayList;
import java.util.List;

public class Blackjack {
    private final Deck deck;

    public Blackjack(Deck deck) {
        this.deck = deck;
    }

    public Blackjack() {
        deck = Deck.createPack();
    }

    public Players acceptPlayers(Names names) {
        Dealer dealer = Dealer.createDefaultDealer(drawTwo());
        List<GamePlayer> gamePlayers = names.stream()
                                            .map(name -> new GamePlayer(name, drawTwo()))
                                            .toList();
        return new Players(dealer, gamePlayers);
    }

    public Result compareResults(Dealer dealer, List<GamePlayer> gamePlayers) {
        List<GamePlayerResult> gamePlayerResults = new ArrayList<>();

        for (GamePlayer gamePlayer : gamePlayers) {
            gamePlayerResults.add(
                    new GamePlayerResult(gamePlayer.getName(), dealer.compareResult(gamePlayer)));
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
