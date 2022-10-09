package com.dmdev.service.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTariff is a Querydsl query type for Tariff
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTariff extends EntityPathBase<Tariff> {

    private static final long serialVersionUID = -525256249L;

    public static final QTariff tariff = new QTariff("tariff");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final ListPath<Request, QRequest> requests = this.<Request, QRequest>createList("requests", Request.class, QRequest.class, PathInits.DIRECT2);

    public final EnumPath<TariffType> type = createEnum("type", TariffType.class);

    public QTariff(String variable) {
        super(Tariff.class, forVariable(variable));
    }

    public QTariff(Path<? extends Tariff> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTariff(PathMetadata metadata) {
        super(Tariff.class, metadata);
    }

}

