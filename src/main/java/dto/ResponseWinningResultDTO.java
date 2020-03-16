package dto;

import java.util.*;

public class ResponseWinningResultDTO {
    private Map<String, Boolean> winningPlayer;

    private ResponseWinningResultDTO(Map<String, Boolean> winningPlayer) {
        this.winningPlayer = winningPlayer;
    }

    public static ResponseWinningResultDTO create(Map<String, Boolean> winningPlayer) {
        return new ResponseWinningResultDTO(winningPlayer);
    }

    public Map<String, Boolean> getWinningPlayer() {
        return this.winningPlayer;
    }
}
