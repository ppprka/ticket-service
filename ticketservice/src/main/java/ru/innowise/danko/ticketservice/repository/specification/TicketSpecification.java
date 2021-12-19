package ru.innowise.danko.ticketservice.repository.specification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import ru.innowise.danko.ticketservice.entity.Ticket;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TicketSpecification implements Specification<Ticket> {

    private SearchCriteria criteria;

    public TicketSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Specification<Ticket> and(Specification<Ticket> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<Ticket> or(Specification<Ticket> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<Ticket> root, CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}
