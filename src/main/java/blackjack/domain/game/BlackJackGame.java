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

    public Players createPlayers(String allName){
        List<String> names = Arrays.asList(allName.split(","));
        List<Player> players = new ArrayList<>();

        dealer = new Dealer();
        players.add(dealer);
        players.addAll(names.stream()
                .map(Name::new)
                .map(Gambler::new)
                .collect(Collectors.toList()));
        return new Players(players);
    }

    public void initPlayerCards(Players players){
        for(Player player : players){
            player.initializeCards(deck);
        }
    }

    public void giveCard(Player player) {
        player.drawCard(deck);
    }

    public void giveDealerCard(){
        dealer.drawCard(deck);
    }

    public boolean ableToDraw(){
        return dealer.ableToDraw();
    }

    public Result getResult(Players players){
        Result result = new Result(dealer.getCards());
        for(Player player : players){
            addGamblerResult(result, player);
        }
        return result;
    }

    private void addGamblerResult(Result result, Player player){
        WinOrLose winOrLose = dealer.calculateGamblerWinOrNot(player);
        result.add(player, winOrLose);
    }
}
