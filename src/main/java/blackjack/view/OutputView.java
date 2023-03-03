package blackjack.view;

import static java.text.MessageFormat.format;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printDealCards(final ParticipantResponse dealer, final List<ParticipantResponse> players, final int count) {
        System.out.println(format("{0}와 {1}에게 {2}장을 나누었습니다.",
                dealer.getName(), getPlayerNamesFormat(players), count));

        printHandedCardsWithoutScore(dealer);
        players.forEach(this::printHandedCardsWithoutScore);
    }

    private String getPlayerNamesFormat(final List<ParticipantResponse> players) {
        return players.stream()
                .map(ParticipantResponse::getName)
                .collect(Collectors.joining(", "));
    }

    public void printHandedCardsWithoutScore(final ParticipantResponse participant) {
        System.out.println(getHandedCards(participant));
    }

    private String getHandedCards(final ParticipantResponse participant) {
        final CardsResponse cardsResponse = participant.getCardsResponse();
        final List<String> cardInfos = cardsResponse.getCardInfos();

        return format("{0}카드: {1}", participant.getName(), getCardInfosFormat(cardInfos));
    }

    private String getCardInfosFormat(final List<String> cardInfos) {
        return String.join(", ", cardInfos);
    }
}

class ParticipantResponse {

    private final String name;
    private final CardsResponse cardsResponse;

    public ParticipantResponse(final String name, final CardsResponse cardsResponse) {
        this.name = name;
        this.cardsResponse = cardsResponse;
    }

    public String getName() {
        return name;
    }

    public CardsResponse getCardsResponse() {
        return cardsResponse;
    }
}

class CardsResponse {

    private final int totalScore;
    private final List<String> cardInfos;

    public CardsResponse(final int totalScore, final List<String> cardInfos) {
        this.totalScore = totalScore;
        this.cardInfos = cardInfos;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public List<String> getCardInfos() {
        return cardInfos;
    }
}
