package com.hcaglar.implicitamingstrategy.utilty;

import org.hibernate.HibernateException;
import org.hibernate.boot.model.naming.*;
import org.hibernate.boot.model.source.spi.AttributePath;
import org.hibernate.boot.spi.MetadataBuildingContext;
import org.hibernate.internal.util.StringHelper;

import static org.hibernate.boot.model.naming.ImplicitJoinColumnNameSource.*;
import java.io.Serializable;
import java.util.Locale;
import java.util.Optional;

/**
 * @author Hüseyin ÇAĞLAR
 * @version 1.0
 * @since 4.03.2022
 */
public class CustomImplicitNamingStrategy implements ImplicitNamingStrategy, Serializable {

    public static final ImplicitNamingStrategy INSTANCE = new CustomImplicitNamingStrategy();

    @Override
    public Identifier determinePrimaryTableName(ImplicitEntityNameSource implicitEntityNameSource) {
        final var source = Optional
               .ofNullable(implicitEntityNameSource)
               .orElseThrow(() -> new HibernateException("Entity naming information was not provided."));

        final var tableName = Optional
               .ofNullable(transformEntityName(source.getEntityNaming()))
                .orElseThrow(() -> new HibernateException("Could not determine primary table name for entity"));

        return toIdentifier(tableName, source.getBuildingContext());
    }


    @Override
    public Identifier determineJoinTableName(ImplicitJoinTableNameSource implicitJoinTableNameSource) {
        final var tableContext = Optional
                .ofNullable(implicitJoinTableNameSource)
                .orElseThrow(() -> new HibernateException("Could not determine join table name for entity"));

        final var name = String.format("%s_%s",
                tableContext.getOwningPhysicalTableName(),
                tableContext.getNonOwningPhysicalTableName());

        return toIdentifier(name, tableContext.getBuildingContext());
    }

    @Override
    public Identifier determineCollectionTableName(ImplicitCollectionTableNameSource implicitCollectionTableNameSource) {
        final var entityName = Optional.ofNullable(implicitCollectionTableNameSource)
                .orElseThrow(() -> new HibernateException("Could not determine collection table name for entity"));
        final var name = String.format("%s_%s",entityName,
                transformAttributePath(implicitCollectionTableNameSource.getOwningAttributePath()));
        return toIdentifier(name, entityName.getBuildingContext());
    }

    @Override
    public Identifier determineDiscriminatorColumnName(ImplicitDiscriminatorColumnNameSource implicitDiscriminatorColumnNameSource) {
        final var source = Optional
                .ofNullable(implicitDiscriminatorColumnNameSource)
                .orElseThrow(() -> new HibernateException("Could not determine discriminitor column name for entity"));

        return toIdentifier(source.getBuildingContext().getMappingDefaults().getImplicitDiscriminatorColumnName(), source.getBuildingContext());
    }

    @Override
    public Identifier determineTenantIdColumnName(ImplicitTenantIdColumnNameSource implicitTenantIdColumnNameSource) {
        final var source = Optional
                .ofNullable(implicitTenantIdColumnNameSource)
                .orElseThrow(() -> new HibernateException("Could not determine tenant id column name for entity"));

        return toIdentifier(source.getBuildingContext().getMappingDefaults().getImplicitTenantIdColumnName(),source.getBuildingContext());
    }

    @Override
    public Identifier determineIdentifierColumnName(ImplicitIdentifierColumnNameSource implicitIdentifierColumnNameSource) {
       final var source = Optional
                .ofNullable(implicitIdentifierColumnNameSource)
                .orElseThrow(() -> new HibernateException("Could not determine Identifier column name for entity"));
        return toIdentifier(transformAttributePath(source.getIdentifierAttributePath()),source.getBuildingContext());
    }

    @Override
    public Identifier determineBasicColumnName(ImplicitBasicColumnNameSource implicitBasicColumnNameSource) {
        final var source = Optional
                .ofNullable(implicitBasicColumnNameSource)
                .orElseThrow(() -> new HibernateException("Could not determine basic column name for entity"));
        return toIdentifier(transformAttributePath(source.getAttributePath()),source.getBuildingContext());
    }

    @Override
    public Identifier determineJoinColumnName(ImplicitJoinColumnNameSource implicitJoinColumnNameSource) {
        final var source = Optional
                .ofNullable(implicitJoinColumnNameSource)
                .orElseThrow(() -> new HibernateException("Could not determine join column name for entity"));
        final var name = source.getNature() != Nature.ELEMENT_COLLECTION && source.getAttributePath() != null ?
                                 in(transformAttributePath(source.getAttributePath()), source) :
                                 in(transformEntityName(source.getEntityNaming()), source);

        return toIdentifier(name, source.getBuildingContext());
    }

    @Override
    public Identifier determinePrimaryKeyJoinColumnName(ImplicitPrimaryKeyJoinColumnNameSource implicitPrimaryKeyJoinColumnNameSource) {
        return Optional
                .ofNullable(implicitPrimaryKeyJoinColumnNameSource)
                .orElseThrow(() -> new HibernateException("Could not determine primary key join column name for entity"))
                .getReferencedPrimaryKeyColumnName();
    }

