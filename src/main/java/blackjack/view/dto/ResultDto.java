package blackjack.view.dto;

import blackjack.domain.game.ResultGame;
import blackjack.domain.game.WinTieLose;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultDto {
    private static final String WIN_MASSAGE = "승";
    private static final String TIE_MASSAGE = "무";
    private static final String LOSE_MASSAGE = "패";
    private static  final  String SPACE = " ";
    private final Map<Player, WinTieLose> playersResult;
    public ResultDto(Map<Player, WinTieLose> playersResult){
        this.playersResult = playersResult;
    }
    public List<String> getParticipantsName(){
        return playersResult.keySet().stream()
                .map(Participant::getName)
                .collect(Collectors.toList());
    }
    public String getDealerResult(){
        return getDealerCount(WinTieLose.WIN)+ WIN_MASSAGE + SPACE +
                getDealerCount(WinTieLose.TIE)  + TIE_MASSAGE + SPACE +
                getDealerCount(WinTieLose.LOSE) + LOSE_MASSAGE;
    }
    private int getDealerCount(final WinTieLose expected) {
        return (int) playersResult.values()
                .stream()
                .filter(winTieLose -> winTieLose.equals(expected.reverseValue()))
                .count();
    }

    public String getPlayerResult(final Player player) {
        WinTieLose result = playersResult.get(player);
        if(WinTieLose.WIN.equals(result)){
            return WIN_MASSAGE;
        }
        if(WinTieLose.TIE.equals(result)){
            return TIE_MASSAGE;
        }
        if(WinTieLose.LOSE.equals(result)){
            return LOSE_MASSAGE;
        }
        throw new IllegalArgumentException();
    }

}
