package com.dmdev.service.entity;

import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tariff.class)
public abstract class Tariff_ {

	public static volatile SingularAttribute<Tariff, BigDecimal> price;
	public static volatile SingularAttribute<Tariff, Integer> id;
	public static volatile ListAttribute<Tariff, Request> requests;
	public static volatile SingularAttribute<Tariff, TariffType> type;

	public static final String PRICE = "price";
	public static final String ID = "id";
	public static final String REQUESTS = "requests";
	public static final String TYPE = "type";

}

