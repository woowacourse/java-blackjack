package domain.betting;

import java.util.ArrayList;
import java.util.List;

public class BatMonies {
    private final List<BatMoney> batMonies;

    public BatMonies(List<BatMoney> batMonies) {
        this.batMonies = new ArrayList<>(batMonies);
    }
}
