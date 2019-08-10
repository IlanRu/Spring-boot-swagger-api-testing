package com.sabugo.spring.test;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(Long id) {
        super("coud not find item number " + id);
    }
}
