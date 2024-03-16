package dto;

import java.util.Map;

public record GameResult(Double dealerResult,
                         Map<String, Double> playerResult) {


//    public int dealerWin() {
//        if (dealerResult.containsKey(PlayerResult.WIN)) {
//            return dealerResult.get(PlayerResult.WIN);
//        }
//        return 0;
//    }
//
//    public int dealerLose() {
//        if (dealerResult.containsKey(PlayerResult.LOSE)) {
//            return dealerResult.get(PlayerResult.LOSE);
//        }
//        return 0;
//    }
//
//    public int dealerTie() {
//        if (dealerResult.containsKey(PlayerResult.TIE)) {
//            return dealerResult.get(PlayerResult.TIE);
//        }
//        return 0;
//    }
//
//    public PlayerResult playerResult(final String name) {
//        if(playerResult.containsKey(name)){
//            return playerResult.get(name);
//        }
//        throw new IllegalArgumentException("존재하지 않는 이름입니다.");
//    }
//
}
