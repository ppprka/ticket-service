package ru.innowise.danko.ticketservice.util;

public class ObjectNotFound extends RuntimeException {

        public ObjectNotFound(Object o, Long id){
            super(o.getClass().getName() + " with id " + id + " doesnt exist");
        }

}
