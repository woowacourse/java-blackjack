package BlackJack.domain;

import java.util.EnumMap;
import java.util.Map;

public class DealerScore {
    Map<Result, Integer> dealerScore;

    public DealerScore(){
        dealerScore = new EnumMap<Result, Integer>(Result.class);
    }

    public void addResult(Result result, Integer count){
        dealerScore.put(result,count);
    }

    public Map<Result, Integer> getDealerScore() {
        return dealerScore;
    }

}
