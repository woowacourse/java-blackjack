package blackjack.domain;

import blackjack.dto.HandState;
import java.util.function.Consumer;
import java.util.function.Function;

public class HitInputOutput {

    private final Function<Nickname, Boolean> readWannaHit;
    private final Consumer<HandState> printHitResult;

    public HitInputOutput(Function<Nickname, Boolean> readWannaHit, Consumer<HandState> printHitResult) {
        this.readWannaHit = readWannaHit;
        this.printHitResult = printHitResult;
    }

    public boolean executeReadIngWannaHit(Nickname nickname) {
        return readWannaHit.apply(nickname);
    }

    public void executePrintingHitResult(HandState state) {
        printHitResult.accept(state);
    }
}
