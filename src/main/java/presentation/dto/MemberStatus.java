package presentation.dto;

import java.util.List;

public record MemberStatus(
        String memberName,
        List<String> cards,
        int totalValue
) {
}
