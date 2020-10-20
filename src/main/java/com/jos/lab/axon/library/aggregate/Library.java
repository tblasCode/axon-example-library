package com.jos.lab.axon.library.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import com.jos.lab.axon.library.command.RegisterLibraryCommand;
import com.jos.lab.axon.library.event.RegisterLibraryEvent;

@Aggregate
public class Library {

    /** The @AggregateIdentifier is the external reference point to into the Library Aggregate. 
     *  This field is a hard requirement, as without it Axon will not know to which Aggregate a given Command is targeted.
     */
    @AggregateIdentifier
    private Integer libraryId;

    private String name;

    /**
     * A no-arg constructor, which is required by Axon.
     * Axon Framework uses this constructor to create an empty aggregate instance before initializing it using past Events. 
     * Failure to provide this constructor will result in an exception when loading the Aggregate. 
     */
    protected Library() {
        // For Axon instantiation @NoArgsConstructor
    }

    /** A @CommandHandler annotated constructor, or differently put the 'command handling constructor'. 
     *This annotation tells the framework that the given constructor is capable of handling the IssueCardCommand.
     *The @CommandHandler annotated functions are the place where you would put your decision-making/business logic.
     */ 
    @CommandHandler
    public Library(RegisterLibraryCommand cmd) {
        Assert.notNull(cmd.getLibraryId(), "ID should not be null");
        Assert.notNull(cmd.getName(), "Name should not be null");

        //The static AggregateLifecycle#apply(Object...) is what is used when an Event Message should be published.
        //Upon calling this function the provided Objects will be published as EventMessages within the scope of the Aggregate they are applied in.
        AggregateLifecycle.apply(new RegisterLibraryEvent(cmd.getLibraryId(), cmd.getName()));
    }

    /**
     * Using the @EventSourcingHandler is what tells the framework that the annotated function should be called when the Aggregate is 'sourced from its events'.  
     * As all the Event Sourcing Handlers combined will form the Aggregate, this is where all the state changes happen.  
     * Note that the Aggregate Identifier must be set in the @EventSourcingHandler of the very first Event published by the aggregate.
     * 
     */
    @EventSourcingHandler
    private void on(RegisterLibraryEvent event) {
        libraryId = event.getLibraryId();
        name = event.getName();
    }
}
