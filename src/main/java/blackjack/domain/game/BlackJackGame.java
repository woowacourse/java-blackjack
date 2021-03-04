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

    private final Deck deck;
    private Dealer dealer;

    public BlackJackGame(){
        deck = new Deck();
        dealer = new Dealer();
    }

    public Players createPlayers(final String allName){
        List<String> names = Arrays.asList(allName.split(","));
        List<Player> players = new ArrayList<>();

        players.add(dealer);
        players.addAll(names.stream()
                .map(Name::new)
                .map(Gambler::new)
                .collect(Collectors.toList()));
        return new Players(players);
    }

    public void initPlayerCards(final Players players){
        for(Player player : players){
            player.initializeCards(deck);
        }
    }

    public void giveCard(final Player player) {
        player.drawCard(deck);
    }

    public void giveDealerCard(){
        dealer.drawCard(deck);
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
