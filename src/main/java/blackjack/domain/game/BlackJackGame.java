package blackjack.domain.game;

import blackjack.domain.YesOrNo;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import blackjack.domain.player.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {

    private final Deck deck = new Deck();
    private Dealer dealer;

    public BlackJackGame(){
    }

    public Players createPlayers(String nameLine){
        List<Player> players = initPlayers(splitAndParseToNames(nameLine));
        return new Players(players);
    }

    private List<Name> splitAndParseToNames(String nameLine){
        return Arrays.asList(nameLine.split(","))
                .stream().map(Name::new)
                .collect(Collectors.toList());
    }

    private List<Player> initPlayers(List<Name> names){
        List<Player> players = new ArrayList<>();

        dealer = new Dealer();
        players.add(dealer);
        players.addAll(names.stream()
                .map(Gambler::new)
                .peek(player -> player.initializeCards(deck))
                .collect(Collectors.toList()));

        return players;
    }

    public void giveCard(final Player player) {
        player.drawCard(deck.draw());
    }

    public void giveDealerCard(){
        dealer.drawCard(deck.draw());
    }

    public boolean ableToDraw(){
        return dealer.ableToDraw();
    }

    public Result getResult(final Players players){
        Result result = new Result(dealer.getCards());
        for(Player player : players){
            addGamblerResult(result, player);
        }
        return result;
    }

    private void addGamblerResult(final Result result, final Player player){
        WinOrLose winOrLose = dealer.calculateGamblerWinOrNot(player);
        result.add(player, winOrLose);
    }
}
