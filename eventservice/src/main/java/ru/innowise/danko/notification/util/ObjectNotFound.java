package ru.innowise.danko.notification.util;

public class ObjectNotFound extends RuntimeException {

        public ObjectNotFound(Object o, String id){
            super(o.getClass().getName() + " with id " + id + " doesnt exist");
        }

}
