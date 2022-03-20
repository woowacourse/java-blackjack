package blackjack.domain.machine;

import blackjack.domain.participant.Players;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import blackjack.domain.dto.DealerRecordDto;
import blackjack.domain.dto.PlayerRecordDto;
import blackjack.domain.dto.RecordsDto;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class Records {
    public static RecordsDto of(Dealer dealer, Players players) {
        Map<String, String> playerRecords = getPlayerRecords(dealer, players.getPlayers());
        Map<String, Integer> dealerRecords = getDealerRecords(dealer, players.getPlayers());

        PlayerRecordDto playerRecordDto = new PlayerRecordDto(playerRecords);
        DealerRecordDto dealerRecordDto = new DealerRecordDto(dealer.getName(), dealerRecords);

        return new RecordsDto(dealerRecordDto, playerRecordDto);
    }

    private static Map<String, String> getPlayerRecords(Dealer dealer, List<Player> players) {
        Map<String, String> records = new LinkedHashMap<>();
        players.forEach(player
                -> records.put(player.getName(), Record.getRecord(player, dealer).getPlayerRecord()));

        return records;
    }

    private static Map<String, Integer> getDealerRecords(Dealer dealer, List<Player> players) {
        Map<String, Integer> records = Arrays.stream(Record.values())
                .collect(Collectors.toMap(Record::getPlayerRecord, record -> 0,
                        (o1, o2) -> o2, TreeMap::new));

        for (Player player : players) {
            String record = Record.getRecord(player, dealer).getDealerRecord();
            records.put(record, records.getOrDefault(record, 0) + 1);
        }

        return records;
    }
}