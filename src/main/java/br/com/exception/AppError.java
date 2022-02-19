package br.com.exception;

public class AppError extends RuntimeException {

    public AppError() {
    }
    
    public AppError(String message) {
        super(message);
    }
    
}
