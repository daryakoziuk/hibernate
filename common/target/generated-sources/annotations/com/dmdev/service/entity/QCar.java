package com.dmdev.service.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCar is a Querydsl query type for Car
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCar extends EntityPathBase<Car> {

    private static final long serialVersionUID = 465058321L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCar car = new QCar("car");

    public final QCarCharacteristic carCharacteristic;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath model = createString("model");

    public final ListPath<Request, QRequest> requests = this.<Request, QRequest>createList("requests", Request.class, QRequest.class, PathInits.DIRECT2);

    public final EnumPath<Status> status = createEnum("status", Status.class);

    public QCar(String variable) {
        this(Car.class, forVariable(variable), INITS);
    }

    public QCar(Path<? extends Car> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCar(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCar(PathMetadata metadata, PathInits inits) {
        this(Car.class, metadata, inits);
    }

    public QCar(Class<? extends Car> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.carCharacteristic = inits.isInitialized("carCharacteristic") ? new QCarCharacteristic(forProperty("carCharacteristic"), inits.get("carCharacteristic")) : null;
    }

}

