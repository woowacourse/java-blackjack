package domain;

import exception.ErrorMessage;

public record Name(String name) {

    public Name {
        validate(name);
    }

    private void validate(String input){
        if(input == null || input.isBlank()){
            throw new IllegalArgumentException(ErrorMessage.EMPTY_NAME.getMessage());
        }
    }
}