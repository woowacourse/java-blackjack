package blackjack;

import blackjack.domain.Players;
import blackjack.view.InputView;

import java.util.Optional;
import java.util.function.Supplier;

public class BlackjackController {

    public void run() {
        Players players = requestUntilValid(() -> Players.from(InputView.readPlayersName()));
    }


    private <T> T requestUntilValid(Supplier<T> supplier) {
        Optional<T> result = Optional.empty();
        while (result.isEmpty()) {
            result = tryGet(supplier);
        }
        return result.get();
    }

    private <T> Optional<T> tryGet(Supplier<T> supplier) {
        try {
            return Optional.of(supplier.get());
        } catch (IllegalArgumentException e) {
            // TODO: OutputView 생성 후 예외 메세지 출력
            return Optional.empty();
        }
    }
}
