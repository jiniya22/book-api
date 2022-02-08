package xyz.applebox.book.config.exception;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException() {
        super("잘못된 입력입니다.");
    }
    public InvalidInputException(String msg) {
        super(msg);
    }
}
