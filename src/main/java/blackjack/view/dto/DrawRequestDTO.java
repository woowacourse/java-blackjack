package blackjack.view.dto;

import java.util.Arrays;

public class DrawRequestDTO {
	private final boolean result;

	public DrawRequestDTO(String command) {
		this.result = PermittedRequest.findByCommand(command)
			.result;
	}

	public boolean isDraw() {
		return result;
	}

	private enum PermittedRequest {
		Y("y", true),
		N("n", false);

		private final String command;
		private final boolean result;

		PermittedRequest(String command, boolean result) {
			this.command = command;
			this.result = result;
		}

		public static PermittedRequest findByCommand(String command) {
			return Arrays.stream(values())
					.filter(permittedRequest -> permittedRequest.equal(command))
					.findFirst()
					.orElseThrow(() -> new IllegalArgumentException(String.format("%s는 올바른 요청이 아닙니다.", command)));
		}

		private boolean equal(String command) {
			return this.command.equals(command);
		}
	}
}
