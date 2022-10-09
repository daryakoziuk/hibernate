package com.dmdev.service.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, PersonalInfo> personalInfo;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, Role> role;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile ListAttribute<User, Request> requests;
	public static volatile SingularAttribute<User, String> username;

	public static final String PERSONAL_INFO = "personalInfo";
	public static final String PASSWORD = "password";
	public static final String ROLE = "role";
	public static final String ID = "id";
	public static final String REQUESTS = "requests";
	public static final String USERNAME = "username";

}