    @Override
    public Identifier determineAnyDiscriminatorColumnName(ImplicitAnyDiscriminatorColumnNameSource implicitAnyDiscriminatorColumnNameSource) {
        final var source = Optional
                .ofNullable(implicitAnyDiscriminatorColumnNameSource)
                .orElseThrow(() -> new HibernateException("Could not determine any discriminator column name for entity"));

        final var columnName = String
                .format("%s_%s",
                        transformAttributePath(source.getAttributePath()),
                        source.getBuildingContext().getMappingDefaults().getImplicitDiscriminatorColumnName());

        return toIdentifier(columnName, source.getBuildingContext());

    }

    @Override
    public Identifier determineAnyKeyColumnName(ImplicitAnyKeyColumnNameSource implicitAnyKeyColumnNameSource) {
        final var source = Optional
                .ofNullable(implicitAnyKeyColumnNameSource)
                .orElseThrow(() -> new HibernateException("Could not determine any key column name for entity"));

        final var columnName = String
                .format("%s_%s",
                        transformAttributePath(source.getAttributePath()),
                        source.getBuildingContext().getMappingDefaults().getImplicitIdColumnName());

        return toIdentifier(columnName, source.getBuildingContext());
    }

    @Override
    public Identifier determineMapKeyColumnName(ImplicitMapKeyColumnNameSource implicitMapKeyColumnNameSource) {
        final var source = Optional
                .ofNullable(implicitMapKeyColumnNameSource)
                .orElseThrow(() -> new HibernateException("Could not determine map key column name for entity"));

        final var columnName = String
                .format("%s_KEY",
                        transformAttributePath(source.getPluralAttributePath()));

        return toIdentifier(columnName, source.getBuildingContext());
    }

    @Override
    public Identifier determineListIndexColumnName(ImplicitIndexColumnNameSource implicitIndexColumnNameSource) {
        final var source = Optional
                .ofNullable(implicitIndexColumnNameSource)
                .orElseThrow(() -> new HibernateException("Could not determine list index column name for entity"));

        final var columnName = String
                .format("%s_ORDER",
                        transformAttributePath(source.getPluralAttributePath()));
        return toIdentifier(columnName, source.getBuildingContext());
    }

    @Override
    public Identifier determineForeignKeyName(ImplicitForeignKeyNameSource implicitForeignKeyNameSource) {
        final var source = Optional
                .ofNullable(implicitForeignKeyNameSource)
                .orElseThrow(() -> new HibernateException("Could not determine foreign key column name for entity"));

        final var userProvidedIdentifier = Optional.ofNullable(source.getUserProvidedIdentifier());

         if(userProvidedIdentifier.isPresent()) {
             return userProvidedIdentifier.get();
         } else {
             final var hashFkName = NamingHelper
                     .withCharset(source.getBuildingContext()
                             .getBuildingOptions()
                             .getSchemaCharset())
                     .generateHashedFkName("FK",
                             source.getTableName(),
                             source.getReferencedTableName(),
                             source.getColumnNames());

             return toIdentifier(hashFkName, source.getBuildingContext());
         }
    }

    @Override
    public Identifier determineUniqueKeyName(ImplicitUniqueKeyNameSource implicitUniqueKeyNameSource) {
        final var source = Optional
                .ofNullable(implicitUniqueKeyNameSource)
                .orElseThrow(() -> new HibernateException("Could not determine unique key name for entity"));

        final var userProvidedIdentifier = Optional.ofNullable(source.getUserProvidedIdentifier());

        if(userProvidedIdentifier.isPresent()) {
            return userProvidedIdentifier.get();
        } else {
            final var hashFkName = NamingHelper
                    .withCharset(source.getBuildingContext()
                            .getBuildingOptions()
                            .getSchemaCharset())
                    .generateHashedConstraintName("UK",
                            source.getTableName(),
                            source.getColumnNames());

            return toIdentifier(hashFkName, source.getBuildingContext());
        }
    }

    @Override
    public Identifier determineIndexName(ImplicitIndexNameSource implicitIndexNameSource) {
        final var source = Optional
                .ofNullable(implicitIndexNameSource)
                .orElseThrow(() -> new HibernateException("Could not determine index name for entity"));

        final var userProvidedIdentifier = Optional.ofNullable(source.getUserProvidedIdentifier());

        if(userProvidedIdentifier.isPresent()) {
            return userProvidedIdentifier.get();
        } else {
            final var hashFkName = NamingHelper
                    .withCharset(source.getBuildingContext()
                            .getBuildingOptions()
                            .getSchemaCharset())
                    .generateHashedConstraintName("IDX",
                            source.getTableName(),
                            source.getColumnNames());

            return toIdentifier(hashFkName, source.getBuildingContext());
        }
    }

    protected String transformAttributePath(AttributePath attributePath) {
        return attributePath.getProperty();
    }

    protected Identifier toIdentifier(String stringForm, MetadataBuildingContext buildingContext) {
        return buildingContext
                .getMetadataCollector()
                .getDatabase()
                .getJdbcEnvironment()
                .getIdentifierHelper()
                .toIdentifier(stringForm);
    }

    protected String transformEntityName(EntityNaming entityNaming) {
        return StringHelper
                .isNotEmpty(entityNaming.getJpaEntityName()) ?
                entityNaming.getJpaEntityName() :
                StringHelper.unqualify(entityNaming.getEntityName());
    }

    private String in(String name, ImplicitJoinColumnNameSource source){
        return String.format("%s_%s",name ,source.getReferencedColumnName().getText());
    }
}
