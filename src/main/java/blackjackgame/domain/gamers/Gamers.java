package blackjackgame.domain.gamers;

import blackjackgame.domain.card.Deck;
import java.util.ArrayList;
import java.util.List;

public class Gamers {
    private final List<Gamer> gamers;

    private Gamers(List<Gamer> gamers) {
        this.gamers = gamers;
    }

    public static Gamers createByNamesAndBetMoneys(List<String> names, List<Double> betMoneys) {
        List<Gamer> gamers = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            gamers.add(Gamer.createByNameAndBetMoney(names.get(i), betMoneys.get(i)));
        }

        return new Gamers(gamers);
    }

    public void cardHoldersDraw(Deck deck, int execution_count) {
        gamers.forEach(gamer -> gamer.cardHolderDraw(deck, execution_count));
    }

    public List<String> getRawGamerNames() {
        return gamers.stream()
                .map(Gamer::getRawGamerName)
                .toList();
    }

    public List<CardHolder> getCardHolders() {
        return gamers.stream()
                .map(Gamer::getCardHolder)
                .toList();
    }

    public List<Double> getGameProfits(Gamer dealer) {
        return gamers.stream()
                .map(gamer -> gamer.getGameProfit(dealer))
                .toList();
    }

    public int getSize() {
        return gamers.size();
    }
}
