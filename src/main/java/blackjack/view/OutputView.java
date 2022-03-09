package blackjack.view;

import blackjack.domain.InitialParticipantDTO;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static void printInitialCards(List<InitialParticipantDTO> dtos) {

        StringBuilder result = new StringBuilder();

        for (InitialParticipantDTO dto : dtos) {
            result.append(dto.getName());
            result.append(": ");

            result.append(dto.getCards().stream()
                    .map(c -> (c.getDenominationName() + c.getSymbolName()))
                    .collect(Collectors.joining(", ")));

            result.append("\n");
        }

        System.out.println(result);

    }
}
