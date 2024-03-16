package controller.dto;

import java.util.Collections;
import java.util.List;

public class BettingInfos {
    private List<BettingInfo> bettingInfos;

    private BettingInfos(List<BettingInfo> bettingInfoInfos) {
        this.bettingInfos = bettingInfoInfos;
    }

    public static BettingInfos from(List<BettingInfo> bettingInfos) {
        return new BettingInfos(bettingInfos.stream()
                .distinct()
                .toList());
    }

    public List<BettingInfo> getBettingInfos() {
        return Collections.unmodifiableList(bettingInfos);
    }
}
