package blackjack.domain.game;

import blackjack.domain.YesOrNo;
import blackjack.domain.card.Deck;
import blackjack.domain.player.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {

    private final Deck deck = new Deck();

    public BlackJackGame(){
    }

    public Players createPlayers(String allName){
        List<String> names = Arrays.asList(allName.split(","));
        List<Player> players = new ArrayList<>();

        players.add(new Dealer());
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

    // dealer인 경우인데 구별 필
    public void giveCard(Player player) {
        // dealer 드로우 조건 확인
        player.drawCard(deck); // Deck을 넘기는게 좋을지, Card를 넘기는게 좋을지...
    }

    public void giveCard(Player player, String askDrawOrNot) {
        if(YesOrNo.of(askDrawOrNot) == YesOrNo.YES){
            player.drawCard(deck); // Deck을 넘기는게 좋을지, Card를 넘기는게 좋을지...
        }
    }
}
