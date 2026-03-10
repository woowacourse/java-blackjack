package dto;

import domain.FinalResult;

public record FinalResultDto(
        String name,
        String result
) {
    public static FinalResultDto from(FinalResult finalResult) {
        return new FinalResultDto(finalResult.getName().getName(), finalResult.getResultType().getType());
    }
}
