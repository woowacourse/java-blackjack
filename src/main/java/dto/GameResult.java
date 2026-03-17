package dto;

import static domain.member.MemberInfo.DEALER_NAME;

public record GameResult(
        String name,
        int result
) {

    public boolean isDealer() {
        return name.equals(DEALER_NAME);
    }
}
