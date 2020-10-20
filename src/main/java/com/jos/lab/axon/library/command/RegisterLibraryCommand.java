package com.jos.lab.axon.library.command;

import lombok.Data;

@Data
public class RegisterLibraryCommand {
    
    private final Integer libraryId;
    private final String name;
}
