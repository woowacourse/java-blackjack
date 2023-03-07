package controller;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class InputProcessHandler {

    private InputProcessHandler() {
        throw new IllegalStateException("해당 클래스는 인스턴스를 생성할 수 없습니다.");
    }

    public static <Input, Output> Output repeat(Supplier<Input> inputSupplier, Runnable guideRunnable,
            Function<Input, Output> processFunction, Consumer<String> exceptionConsumer) {
        Optional<Output> output = Optional.empty();

        while (output.isEmpty()) {
            guideRunnable.run();
            output = process(inputSupplier, processFunction, exceptionConsumer);
        }
        return output.orElseThrow(() -> new IllegalStateException("애플리케이션에 문제가 발생했습니다."));
    }

    private static <Input, Output> Optional<Output> process(Supplier<Input> inputSupplier,
            Function<Input, Output> processFunction, Consumer<String> exceptionConsumer) {
        try {
            Input input = inputSupplier.get();

            return Optional.of(processFunction.apply(input));
        } catch (IllegalArgumentException e) {
            exceptionConsumer.accept(e.getMessage());
            return Optional.empty();
        }
    }
}
