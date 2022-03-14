package blackjack.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.BlackJack;
import blackjack.domain.Participant;
import blackjack.domain.Result;
import blackjack.domain.card.Card;

public class BlackJackDto {
    private static final String NAME_DELIMITER = ": ";
    private static final String CARD_DELIMITER = ", ";
    private static final int FIRST_CARD = 0;

    private final Participant dealer;
    private final List<Participant> players;
    private final Result result;

    public BlackJackDto(Participant dealer, List<Participant> players, Result result) {
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
        return dealer.getName() + NAME_DELIMITER + result.getDealerRevenue();
    }

    public List<String> getPlayersRevenue() {
        return players.stream()
            .map(player -> player.getName() + NAME_DELIMITER + result.getPlayerRevenue(player))
            .collect(Collectors.toList());
    }

    public Participant getDealer() {
        return dealer;
    }

    public List<Participant> getPlayers() {
        return players;
    }
}
