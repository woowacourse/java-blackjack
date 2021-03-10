package blackjack.domain.game;

import blackjack.domain.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Result {
    Map<Player, WinOrLose> winningTable = new HashMap<>();

    public void add(Player player, WinOrLose winOrLose){
        winningTable.put(player, winOrLose);
    }

    public WinOrLose get(Player player){
        return winningTable.get(player);
    }

    public WinOrLose getReverseResult(Player player){
        if(winningTable.get(player).equals(WinOrLose.WIN)){
            return WinOrLose.LOSE;
        }

        if(winningTable.get(player).equals(WinOrLose.LOSE)){
            return WinOrLose.WIN;
        }

        return WinOrLose.DRAW;
    }

    public Set<Player> getPlayerSet(){
        return winningTable.keySet();
    }

    public int getNumberOfWin(){
        return (int)winningTable.values().stream()
                .filter(winOrLose -> winOrLose.equals(WinOrLose.WIN))
                .count();
    }

    public int getNumberOfLose(){
        return (int)winningTable.values().stream()
                .filter(winOrLose -> winOrLose.equals(WinOrLose.LOSE))
                .count();
    }

    public int getNumberOfDraw(){
        return (int)winningTable.values().stream()
                .filter(winOrLose -> winOrLose.equals(WinOrLose.DRAW))
                .count();
    }
}