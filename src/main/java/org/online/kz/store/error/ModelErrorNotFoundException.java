package org.online.kz.store.error;

public class ModelErrorNotFoundException extends RuntimeException {

    public ModelErrorNotFoundException() {
        super();
    }

    public ModelErrorNotFoundException(String message) {
        super(message);
    }
}
