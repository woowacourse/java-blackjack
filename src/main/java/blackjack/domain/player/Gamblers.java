package blackjack.domain.player;

import blackjack.domain.card.Deck;
import blackjack.domain.game.Result;
import blackjack.domain.game.WinOrLose;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gamblers {
    private final List<Gambler> gamblers;

    public Gamblers() {
        gamblers = new ArrayList<>();
    }

    public Gamblers(final List<Gambler> gamblers) {
        this.gamblers = gamblers;
    }

    public void add(Gambler gambler) {
        gamblers.add(gambler);
    }

    public void addAll(Gamblers gamblers) {
        gamblers.getGamblers().forEach(this::add);
    }

    public List<Gambler> getGamblers() {
        return Collections.unmodifiableList(gamblers);
    }

    public void initGamblerCards(Deck deck) {
        gamblers.forEach(player -> player.initializeCards(deck));
    }

    public Gambler getGamblerByName(Gambler gambler) {
        return gamblers.stream()
                .filter(player -> player.isSameName(gambler))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 겜블러가 없습니다."));
    }

    public void addGamblerResult(Result result, Dealer dealer) {
        for (Gambler gambler : gamblers) {
            WinOrLose winOrLose = WinOrLose.calculateGamblerWinOrNot(dealer, gambler);
            result.add(gambler, winOrLose);
        }
    }
}
