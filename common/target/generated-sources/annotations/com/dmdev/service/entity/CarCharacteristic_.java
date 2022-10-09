package com.dmdev.service.entity;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CarCharacteristic.class)
public abstract class CarCharacteristic_ {

	public static volatile SingularAttribute<CarCharacteristic, Integer> engineVolume;
	public static volatile SingularAttribute<CarCharacteristic, TypeTransmission> transmission;
	public static volatile SingularAttribute<CarCharacteristic, Car> car;
	public static volatile SingularAttribute<CarCharacteristic, LocalDate> dateRelease;
	public static volatile SingularAttribute<CarCharacteristic, Integer> id;
	public static volatile SingularAttribute<CarCharacteristic, TypeFuel> type;

	public static final String ENGINE_VOLUME = "engineVolume";
	public static final String TRANSMISSION = "transmission";
	public static final String CAR = "car";
	public static final String DATE_RELEASE = "dateRelease";
	public static final String ID = "id";
	public static final String TYPE = "type";

}

