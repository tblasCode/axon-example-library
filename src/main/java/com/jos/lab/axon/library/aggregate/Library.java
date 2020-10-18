package com.jos.lab.axon.library.aggregate;

import java.util.ArrayList;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.jos.lab.axon.library.command.RegisterLibraryCommand;
import com.jos.lab.axon.library.event.RegisterLibraryEvent;

@Aggregate
public class Library {

    @AggregateIdentifier
    private Integer libraryId;

    private String name;

    protected Library() {
        // For Axon instantiation
    }

    @CommandHandler
    public Library(RegisterLibraryCommand cmd) {
        AggregateLifecycle.apply(new RegisterLibraryEvent(cmd.getLibraryId(), cmd.getName()));
    }

    @EventSourcingHandler
    private void handleCreatedEvent(RegisterLibraryEvent event) {
        libraryId = event.getLibraryId();
        name = event.getName();
    }
}
