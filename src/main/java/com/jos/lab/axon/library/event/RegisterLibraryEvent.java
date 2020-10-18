package com.jos.lab.axon.library.event;

import lombok.Data;

@Data
public class RegisterLibraryEvent {

    private final Integer libraryId;
    private final String name;
}
