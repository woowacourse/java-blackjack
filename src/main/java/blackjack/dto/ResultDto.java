package blackjack.dto;

import blackjack.domain.game.WinTieLose;
import blackjack.domain.participant.Player;

import java.util.HashMap;
import java.util.Map;

public class ResultDto {
    private static final String WIN_MASSAGE = "승";
    private static final String TIE_MASSAGE = "무";
    private static final String LOSE_MASSAGE = "패";
    private final Map<String, String> playersResult;

    public ResultDto(Map<Player, WinTieLose> playersResult) {
        this.playersResult = new HashMap<>();
        playersResult.forEach((player, result) -> this.playersResult.put(player.getName(), getResultOutputString(result)));
    }

    public Map<String, String> getPlayerResult() {
        return this.playersResult;
    }

    public int getDealerWinCount() {
        return this.getDealerCount(LOSE_MASSAGE);
    }

    public int getDealerTieCount() {
        return this.getDealerCount(TIE_MASSAGE);
    }

    public int getDealerLoseCount() {
        return this.getDealerCount(WIN_MASSAGE);
    }

    private int getDealerCount(final String expected) {
        return (int) playersResult.values()
                .stream()
                .filter(result -> result.equals(expected))
                .count();
    }

    private String getResultOutputString(final WinTieLose result) {
        if (WinTieLose.WIN.equals(result)) {
            return WIN_MASSAGE;
        }
        if (WinTieLose.TIE.equals(result)) {
            return TIE_MASSAGE;
        }
        if (WinTieLose.LOSE.equals(result)) {
            return LOSE_MASSAGE;
        }
        throw new IllegalArgumentException();
    }

}
