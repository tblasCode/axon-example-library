package com.jos.lab.axon.library.aggregate;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jos.lab.axon.library.command.RegisterLibraryCommand;
import com.jos.lab.axon.library.event.RegisterLibraryEvent;

class LibraryTest {

    private FixtureConfiguration<Library> fixture;
    
    @BeforeEach
    void setUp() throws Exception {
        fixture = new AggregateTestFixture<Library>(Library.class);
    }

    @Test
    void register_library_when_call_register_command_and_return_event() {
        fixture.givenNoPriorActivity()
        .when(new RegisterLibraryCommand("personal")).expectEvents(new RegisterLibraryEvent("personal"));
    }

}
