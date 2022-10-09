package com.dmdev.service.entity;

import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Request.class)
public abstract class Request_ {

	public static volatile SingularAttribute<Request, LocalDateTime> dateReturn;
	public static volatile SingularAttribute<Request, Car> car;
	public static volatile SingularAttribute<Request, Tariff> tariff;
	public static volatile SingularAttribute<Request, Long> id;
	public static volatile SingularAttribute<Request, LocalDateTime> dateRequest;
	public static volatile SingularAttribute<Request, User> user;

	public static final String DATE_RETURN = "dateReturn";
	public static final String CAR = "car";
	public static final String TARIFF = "tariff";
	public static final String ID = "id";
	public static final String DATE_REQUEST = "dateRequest";
	public static final String USER = "user";

}

