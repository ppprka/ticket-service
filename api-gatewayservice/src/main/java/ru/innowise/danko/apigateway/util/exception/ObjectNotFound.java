package ru.innowise.danko.apigateway.util.exception;

public class ObjectNotFound extends RuntimeException {

        public ObjectNotFound(Object o, Long id){
            super(o.getClass().getName() + " with id " + id + " doesnt exist");
        }
        public ObjectNotFound(Object o, String name){
            super(o.getClass().getName() + " with name " + name + " doesnt exist");
        }

}
