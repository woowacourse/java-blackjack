package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Dealer extends Gamer {
    private static final Dealer dealer = new Dealer();

    private final Map<WinDrawLose, Integer> winDrawLose = new EnumMap(WinDrawLose.class);

    private Dealer() {
        super("딜러", new Cards(new ArrayList<>()));
    }

    public static Dealer init() {
        return dealer;
    }

    public boolean checkHitFlag() {
        return getCards().calculateScore() <= 16;
    }

    @Override
    public List<Card> getViewCard() {
        return List.of(getCards().getCards().get(0));
    }

    @Override
    public void win() {
        winDrawLose.merge(WinDrawLose.WIN, 1, Integer::sum);
    }

    @Override
    public void draw() {
        winDrawLose.merge(WinDrawLose.DRAW, 1, Integer::sum);
    }

    @Override
    public void lose() {
        winDrawLose.merge(WinDrawLose.LOSE, 1, Integer::sum);
    }

    @Override
    public String getWinDrawLoseString() {
        return winDrawLose.entrySet().stream()
                .map(set -> set.getValue() + set.getKey().getName())
                .collect(Collectors.joining(" "));
    }
}
