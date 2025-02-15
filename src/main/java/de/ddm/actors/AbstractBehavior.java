package de.ddm.actors;

import akka.actor.typed.javadsl.ActorContext;

/**
 * To be removed, once issue is fixed in base code
 * @param <T>
 */
public abstract class AbstractBehavior<T> extends akka.actor.typed.javadsl.AbstractBehavior<T> {

    public AbstractBehavior(ActorContext<T> context) {
        super(context);
        try {
            if(context.getSelf() == null){
                System.out.println("Context self is null, sleeping for a second to workaround lazy initialization");
                Thread.sleep(1000); // Sleep for a second to workaround context.getSelf() returning null
            } else if (de.ddm.singletons.ReaperSingleton.get() == null) {
                System.out.println("Reaper is null, sleeping for a second to workaround lazy initialization");
                Thread.sleep(1000); // Sleep for a second to workaround ReaperSingleton.get() returning null
            } else {
                System.out.println("Worker actor created with path: " + context.getSelf().path().toString());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
