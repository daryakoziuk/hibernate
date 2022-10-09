package com.dmdev.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Predicate_ {

    private final List<Predicate> listPredicates = new ArrayList<>();

    public static Predicate_ builder(){
        return new Predicate_();
    }

    public Predicate_ add(Predicate predicate){
        if (predicate!=null){
            listPredicates.add(predicate);
        }
        return this;
    }

    public List<Predicate> getPredicates() {
        return listPredicates;
    }
}
