package com.dmdev.service.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCarCharacteristic is a Querydsl query type for CarCharacteristic
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCarCharacteristic extends EntityPathBase<CarCharacteristic> {

    private static final long serialVersionUID = -1049029108L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCarCharacteristic carCharacteristic = new QCarCharacteristic("carCharacteristic");

    public final QCar car;

    public final DatePath<java.time.LocalDate> dateRelease = createDate("dateRelease", java.time.LocalDate.class);

    public final NumberPath<Integer> engineVolume = createNumber("engineVolume", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final EnumPath<TypeTransmission> transmission = createEnum("transmission", TypeTransmission.class);

    public final EnumPath<TypeFuel> type = createEnum("type", TypeFuel.class);

    public QCarCharacteristic(String variable) {
        this(CarCharacteristic.class, forVariable(variable), INITS);
    }

    public QCarCharacteristic(Path<? extends CarCharacteristic> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCarCharacteristic(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCarCharacteristic(PathMetadata metadata, PathInits inits) {
        this(CarCharacteristic.class, metadata, inits);
    }

    public QCarCharacteristic(Class<? extends CarCharacteristic> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.car = inits.isInitialized("car") ? new QCar(forProperty("car"), inits.get("car")) : null;
    }

}

