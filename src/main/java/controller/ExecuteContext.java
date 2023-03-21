package controller;

import java.util.function.Supplier;

import view.OutputView;

public class ExecuteContext {

	public static <T> T RetryIfThrowsException(final Supplier<T> strategy) {
		T result = null;
		while (result == null) {
			result = tryCatchStrategy(strategy, result);
		}
		return result;
	}

	private static <T> T tryCatchStrategy(final Supplier<T> strategy, T result) {
		try {
			result = strategy.get();
		} catch (IllegalArgumentException exception) {
			OutputView.printErrorMessage(exception.getMessage());
		}
		return result;
	}
}
