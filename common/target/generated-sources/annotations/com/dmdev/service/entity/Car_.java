package com.dmdev.service.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Car.class)
public abstract class Car_ {

	public static volatile SingularAttribute<Car, CarCharacteristic> carCharacteristic;
	public static volatile SingularAttribute<Car, String> model;
	public static volatile SingularAttribute<Car, Long> id;
	public static volatile ListAttribute<Car, Request> requests;
	public static volatile SingularAttribute<Car, Status> status;

	public static final String CAR_CHARACTERISTIC = "carCharacteristic";
	public static final String MODEL = "model";
	public static final String ID = "id";
	public static final String REQUESTS = "requests";
	public static final String STATUS = "status";

}

