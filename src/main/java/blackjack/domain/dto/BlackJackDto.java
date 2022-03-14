package blackjack.domain.dto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.BlackJack;
import blackjack.domain.Participant;
import blackjack.domain.Result;
import blackjack.domain.card.Card;

public class BlackJackDto {
    private static final String NAME_DELIMITER = ": ";
    private static final String CARD_DELIMITER = ", ";
    private static final String MESSAGE_WIN = "승 ";
    private static final String MESSAGE_LOSE = "패 ";
    private static final int FIRST_CARD = 0;

    private final Participant dealer;
    private final List<Participant> players;
    private final Map<Participant, Result> result;

    public BlackJackDto(Participant dealer, List<Participant> players, Map<Participant, Result> result) {
        this.dealer = dealer;
        this.players = players;
        this.result = result;
    }

    public static BlackJackDto from(BlackJack blackJack) {
        return new BlackJackDto(blackJack.getDealer(), blackJack.getPlayers(), blackJack.getResult());
    }

    public String getDealerOpenCard() {
        return dealer.getName() + NAME_DELIMITER + dealer.getCards().get(FIRST_CARD).getName();
    }

    public String getPlayerCardStatus(Participant participant) {
        String[] playerCardStatus = participant.getCards().stream()
            .map(Card::getName)
            .toArray(String[]::new);

        return participant.getName() + NAME_DELIMITER + String.join(CARD_DELIMITER, playerCardStatus);
    }

    public String getDealerRevenue() {
        int dealerRevenue= result.values().stream()
            .mapToInt(Result::getRevenue)
            .sum();
        return dealer.getName() + NAME_DELIMITER + dealerRevenue;
    }

    public List<String> getPlayersRevenue() {
        return result.keySet().stream()
            .map(key -> key.getName() + NAME_DELIMITER + result.get(key).getRevenue())
            .collect(Collectors.toList());
    }

    public Participant getDealer() {
        return dealer;
    }

    public List<Participant> getPlayers() {
        return players;
    }
}
