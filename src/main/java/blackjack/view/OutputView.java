package blackjack.view;

import java.util.List;

import blackjack.dto.CardDto;
import blackjack.dto.DealerInitialHandDto;
import blackjack.dto.DealerResultDto;
import blackjack.dto.GamerHandDto;
import blackjack.dto.PlayerResultsDto;

public class OutputView {

	private static final String LINE_SEPARATOR = System.lineSeparator();
	private static final String COLON_WITH_SPACE = ": ";

	public void printInitialHands(DealerInitialHandDto dealerInitialHandDto, List<GamerHandDto> playersInitialHandDto) {
		printInitialMessage(dealerInitialHandDto, playersInitialHandDto);
		printDealerFirstCard(dealerInitialHandDto);
		printAllPlayerHands(playersInitialHandDto);
	}

	private void printInitialMessage(
		DealerInitialHandDto dealerInitialHandDto,
		List<GamerHandDto> playersInitialHandDto
	) {
		System.out.printf("%s와 %s에게 2장을 나누었습니다." + LINE_SEPARATOR,
			dealerInitialHandDto.name(), joinNames(getPlayerNames(playersInitialHandDto))
		);
	}

	private String joinNames(List<String> names) {
		return String.join(", ", names);
	}

	private List<String> getPlayerNames(List<GamerHandDto> playersInitialHandDto) {
		return playersInitialHandDto.stream().map(GamerHandDto::name).toList();
	}

	private void printDealerFirstCard(DealerInitialHandDto dealerInitialHandDto) {
		System.out.println(buildNameAndCard(
			dealerInitialHandDto.name(), formatCardName(dealerInitialHandDto.firstCard())
		));
	}

	private String formatCardName(CardDto cardDto) {
		return cardDto.cardNumber() + cardDto.cardShape();
	}

	private void printAllPlayerHands(List<GamerHandDto> playersInitialHandDto) {
		playersInitialHandDto.forEach(this::printGamerNameAndHand);
	}

	public void printGamerNameAndHand(GamerHandDto gamerHandDto) {
		System.out.println(buildGamerNameAndHand(gamerHandDto));
	}

	private StringBuilder buildGamerNameAndHand(GamerHandDto gamerHandDto) {
		return buildNameAndCard(gamerHandDto.name(), joinNames(getCardNames(gamerHandDto)));
	}

	private StringBuilder buildNameAndCard(String name, String card) {
		return new StringBuilder()
			.append(name)
			.append(" ")
			.append("카드")
			.append(COLON_WITH_SPACE)
			.append(card);
	}

	private List<String> getCardNames(GamerHandDto gamerHandDto) {
		return gamerHandDto.gamerHand().stream()
			.map(this::formatCardName)
			.toList();
	}

	public void printDealerMessage(String dealerName) {
		System.out.printf("%s는 16이하라 한장의 카드를 더 받았습니다." + LINE_SEPARATOR, dealerName);
	}

	public void printScore(GamerHandDto gamerHandDto, int score) {
		StringBuilder builder = buildGamerNameAndHand(gamerHandDto)
			.append(" - ")
			.append("결과")
			.append(COLON_WITH_SPACE)
			.append(score);
		System.out.println(builder);
	}

	public void printFinalResult(DealerResultDto dealerResultDto, PlayerResultsDto playerResultsDto) {
		System.out.println("## 최종 승패");
		printDealerResult(dealerResultDto);
		printPlayerResults(playerResultsDto);
	}

	private void printDealerResult(DealerResultDto dealerResultDto) {
		int winCount = dealerResultDto.winCount();
		int loseCount = dealerResultDto.loseCount();
		printResult(dealerResultDto.name(), formatDealerResult(winCount, loseCount));
	}

	private void printResult(String name, String result) {
		System.out.println(name + COLON_WITH_SPACE + result);
	}

	private String formatDealerResult(int winCount, int loseCount) {
		StringBuilder stringBuilder = new StringBuilder();
		if (winCount > 0) {
			stringBuilder.append(winCount).append("승 ");
		}
		if (loseCount > 0) {
			stringBuilder.append(loseCount).append("패");
		}
		return stringBuilder.toString();
	}

	private void printPlayerResults(PlayerResultsDto playerResultsDto) {
		playerResultsDto.playerNameResults().forEach(this::printResult);
	}

	public void printEmptyLine() {
		System.out.println();
	}
}
